package practice.three;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;

public class VolatileCachedFactorizer implements Servlet {

    private volatile OneValueCache cache = new OneValueCache(null,null);

    public void service(ServletRequest req, ServletResponse resp){
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = cache.getFactors(i);
        if(factors==null){
            factors = factor(i);
            cache = new OneValueCache(i,factors);
        }
        encodeIntoResponse(resp, factors);
    }
}
