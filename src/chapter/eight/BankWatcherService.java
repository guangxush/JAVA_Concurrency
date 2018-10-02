package chapter.eight;

import chapter.four.Interrupted;

import java.lang.management.ThreadInfo;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by gshan on 2018/10/2
 */
public class BankWatcherService implements Runnable{

    /**
     * 创建四个屏障，处理完之后执行当前类的run方法
     */
    private CyclicBarrier c = new CyclicBarrier(4,this);

    /**
     * 假设有4个sheet,所以只启动四个线程
     */
    private Executor executors = Executors.newFixedThreadPool(4);

    /**
     * 保存每个sheet计算出的结果
     */
    private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<>();

    private void count(){
        for(int i=0;i<4;i++){
            executors.execute(new Runnable() {
                @Override
                public void run() {
                    //计算当前的银行数据
                    sheetBankWaterCount.put(Thread.currentThread().getName(),1);
                    //银行数据计算完成，插入一个屏障
                    try{
                        c.await();
                    }catch (InterruptedException | BrokenBarrierException e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    @Override
    public void run() {
        int result = 0;
        //汇总每个sheet的计算结果
        for(Map.Entry<String,Integer>sheet : sheetBankWaterCount.entrySet()){
            result += sheet.getValue();
        }
        //将结果输出
        sheetBankWaterCount.put("result",result);
        System.out.println(result);
    }

    public static void main(String[] args) {
        BankWatcherService bankWatcherService = new BankWatcherService();
        bankWatcherService.count();
    }
}
