package chapter.three;

/**
 * Created by gshan on 2018/9/14
 */
public class DoubleCheckedLocking {
    private static DoubleCheckedLocking instance;

    public static DoubleCheckedLocking getInstance(){
        if(instance==null){   //第一次检查
            synchronized (DoubleCheckedLocking.class){//加锁
                if(instance==null){//第二次检查
                    instance = new DoubleCheckedLocking();//问题的根源
                    //memory = allocate();
                    //instance  = memory;
                    //ctorInstance(memory);
                }
            }
        }
        return instance;
    }
}
