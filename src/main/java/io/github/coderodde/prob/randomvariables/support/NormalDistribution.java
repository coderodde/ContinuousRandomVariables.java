package io.github.coderodde.prob.randomvariables.support;

import io.github.coderodde.prob.randomvariables.ContinuousRandomVariable;
import java.math.BigDecimal;
import java.util.Objects;
import org.apache.commons.math3.special.Erf;

/**
 * This class models the normal distribution.
 */
public class NormalDistribution extends ContinuousRandomVariable {

    private static final double INVERSE_SQRT2 = 1.0 / Math.sqrt(2.0);
    
    private final BigDecimal mu;
    private final BigDecimal sd;
    
    public NormalDistribution(BigDecimal mu,
                              BigDecimal variance) {
        
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
    
    private static BigDecimal phi(BigDecimal z) {
        return BigDecimal
                .valueOf(0.5)
                .multiply(BigDecimal.ONE.add(
                        BigDecimal.valueOf(
                                Erf.erf(z.doubleValue() * INVERSE_SQRT2))));
    }
}
