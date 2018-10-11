package practice.two;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

public class CountingFactorizer implements Servlet{
    private final AtomicLong count = new AtomicLong(0);
    public long getCount(){return count.get();}
    public void service(ServletRequest req, ServletResponse resp){
        BigInteger i = extractFromRequest(req);
        count.incrementAndGet();
        encodeIntoResponse(resp, factors);
    }
}
