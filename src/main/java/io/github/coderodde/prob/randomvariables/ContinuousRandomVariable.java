package io.github.coderodde.prob.randomvariables;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * This abstract class defines the API for the continuous random variables.
 */
public abstract class ContinuousRandomVariable {

    private static final MathContext DEFAULT_MATH_CONTEXT = 
                     new MathContext(10, RoundingMode.HALF_UP);
    
    protected MathContext mathContext;
    
    public ContinuousRandomVariable() {
        this.mathContext = DEFAULT_MATH_CONTEXT;
    }
    
    public abstract BigDecimal cdfLessThan(BigDecimal x);
    
    public BigDecimal cdfGreaterThan(BigDecimal x) {
        return BigDecimal.ONE.subtract(cdfLessThan(x));
    }
    
    public MathContext getMathContext() {
        return mathContext;
    }
    
    public void setMathContext(MathContext mathContext) {
        this.mathContext = 
                Objects.requireNonNull(
                        mathContext, 
                        "The input math context is null.");
    }
    
    public BigDecimal getProbabilityBetween(BigDecimal x1, BigDecimal x2) {
        checkZs(x1, x2);
        
        return cdfLessThan(x2).subtract(cdfLessThan(x1));
    }

    public BigDecimal cdfLessThan(double x) {
        return cdfLessThan(BigDecimal.valueOf(x));
    }
    
    public BigDecimal cdfGreaterThan(double x) {
        return cdfGreaterThan(BigDecimal.valueOf(x));
    }
    
    public BigDecimal getCumulativeMass(double x1, double x2) {
        BigDecimal z1 = BigDecimal.valueOf(x1);
        BigDecimal z2 = BigDecimal.valueOf(x2);
        
        checkZs(z1, z2);
        
        return cdfLessThan(z2).subtract(cdfLessThan(z2));
    }
    
    protected static void checkZs(BigDecimal x1, BigDecimal x2) {
        if (x1.compareTo(x2) > 0) {
            throw new IllegalArgumentException(
                    String.format("x1(%s) > x2(%s)", x1, x2));
        }
    }
}
