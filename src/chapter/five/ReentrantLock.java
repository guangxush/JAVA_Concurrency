package chapter.five;

/**
 * Created by gshan on 2018/10/1
 */
public class ReentrantLock {
    /**
    final boolean nonfairTryAcquire(int acquires){
        final Thread current = Thread.currentThread();
        int c = getState();
        if(c==0){
            if(compareAndSetState(0,acquires)){
                return true;
            }
        }
        else if(current == getExclusiveOwnerThread())
        {
            int nextc = c+acquires;
            if(nextc<0)
                throw new Error("Maximum lock count exceeded");
            setState(nextc);
            return true;
        }
        return false;
    }
    protected final boolean tryRelease(int releases){
        int c = getState()-releases;
        if(Thread.currentThread()!=getExclusiveOwnerThread()){
            throw new IllegalMonitorStateException();
        }
        boolean free = false;
        if(c==0){
            free = true;
            setExclusiveOwnerThread(null);
        }
        setState(c);
        return free;
    }
    protected final boolean tryAcquire(int acquires){
        final Thread current = Thread.currentThread();
        int c = getState();
        if(c==0){
            if(!hasQueuedPredecessors()&&compareAndSetState(0,acquires)){
                setExclusiveOwnerThread(current);
                return true;
            }
        }else if(current == getExclusiveOwnerThread()){
            int nextc = c +acquires;
            if(nextc<0){
                throw new Error("Maximum lock count exceeded");
            }
            setState(nextc);
            return true;
        }
        return false;
    }**/
}
