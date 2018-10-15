package practice.two;

import net.jcip.annotations.GuardedBy;
import org.junit.runner.notification.RunListener;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;

@RunListener.ThreadSafe
public class SynchronizedFactorizer implements Servlet{
    @GuardedBy("this")
    private BigInteger lastNumber;

    @GuardedBy("this")
    private BigInteger[] lastFactors;

    public synchronized void service(ServletRequest req, ServletResponse resp){
        BigInteger i = extractFromRequest(req);
        if(i.equals(lastNumber))
            encodeIntoResponse(resp, lastFactors);
        else{
            BigInteger[] factors = factor(i);
            lastNumber = i;
            lastFactors = factors;
            encodeIntoResponse(resp, factors);
        }
    }
}
