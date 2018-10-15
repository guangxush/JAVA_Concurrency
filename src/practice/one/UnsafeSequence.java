package practice.one;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class UnsafeSequence {
    private int value;
    /**
     * 返回一个唯一的值
     */
    public int getNext(){
        return value++;
    }
}
