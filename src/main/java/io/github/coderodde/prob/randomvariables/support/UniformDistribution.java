package io.github.coderodde.prob.randomvariables.support;

import io.github.coderodde.prob.randomvariables.ContinuousRandomVariable;
import io.github.coderodde.prob.randomvariables.Selector;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * This class models the uniform distribution.
 */
public class UniformDistribution extends ContinuousRandomVariable {

    private static final MathContext DEFAULT_MATH_CONTEXT = 
                     new MathContext(10, RoundingMode.HALF_UP);
    
    private final BigDecimal a;
    private final BigDecimal b;
    private final BigDecimal p;
    
    public UniformDistribution(BigDecimal a, BigDecimal b) {
        this.a = Objects.requireNonNull(a, "The lower bound a is null.");
        this.b = Objects.requireNonNull(b, "The upper bound b is null.");
        
        checkAB(a, b);
        setMathContext(DEFAULT_MATH_CONTEXT);
        
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
    public BigDecimal getCumulativeMass(Selector s, BigDecimal z) {
        switch (s) {
            case LESS -> {
                return p.multiply(z.subtract(a));
            }
                
            case GREATER -> {
                return p.multiply(b.subtract(z));
            }
                
            default -> 
                throw new IllegalStateException("Unknown Selector enum: " + s);
        }
    }

    private static void checkAB(BigDecimal a, BigDecimal b) {
        if (a.compareTo(b) >= 0) {
            throw new IllegalArgumentException(
                    String.format("a(%s) >= b(%s).", a, b));
        }
    }
}
