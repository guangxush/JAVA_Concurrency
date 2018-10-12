package practice.three;

import org.junit.runner.notification.RunListener;

/**
 * 线程安全的可变整数访问器
 */
@RunListener.ThreadSafe
public class SynchronizedInteger {
    @GuardeBy("this")
    private int value;

    public synchronized int get(){
        return value;
    }
    public synchronized void set(int value){
        this.value = value;
    }
}
