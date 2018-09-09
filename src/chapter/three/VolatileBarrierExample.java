package chapter.three;

/**
 * Created by gshan on 2018/9/9
 */
public class VolatileBarrierExample {

    int a;
    volatile int v1 = 1;
    volatile int v2 = 2;

    void readAndWrite(){
        int i = v1;//第一个volatile读
        //loadload屏障
        int j = v2;//第二个volatile读
        //loadstore屏障
        a = i+j;//普通写
        //storestore屏障
        v1 = i+1;//第一个volatile写
        //storestore屏障
        v2 = j*2;//第二个volatile写
        //storeload屏障
    }
}
