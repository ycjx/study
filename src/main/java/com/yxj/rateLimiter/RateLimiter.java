package com.yxj.rateLimiter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-04-30 11:08
 */
public class RateLimiter {


    private static RateLimiter rateLimiter = new RateLimiter(TimeUnit.SECONDS);


    public static void main(String[] args) {
//        int i= 0;
//        while (i<100) {
////            boolean acquire1 = rateLimiter.acquire(10, 2000);
//            boolean acquire2 = rateLimiter.acquire(20, 100);
////            if (acquire1) {
////                System.out.println("acquire1 成功");
////            } else {
////                System.out.println("acquire1 失败");
////            }
//
//            if (acquire2) {
//                System.out.println("acquire2 成功");
//            } else {
//                System.out.println("acquire2 失败");
//            }
//            i++;
//
//
//        }
        boolean acquire2 = rateLimiter.acquire(2, 100);

        boolean acquire3 = rateLimiter.acquire(2, 100);
        boolean acquire4 = rateLimiter.acquire(2, 100);
        boolean acquire5 = rateLimiter.acquire(2, 100);
        if (acquire2) {
            System.out.println("acquire2 成功");
        } else {
            System.out.println("acquire2 失败");
        }
    }


    private final long rateToMsConversion;

    private final AtomicInteger consumedTokens = new AtomicInteger();
    private final AtomicLong lastRefillTime = new AtomicLong(0);

    @Deprecated
    public RateLimiter() {
        this(TimeUnit.SECONDS);
    }

    public RateLimiter(TimeUnit averageRateUnit) {
        switch (averageRateUnit) {
            case SECONDS:
                rateToMsConversion = 1000;
                break;
            case MINUTES:
                rateToMsConversion = 60 * 1000;
                break;
            default:
                throw new IllegalArgumentException("TimeUnit of " + averageRateUnit + " is not supported");
        }
    }

    /**
     * 假设burstSize = 20 ，averageRate = 1000 ， rateToMsConversion=1000
     * <p>
     * 桶里面最多有20个令牌，一次执行距离上次执行2毫秒，那么产生了两个token，先去桶里去掉两个token,在去消耗一个token往桶里放
     * <p>
     * averageRate 过小的话，不是每次都会去减去桶里的token,这样就不能消费往桶里放token了
     * <p>
     * 好处 ： 这个限流比较平稳，不会出现 前1秒 999 后59秒 1
     *
     * @param burstSize
     * @param averageRate
     * @return
     */
    public boolean acquire(int burstSize, long averageRate) {
        return acquire(burstSize, averageRate, System.currentTimeMillis());
    }

    public boolean acquire(int burstSize, long averageRate, long currentTimeMillis) {
        if (burstSize <= 0 || averageRate <= 0) { // Instead of throwing exception, we just let all the traffic go
            return true;
        }

        refillToken(burstSize, averageRate, currentTimeMillis);
        return consumeToken(burstSize);
    }

    /**
     * 填充桶里面的token
     *
     * @param burstSize         桶里最多多少令牌
     * @param averageRate       一个rateToMsConversion 产生多少令牌
     * @param currentTimeMillis
     */
    private void refillToken(int burstSize, long averageRate, long currentTimeMillis) {
        //上次获取令牌的时间
        long refillTime = lastRefillTime.get();

        //与上次的时间间隔
        long timeDelta = currentTimeMillis - refillTime;

        //计算出时间间隔中该产生多少个令牌
        long newTokens = timeDelta * averageRate / rateToMsConversion;
        //大于0就继续
        if (newTokens > 0) {

            //新的填充时间 （当前时间）
            //TODO 这里为哈要重新计算呢 并发？
            long newRefillTime = refillTime == 0
                    ? currentTimeMillis
                    : refillTime + newTokens * rateToMsConversion / averageRate;
            //替换成功
            //TODO 没有替换成功会发送什么呢
            if (lastRefillTime.compareAndSet(refillTime, newRefillTime)) {
                while (true) {
                    //桶里被消费的数量
                    int currentLevel = consumedTokens.get();

                    //建议桶里token的数量
                    int adjustedLevel = Math.min(currentLevel, burstSize); // In case burstSize decreased

                    //桶里剩余的token
                    //如果averageRate 越小，newTokens就会越小，这样newLevel就会和currentLevel值一样，然后消费token的话就会消费不到
                    //只有过了一定的时间让newTokens>0,才能继续消费到token
                    int newLevel = (int) Math.max(0, adjustedLevel - newTokens);
                    if (consumedTokens.compareAndSet(currentLevel, newLevel)) {
                        return;
                    }
                }
            }
        }
    }

    private boolean consumeToken(int burstSize) {
        while (true) {
            int currentLevel = consumedTokens.get();
            //桶里被消费的数量大于burstSize 就报错
            if (currentLevel >= burstSize) {
                return false;
            }
            System.out.println(currentLevel);
            if (consumedTokens.compareAndSet(currentLevel, currentLevel + 1)) {
                return true;
            }
        }
    }

    public void reset() {
        consumedTokens.set(0);
        lastRefillTime.set(0);
    }
}
