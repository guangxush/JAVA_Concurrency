package practice.three;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * 线程安全的可变整数访问器
 */
@ThreadSafe
public class SynchronizedInteger {
    @GuardedBy("this")
    private int value;

    public synchronized int get(){
        return value;
    }
    public synchronized void set(int value){
        this.value = value;
    }
}
