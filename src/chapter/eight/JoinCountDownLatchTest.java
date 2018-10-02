package chapter.eight;

/**
 * Created by gshan on 2018/10/2
 */
public class JoinCountDownLatchTest{
    public static void main(String[] args) throws InterruptedException{
        Thread parser1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("parser1 finished");
            }
        });
        Thread parser2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("parser2 finished");
            }
        });
        parser1.start();
        parser2.start();
        parser1.join();
        parser2.join();
        System.out.println("all parser finished");
    }
}
