package practice.two;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;

public class CachedFactorizer implements Servlet {

    @GuardedBy("this")
    private BigInteger lastNumber;

    @GuardedBy("this")
    private BigInteger[] lastFactors;

    @GuardedBy("this")
    private long hits;

    @GuardedBy("this")
    private long cacheHits;

    public synchronized long getHits(){return hits;}

    public synchronized double getCacheHitRatio(){
        return (double) cacheHits/(double) hits;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = null;

        synchronized (this){
            ++hits;
            if(i.equals(lastNumber)){
                ++cacheHits;
                factors = lastFactors.clone();
            }
        }
        if(factors==null){
            factors = factors(i);
            synchronized (this){
                lastNumber = i;
                lastFactors = factors.clone();
            }
        }
        encodeIntoResponse(resp, factors);
    }
}
