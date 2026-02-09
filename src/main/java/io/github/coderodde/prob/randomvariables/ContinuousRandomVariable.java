package io.github.coderodde.prob.randomvariables;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

/**
 * This abstract class defines the API for the continuous random variables.
 */
public abstract class ContinuousRandomVariable {

    protected MathContext mathContext;
    
    public abstract BigDecimal getCumulativeMass(Selector s, BigDecimal z);
    
    public MathContext getMathContext() {
        return mathContext;
    }
    
    public void setMathContext(MathContext mathContext) {
        this.mathContext = 
                Objects.requireNonNull(
                        mathContext, 
                        "The input math context is null.");
    }
    
    public BigDecimal getCumulativeMassLess(BigDecimal z) {
        return getCumulativeMass(Selector.LESS, z);
    }
    
    public BigDecimal getCumulativeMassGreater(BigDecimal z) {
        return getCumulativeMass(Selector.GREATER, z);
    }
    
    public BigDecimal getCumulativeMass(BigDecimal z1, BigDecimal z2) {
        checkZs(z1, z2);
        
        return getCumulativeMassLess(z2).subtract(getCumulativeMassLess(z2));
    }

    public BigDecimal getCumulativeMass(Selector s, double dz) {
        return getCumulativeMass(s, BigDecimal.valueOf(dz));
    }
    
    public BigDecimal getCumulativeMassLess(double z) {
        return getCumulativeMass(Selector.LESS, BigDecimal.valueOf(z));
    }
    
    public BigDecimal getCumulativeMassGreater(double z) {
        return getCumulativeMass(Selector.GREATER, BigDecimal.valueOf(z));
    }
    
    public BigDecimal getCumulativeMass(double dz1, double dz2) {
        BigDecimal z1 = BigDecimal.valueOf(dz1);
        BigDecimal z2 = BigDecimal.valueOf(dz2);
        
        checkZs(z1, z2);
        
        return getCumulativeMassLess(z2).subtract(getCumulativeMassLess(z2));
    }
    
    protected static void checkZs(BigDecimal z1, BigDecimal z2) {
        if (z1.compareTo(z2) > 0) {
            throw new IllegalArgumentException(
                    String.format("z1(%s) > z2(%s)", z1, z2));
        }
    }
}
