package edu.hw2.Task1;

public sealed interface Expr {
    double evaluate();

    record Constant(double value) implements Expr {
        public double evaluate() {
            return value;
        }
    }

    record Negate(Expr negate) implements Expr {
        public double evaluate() {
            return negate.evaluate() == 0 ? 0 : (-1) * negate.evaluate();
        }
    }

    record Exponent(Expr value, int degree) implements Expr {
        public double evaluate() {
            return Math.pow(value.evaluate(), degree);
        }
    }

    record Addition(Expr first, Expr second) implements Expr {
        public double evaluate() {
            return first.evaluate() + second.evaluate();
        }
    }

    record Multiplication(Expr first, Expr second) implements Expr {
        public double evaluate() {
            return first.evaluate() * second.evaluate();
        }
    }
}
