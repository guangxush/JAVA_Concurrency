package chapter.six;

import chapter.five.ReentrantLock;

import java.util.concurrent.Delayed;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by gshan on 2018/10/2
 */
public class ArrayBlockingQueue {
    /**
    private static final AtomicLong sequencer = new AtomicLong(0);
    public ArrayBlockingQueue(int capacity, boolean fair){
        if(capacity<0){
            throw new IllegalArgumentException();
        }
        this.items = new Object[capacity];
        lock = new ReentrantLock(fair);
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    public void ScheduledFutureTask(Runnable r, V result, long ns,long period){
        super(r,result);
        this.time = ns;
        this.period = period;
        this.sequencerNumber = sequencer.getAndIncrement();
    }

    public long getDelay(TimeUnit unit){
        return unit.convert(time-now(),TimeUnit.NANOSECONDS);
    }

    public int compareTo(Delayed other){
        if(other==this){
            return 0;
        }
        if(other instanceof ScheduledFutureTask){
            ScheduledFutureTask<?> x = (ScheduledFutureTask<?>) other;
            long diff = time - x.time;
            if(diff<0){
                return -1;
            }else if(diff>0){
                return 1;
            }else if(sequenceNumber<x.sequenceNumber){
                return -1;
            }else{
                return 1;
            }
        }
        long d = (getDelay(TimeUnit.NANOSECONDS)-other.getDelay(TimeUnit.NANOSECONDS));
        return d==0?0:(d<0?-1:1);
    }**/
}
