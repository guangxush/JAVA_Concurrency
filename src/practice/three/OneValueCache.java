package practice.three;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Arrays;

@Immutable
public class OneValueCache {
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger lastNumber, BigInteger[] lastFactors) {
        this.lastNumber = lastNumber;
        this.lastFactors = lastFactors;
    }
    public BigInteger[] getFactors(BigInteger i){
        if(lastNumber==null||!lastNumber.equals(i))
            return null;
        else
            return Arrays.copyOf(lastFactors,lastFactors.length);
    }
}
