package chapter.five;

/**
 * Created by gshan on 2018/10/1
 */
public class ReentrantReadWriteLock {
    /**
    protected final boolean tryAcquire(int acquires){
        Thread current = Thread.currentThread();
        int c = getState();
        int w = exclusiveCount(c);
        if(c!=0){
            //存在读锁或者当前获取线程已经不是已经获取写锁的线程
            if(w==0||current!=getExclusiveOwnerThread())
                return false;
            if(w+exclusiveCount(acquires)>MAX_COUNT){
                throw new Error("Maximum lock count exceeded");
            }
            setState(c+acquires);
            return true;
        }
        if(writeShouldBlock()||!compareAndSetState(c, c+acquires)){
            return false;
        }
        setExclusiveOwnerThread(current);
        return true;
    }
    protected final int tryAcquireShared(int unused){
        for(;;){
            int c = getState();
            int nextc = c + (1<<16);
            if(nextc<c){
                throw new Error("Maximum lock count exceeded");
            }
            if(exclusiveCount(c)!=0&&owner!=Thread.currentThread()){
                return -1;
            }
            if(compareAndSetState(c, nextc)){
                return 1;
            }
        }
    }
    //锁降级
    public void processData(){
        readLock lock;
        if(!update){
            //必须先释放读锁
            readLock.unlock();
            //锁降级为血锁获取到开始
            writeLock.lock();
            try{
                if(!update){
                    //准备数据
                    update = true;
                }
                readLock.lock();
            }finally {
                writeLock.unlock();
            }
            //锁降级完成，写锁降级为读锁
        }
        try{
            //使用数据
        }finally {
            readLock.unlock();
        }
    }**/
}
