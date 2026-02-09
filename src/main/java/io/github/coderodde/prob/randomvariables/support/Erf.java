package io.github.coderodde.prob.randomvariables.support;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * This class implements the {@code erf} function for the {@link BigDecimal}.
 */
public class Erf {

    private static final BigDecimal TWO =
            BigDecimal.valueOf(2);

    private static final BigDecimal SQRT_PI =
            new BigDecimal("1.7724538509055160272981674833411451827975");

    private Erf() {}

    public static BigDecimal erf(BigDecimal x, MathContext mc) {
        if (x.signum() == 0) {
            return BigDecimal.ZERO;
        }

        // erf(-x) = -erf(x)
        if (x.signum() < 0) {
            return erf(x.negate(), mc).negate();
        }

        BigDecimal sum  = BigDecimal.ZERO;
        BigDecimal term = x;
        BigDecimal x2   = x.multiply(x, mc);

        BigDecimal factorial = BigDecimal.ONE;
        BigDecimal sign      = BigDecimal.ONE;

        int n = 0;

        while (true) {
            BigDecimal denom =
                    factorial.multiply(
                            BigDecimal.valueOf(2L * n + 1), mc);

            BigDecimal add =
                    sign.multiply(term, mc).divide(denom, mc);

            sum = sum.add(add, mc);

            // Stop when term is smaller than precision target
            if (add.abs().compareTo(
                    BigDecimal.ONE.movePointLeft(mc.getPrecision())) < 0) {
                break;
            }

            n++;
            factorial = factorial.multiply(
                    BigDecimal.valueOf(n), mc);

            term = term.multiply(x2, mc);
            sign = sign.negate();
        }

        return TWO.divide(SQRT_PI, mc).multiply(sum, mc);
    }
}
