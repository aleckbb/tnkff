package edu.hw2.Task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")

public class Task1 {

    private static final int FOUR = 4;

    private static final Logger LOGGER = LogManager.getLogger();

    private Task1() {

    }

    public static void main(String[] args) {
        var two = new Expr.Constant(2);
        var four = new Expr.Constant(FOUR);
        var negOne = new Expr.Negate(new Expr.Constant(1));
        var sumTwoFour = new Expr.Addition(two, four);
        var mult = new Expr.Multiplication(sumTwoFour, negOne);
        var exp = new Expr.Exponent(mult, 2);
        var res = new Expr.Addition(exp, new Expr.Constant(1));
        LOGGER.info(res + " = " + res.evaluate());
    }
}
