package chapter.three;

/**
 * Created by gshan on 2018/9/9
 */
public class VolatileFeaturesExample {

    volatile long v1 = 0L;//使用volatile声明变量

    public void set(long v1){//单个volatile变量的写
        v1 = v1;
    }

    public void getAndIncrement(){//单个volatile的读/写
        v1++;
    }

    public long get(){//单个volatile变量的读
        return v1;
    }
}
//等价类
class VolatileFeaturesExample1 {

    long v1 = 0L;//普通变量

    public synchronized void set(long v1){//普通变量的写
        v1 = v1;
    }

    public void getAndIncrement(){//普通方法的调用
        long temp = get();//调用已同步的读方法
        temp += 1L;//普通写操作
        set(temp);//调用已同步的写方法
    }

    public synchronized long get(){//普通变量的读用同一个锁同步
        return v1;
    }
}
