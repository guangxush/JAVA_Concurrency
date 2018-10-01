package chapter.five;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by gshan on 2018/10/1
 */
public class ConditionUseCase {
    Lock lock  = (Lock) new ReentrantLock();
    Condition condition = lock.newCondition();
    public void conditionWait() throws InterruptedException{
        lock.lock();
        try{
            condition.await();
        }finally {
            lock.unlock();
        }
    }
    public void conditionSignal() throws InterruptedException{
        lock.lock();
        try{
            condition.signal();
        }finally {
            lock.unlock();
        }
    }
}
