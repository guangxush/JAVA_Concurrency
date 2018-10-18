package practice.six;

public class ThreadPerTaskExecutor {
    public void execute(Runnable r) {
        new Thread(r).start();
    };
}
