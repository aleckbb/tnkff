package edu.hw2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")

public class Task1 {

    private final static int FOUR = 4;

    private final static Logger LOGGER = LogManager.getLogger();

    private Task1(){

    }

    public sealed interface Expr {
        double evaluate();

        public record Constant(double value) implements Expr {
            public double evaluate() {
                return value;
            }
        }

        public record Negate(Expr negate) implements Expr {
            public double evaluate() {
                return negate.evaluate() == 0 ? 0 : (-1) * negate.evaluate();
            }
        }

        public record Exponent(Expr value, int degree) implements Expr {
            public double evaluate() {
                return Math.pow(value.evaluate(), degree);
            }
        }

        public record Addition(Expr first, Expr second) implements Expr {
            public double evaluate() {
                return first.evaluate() + second.evaluate();
            }
        }

        public record Multiplication(Expr first, Expr second) implements Expr {
            public double evaluate() {
                return first.evaluate() * second.evaluate();
            }
        }
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
