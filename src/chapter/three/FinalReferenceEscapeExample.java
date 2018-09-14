package chapter.three;

/**
 * Created by gshan on 2018/9/14
 */
public class FinalReferenceEscapeExample {

    final int i; //final是引用类型
    static FinalReferenceEscapeExample obj;
    public FinalReferenceEscapeExample(){//构造函数
        i = 1;    //1 写final域
        obj = this;           //2 this引用在此溢出
    }

    public static void writer(){ //写线程执行
        obj = new FinalReferenceEscapeExample();
    }

    public static void reader(){ //读线程执行
        if(obj != null){         //3
            int temp1 = obj.i; //4
        }
    }
}
