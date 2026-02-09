package io.github.coderodde.prob.randomvariables.support;

import io.github.coderodde.prob.randomvariables.ContinuousRandomVariable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * This class models the uniform distribution.
 */
public class UniformDistribution extends ContinuousRandomVariable {
    
    private final BigDecimal a;
    private final BigDecimal b;
    private final BigDecimal p;
    
    public UniformDistribution(BigDecimal a, BigDecimal b) {
        this.a = Objects.requireNonNull(a, "The lower bound a is null.");
        this.b = Objects.requireNonNull(b, "The upper bound b is null.");
        
        checkAB(a, b);
        
        p = BigDecimal.ONE.divide(b.subtract(a), mathContext);
    }
    
    public UniformDistribution(double a, double b) {
        this(BigDecimal.valueOf(a),
             BigDecimal.valueOf(b));
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public BigDecimal cdfLessThan(BigDecimal z) {
        return p.multiply(z.subtract(a));
    }

    private static void checkAB(BigDecimal a, BigDecimal b) {
        if (a.compareTo(b) >= 0) {
            throw new IllegalArgumentException(
                    String.format("a(%s) >= b(%s).", a, b));
        }
    }
}
