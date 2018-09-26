package chapter.five;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by gshan on 2018/9/26
 */
public class LockUseCase {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        try{

        }finally{
            lock.unlock();
        }
    }
}
