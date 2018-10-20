package practice.seven;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class CheckForMail {
    private Set<String> hosts;
    private long timeout;
    private TimeUnit unit;

    public boolean checkMail(Set<String> hosts, long timeout, TimeUnit unit)
            throws InterruptedException {
        this.hosts = hosts;
        this.timeout = timeout;
        this.unit = unit;
        ExecutorService exec = Executors.newCachedThreadPool();
        final AtomicBoolean hasNewMail = new AtomicBoolean(false);
        try {
            for (final String host : hosts)
                exec.execute(new Runnable() {
                    public void run() {
                        if (checkMail(host))
                            hasNewMail.set(true);
                    }
                });
        } finally {
            exec.shutdown();
            exec.awaitTermination(timeout, unit);
        }
        return hasNewMail.get();
    }

    private boolean checkMail(String host) {
        // Check for mail
        return false;
    }
}
