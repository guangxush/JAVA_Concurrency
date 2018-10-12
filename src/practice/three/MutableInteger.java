package practice.three;

/**
 * 非线程安全的可变整数访问器
 */
public class MutableInteger {
    private int value;
    public int get(){
        return value;
    }
    public void set(int value){
        this.value = value;
    }
}
