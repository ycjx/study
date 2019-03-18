package com.yxj.Lock;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author:yuxj
 * @descriptio
 * @create:2019-03-07 11:41
 */
public class YcjxLock implements Lock, Serializable {

    private final Sync sync;

    //内部类，自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer {


//        abstract void lock();

        //线程获取锁
        @Override
        protected boolean tryAcquire(int arg) {
            assert arg == 1;
            //只有当前线程状态为0才能获取锁，否则返回false
            if (compareAndSetState(0, 1)) {
                //将同步器的所有者变成当前线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        //释放锁
        @Override
        protected boolean tryRelease(int arg) {
            int c = getState() - arg;
            if (!isHeldExclusively()) throw new IllegalMonitorStateException();
            boolean free = false;
            if (c == 0) {
                free = true;
                setExclusiveOwnerThread(null);
            }
            setState(c);
            return free;
        }

        //返回一个Condition，每个condition都包含了一个condition队列
        ConditionObject newCondition() {
            return new ConditionObject();
        }


        //是否处于占用状态
        @Override
        protected final boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }

        final boolean isLoack() {
            return getState() != 0;
        }


    }


    public YcjxLock() {
        sync = new Sync();
    }


    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }


}
