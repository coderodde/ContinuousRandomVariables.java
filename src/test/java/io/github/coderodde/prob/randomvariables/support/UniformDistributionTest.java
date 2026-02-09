package io.github.coderodde.prob.randomvariables.support;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import org.junit.Test;
import static org.junit.Assert.*;

public class UniformDistributionTest {
    
    private final UniformDistribution dist = new UniformDistribution(2.0, 12.0);
    
    public UniformDistributionTest() {
        dist.setMathContext(new MathContext(10, RoundingMode.HALF_UP));
    }
    
    @Test
    public void getCumulativeMassLess() {
        assertEquals(BigDecimal.valueOf(0.4),
                     dist.getCumulativeMassLess(6.0).setScale(1));
    }
    
    @Test
    public void getCumulativeMassGreater() {
        assertEquals(BigDecimal.valueOf(0.6), 
                     dist.getCumulativeMassGreater(6.0).setScale(1));
    }
}
