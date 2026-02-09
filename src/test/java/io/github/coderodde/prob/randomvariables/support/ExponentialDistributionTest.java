package io.github.coderodde.prob.randomvariables.support;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExponentialDistributionTest {

    private ExponentialDistribution dist;
    
    @Test
    public void cdfLessThan() {
        BigDecimal lambda = BigDecimal.valueOf(2.0);
        dist = new ExponentialDistribution(lambda);
        
        assertEquals(lambda, dist.cdfLessThan(BigDecimal.ZERO).setScale(1));
        assertEquals(BigDecimal.ZERO.setScale(5), 
                     dist.cdfLessThan(BigDecimal.ONE.negate())
                             .setScale(5, RoundingMode.HALF_UP));
        
        assertEquals(
                BigDecimal.valueOf(0.27067), 
                dist.cdfLessThan(BigDecimal.ONE)
                        .setScale(5, RoundingMode.HALF_UP));
        
        assertEquals(
                BigDecimal.valueOf(0.72933).setScale(5), 
                dist.cdfGreaterThan(BigDecimal.ONE)
                        .setScale(5, RoundingMode.HALF_UP));
    }
}
