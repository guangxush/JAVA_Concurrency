package chapter.five;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by gshan on 2018/10/1
 */
public class QueueSync {
    /**
    public final void acquire(int arg){
        if(!tryAcquire(arg)&&acquireQueued(addWaiter(Node.EXCLUSIVE),arg))
            selfInterrupt();
    }
    private Node addWaiter(Node mode){
        Node node = new Node(Thread.currentThread(), mode);
        //快速尝试在尾部添加
        Node pred = tail;
        if(pred!=null){
            node.prev = pred;
            if(compareAndSetTail(pred, node)){
                pred.next = node;
                return node;
            }
        }
        enq(node);
        return node;
    }
    private Node enq(final Node node){
        for(;;){
            Node t = tail;
            if(t==null){
                //must initialize
                if(compareAndSetHead(new Node())){
                    tail = head;
                }
            }else{
                node.prev = t;
                if(compareAndSetTail(t, node)){
                    t.next = node;
                    return t;
                }
            }
        }
    }
    final boolean acquireQueued(final Node node, int arg){
        boolean failed = true;
        try{
            boolean interrupted = false;
            for(;;){
                final Node p = node.predecessor();
                if(p==head&&tryAcquire(arg)){
                    setHead(node);
                    p.next = null;//help GC
                    failed = false;
                    return interrupted;
                }
                if(shouldParkAfterFailed(p,node)&&parkAndCheckInterrupt())
                    interrupted = true;
            }
        }finally {
            if(failed)
                cancelAcquire(node);
        }
    }
    public final boolean release(int arg){
        if(tryRelease(arg)){
            Node h = head;
            if(h!=null&&h.waitStatus!=0)
                unparkSuccessor(h);
            return true;
        }
        return false;
    }
    public final void acquireShared(int arg){
        if(tryAcquireShared(arg) < 0){
            doAcquireShared(arg);
        }
    }
    private void doAcquireShared(int arg){
        final Node node = addWaiter(Node.SHARED);
        boolean failed = true;
        try{
            boolean interrupted = false;
            for(;;){
                final Node p = node.predcessor();
                if(p == head){
                    int r = tryAcquireShared(arg);
                    if(r>=0){
                        setHeadAndPropagate(node, r);
                        p.next = null;
                        if(interrupted)
                            selfInterrupt();
                        failed = false;
                        return;
                    }
                }
                if(shouldParkAfterFailedAcquire(p, node)&&parkAndCheckInterrupt())
                    interrupted = true;
            }
        }finally {
            if(failed)
                cancelAcquire(node);
        }
    }
    public final boolean releaseShared(int arg){
        if(tryReleaseShared(arg)){
            doReleasedShared();
            return true;
        }
        return false;
    }
    private boolean doAcquireNanos(int arg, long nanosTimeout)throws InterruptedException{
        long lastTime = System.nanoTime();
        final Node node = addWaiter(Node.EXCLUSIVE);
        boolean failed = true;
        try{
            for(;;){
                final Node p = node.predecessor();
                if(p==head&&tryAcquire(arg)){
                    setHead(node);
                }
                p.next = null;//help GC
                failed = false;
                return true;
            }
            if(nanosTimeout <= 0){
                return false;
            }
            if(shouldParkAfterFailedAcquire(p,node)&&nanosTimeout>spinForTimeoutThreshold)
                LockSupport.parkNanos(this,nanosTimeout);
            long now = System.nanoTime();
            //计算时间，当前时间now减去睡眠之前的时间lasttime得到已经睡眠的时间delta
            //然后被原有超时的时间nanosTimeout减去，得到还应该睡眠的时间
            nanosTimeout -= now -lastTime;
            lastTime = out;
            if(Thread.interrupted())
                throw new InterruptedException();
        }finally {
            if(failed)
                cancelAcquire(node);
        }
    }
     **/
}
