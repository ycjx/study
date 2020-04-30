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
        boolean acquire2 = rateLimiter.acquire(20, 100);

        boolean acquire3 = rateLimiter.acquire(20, 100);
        boolean acquire4 = rateLimiter.acquire(20, 100);
        boolean acquire5 = rateLimiter.acquire(20, 100);
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
            if (lastRefillTime.compareAndSet(refillTime, newRefillTime)) {
                while (true) {
                    int currentLevel = consumedTokens.get();
                    int adjustedLevel = Math.min(currentLevel, burstSize); // In case burstSize decreased
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
