
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Calculator {
    final int NUMBER = 2;
    final Operator[] operators;

    public Calculator() {
        operators = new Operator[NUMBER];
        operators[0] = Operator.LCD; operators[1] = Operator.GCD;
    }

    enum Operator {
        LCD {
            long calculate(int num1, int num2) {
                final long gcd = getGCD(num1, num2);
                return gcd * (num1/gcd) * (num2/gcd);
            }
            boolean isOperator(String operator) { return operator.equals("LCD"); }
        }, GCD {
            long calculate(int num1, int num2) { return getGCD(num1, num2); }
            boolean isOperator(String operator) { return operator.equals("GCD"); }
        };

        abstract long calculate(int num1, int num2);
        abstract boolean isOperator(String operator);

        public int commonDivisor(int num1, int num2) {
            int min = Math.min(num1, num2);
            for (int i = 2; i <= min; i++) {
                if (num1 % i == 0 && num2 % i == 0) return i;
            }
            return min;
        }

        public long getGCD(int num1, int num2) {
            long acc = 1;
            int divisor = commonDivisor(num1, num2);
            for (; num1 % divisor == 0 && num2 % divisor == 0 && divisor != 1; num1 = num1 / divisor, num2 = num2 / divisor, divisor = commonDivisor(num1, num2)) {
                acc = acc * divisor;
            }
            return acc;
        }
    }

    public long calculate(String operator, int num1, int num2) {
        if (isOperator(operator) < 0) throw new IllegalArgumentException();
        return operators[isOperator(operator)].calculate(num1, num2);
    }

    private int isOperator(String operator) {
        for (int i = 0; i < operators.length; i++) {
            if (operators[i].isOperator(operator)) return i;
        }
        return -1;
    }
}

public class LCDGCD_2609 {

    static class Result {
        private long lcd;
        private long gcd;

        public String toString() {
            return gcd + "\n" + lcd;
        }
    }

    public static String run(int num1, int num2) {
        Result result = new Result();
        result.gcd = new Calculator().calculate("GCD", num1, num2);
        result.lcd = new Calculator().calculate("LCD", num1, num2);
        return result.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int num1 = Integer.parseInt(tokenizer.nextToken());
        int num2 = Integer.parseInt(tokenizer.nextToken());
        System.out.println(run(num1, num2));
    }
}