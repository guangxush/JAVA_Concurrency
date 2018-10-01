package chapter.five;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by gshan on 2018/10/1
 */
public class ConditionObject {
    /**
    public final void await() throws InterruptedException{
        if (Thread.interrupted())
            throw new InterruptedException();
        //当前线程加入等待队列
        Node node = addConditionWaiter();
        //释放同步状态，也就是释放锁
        int savedState = fullyRelease(node);
        int interruptMode = 0;
        while(!isOnSyncQueue(node)){
            LockSupport.park(this);
            if((interruptMode=checkInterruptWhileWaiting(node))!=0){
                break;
            }
        }
        if(acquireQueued(node,savedState)&&interruptMode!=THROW_IE)
            interruptMode = REINTERRUPT;
        if(node.nextWaiter!=null)
            unlinkCancelledWaiters();
        if(interruptMode!=0)
            reportInterruptAfterWait(interruptMode);
    }
    public final void signal(){
        if(!isHelpExclusively()){
            throw new IllegalMonitorStateException();
        }
        Node first = firstWaiter;
        if(first!=null)
            doSingal(first);
    }**/
}
