package practice.two;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

public class CountingFactorizer implements Servlet {
    private final AtomicLong count = new AtomicLong(0);
    public long getCount(){return count.get();}
    public void service(ServletRequest req, ServletResponse resp){
        BigInteger i = extractFromRequest(req);
        count.incrementAndGet();
        encodeIntoResponse(resp, factors);
    }
}
