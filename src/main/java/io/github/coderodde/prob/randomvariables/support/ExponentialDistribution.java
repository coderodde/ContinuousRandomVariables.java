package io.github.coderodde.prob.randomvariables.support;

import io.github.coderodde.prob.randomvariables.ContinuousRandomVariable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * This class implements the exponential distribution.
 */
public class ExponentialDistribution extends ContinuousRandomVariable {

    private static final BigDecimal E = BigDecimal.valueOf(Math.E);
    
    private final BigDecimal lambda;
    
    public ExponentialDistribution(BigDecimal lambda) {
        this.lambda = checkLambda(lambda);
    }
    
    @Override
    public BigDecimal cdfLessThan(BigDecimal x) {
        if (x.compareTo(BigDecimal.ZERO) < 0) {
            return BigDecimal.ZERO;
        }
        
        double exponent = 
                Math.pow(Math.E, 
                         -lambda.doubleValue() * x.doubleValue());
        
        BigDecimal exponentBigDecimal = BigDecimal.valueOf(exponent);
        return lambda.multiply(exponentBigDecimal);
    }

    private static BigDecimal checkLambda(BigDecimal lambda) {
        Objects.requireNonNull(lambda, "The lambda parameter is null");
        
        if (lambda.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                String.format("Lambda (%s) must be positive.", lambda));
        }
        
        return lambda;
    }
}
