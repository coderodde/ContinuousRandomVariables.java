package io.github.coderodde.prob.randomvariables.support;

import io.github.coderodde.prob.randomvariables.ContinuousRandomVariable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

/**
 * This class models the normal distribution.
 */
public class NormalDistribution extends ContinuousRandomVariable {

    private static BigDecimal INVERSE_SQRT2 = null;
    
    private final BigDecimal mu;
    private final BigDecimal sd;
    
    public NormalDistribution(BigDecimal mu,
                              BigDecimal variance) {
        super();
        
        this.mu = Objects.requireNonNull(mu, "The expected value is null.");
        this.sd = checkVariance(variance);
    }
    
    public NormalDistribution(double mu,
                              double variance) {
        this(BigDecimal.valueOf(mu), 
             BigDecimal.valueOf(variance));
    }

    @Override
    public BigDecimal cdfLessThan(BigDecimal x) {
        BigDecimal z = x.subtract(mu).divide(sd, mathContext);
            
        return phi(z);
    }
    
    private BigDecimal checkVariance(BigDecimal variance) {
        Objects.requireNonNull(variance, "The input variance is null.");
        
        if (variance.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                String.format("Variance (%s) is too small. Must be posiitve.",
                              variance));
        }
        
        return variance.sqrt(mathContext);
    }
    
    private BigDecimal phi(BigDecimal z) {
        if (INVERSE_SQRT2 == null) {
            INVERSE_SQRT2 = inverseSqrt2(mathContext);
        }
        
        return BigDecimal
                .valueOf(0.5)
                .multiply(BigDecimal.ONE.add(Erf.erf(z.multiply(INVERSE_SQRT2), 
                          getMathContext())));
    }
    
    private static BigDecimal inverseSqrt2(MathContext mc) {
        return BigDecimal.ONE
                .divide(BigDecimal.valueOf(2).sqrt(mc), mc);
    }
}
