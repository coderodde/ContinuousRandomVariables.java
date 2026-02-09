package io.github.coderodde.prob.randomvariables.support;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.Test;
import static org.junit.Assert.*;

public class NormalDistributionTest {

    private final NormalDistribution dist = 
              new NormalDistribution(100.0, Math.pow(24.0, 2.0));
    
    @Test
    public void testCdfLessThan() {
        assertEquals(
                BigDecimal.valueOf(0.5), 
                dist.cdfLessThan(BigDecimal.valueOf(100.0)).setScale(1));
        
        assertEquals(
                BigDecimal.valueOf(0.5),
                dist.cdfGreaterThan(BigDecimal.valueOf(100.0)).setScale(1));
        
        assertEquals(
                BigDecimal.valueOf(0.5),
                dist.cdfGreaterThan(BigDecimal.valueOf(100.0)).setScale(1));
        
        assertEquals(
                BigDecimal.valueOf(0.0002),
                dist.cdfLessThan(BigDecimal.valueOf(15.0))
                        .setScale(5, RoundingMode.HALF_UP));
        assertEquals(
                BigDecimal.valueOf(0.0002),
                dist.cdfGreaterThan(BigDecimal.valueOf(185.0))
                        .setScale(5, RoundingMode.HALF_UP));
        
        assertEquals(BigDecimal.valueOf(0.682689),
                dist.getProbabilityBetween(BigDecimal.valueOf(76.0),
                                           BigDecimal.valueOf(124.0))
                        .setScale(6, RoundingMode.HALF_UP));
    }
}
