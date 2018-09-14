package chapter.three;

/**
 * Created by gshan on 2018/9/14
 */
public class FinalReferencesExample {
    final int[] intArray; //final是引用类型
    static FinalReferencesExample obj;
    public FinalReferencesExample(){//构造函数
        intArray = new  int[1];    //1
        intArray[0] = 1;           //2
    }

    public static void writeOne(){ //写线程A执行
        obj = new FinalReferencesExample(); //3
    }

    public static void writeTwo(){ //写线程B执行
        obj.intArray[0] = 2;        //4
    }

    public static void reader(){ //读线程C执行
        if(obj != null){         //5
            int temp1 = obj.intArray[0]; //6
        }
    }
}
