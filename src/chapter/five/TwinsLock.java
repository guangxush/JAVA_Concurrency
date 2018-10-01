package chapter.five;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
/**
 * Created by gshan on 2018/10/1
 */
public class TwinsLock implements Lock {
    private final Sync sync = new Sync(2);
    private static final class Sync extends AbstractQueuedLongSynchronizer{
        Sync(int count){
            if(count <= 0){
                throw new IllegalArgumentException("count must large than zero;");
            }
            setState(count);
        }
        public int tryAcquireShared(int reduceCount){
            for(;;){
                int current = (int) getState();
                int newCount = current - reduceCount;
                if(newCount<0 || compareAndSetState(current,newCount)){
                    return newCount;
                }
            }
        }

        public boolean tryReleaseShared(int returnCount){
            for(;;){
                int current = (int) getState();
                int newCount = current + returnCount;
                if(compareAndSetState(current,newCount)){
                    return true;
                }
            }
        }
    }

    @Override
    public void lock(){
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock(){
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

}
