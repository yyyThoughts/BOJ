import java.util.Scanner;
import java.util.Stack;

class Operators {
    private final OPERATOR[] operators;
    private final int NUMBER = 4;

    enum OPERATOR {
        PLUS {
            double calculate(double operand1, double operand2) { return operand1 + operand2; }
            boolean isOperator(String operator) { return operator.equals("+"); }
        },
        MINUS {
            double calculate(double operand1, double operand2) { return operand1 - operand2; }
            boolean isOperator(String operator) { return operator.equals("-"); }
        },
        MULTIPLY {
            double calculate(double operand1, double operand2) { return operand1 * operand2; }
            boolean isOperator(String operator) { return operator.equals("*"); }
        },
        DIVIDE {
            double calculate(double operand1, double operand2) { return operand1 / operand2; }
            boolean isOperator(String operator) { return operator.equals("/"); }
        };

        abstract double calculate(double operand1, double operand2);
        abstract boolean isOperator(String operator);
    }

    Operators() {
        operators = new OPERATOR[NUMBER];
        operators[0] = OPERATOR.PLUS; operators[1] = OPERATOR.MINUS;
        operators[2] = OPERATOR.MULTIPLY; operators[3] = OPERATOR.DIVIDE;
    }

    OPERATOR getInstance(String operator) {
        final int index = isOperator(operator);
        if (index != -1) return operators[index];
        throw new RuntimeException();
    }

    int isOperator(String operator) {
        for (int i = 0; i < NUMBER; i++) {
            if (operators[i].isOperator(operator)) return i;
        }
        return -1;
    }
}

class Calculator {
    public static double calculate(double operand1, double operand2, String operator) {
        return new Operators().getInstance(operator).calculate(operand1, operand2);
    }

    public static boolean isOperator(String operator) {
        return new Operators().isOperator(operator) > -1;
    }
}

public class Expression_1935 {
    public static double _run(String expression, int[] values) {
        Stack<Double> stack = new Stack<>();
        for (char ch : expression.toCharArray()) {
            if (Character.isAlphabetic(ch)) {
                stack.push((double)values[ch]);
            } else if (Calculator.isOperator(String.valueOf(ch))) {
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                stack.push(Calculator.calculate(operand1, operand2, String.valueOf(ch)));
            } else {
                throw new RuntimeException();
            }
        }
        return stack.pop();
    }

    public static String run() {
        Scanner sc = new Scanner(System.in);
        int numOfVar = Integer.parseInt(sc.nextLine());
        String str = sc.nextLine();
        int[] values = new int['Z' + 1];
        char ch = 'A';
        for (int i = 0; i < numOfVar; i++) {
            values[ch++] = Integer.parseInt(sc.nextLine());
        }
        return String.format("%.2f", Math.round(_run(str, values) * 100.0) / 100.0);
    }

    public static void main(String[] args) {
        System.out.println(Expression_1935.run());
    }
}