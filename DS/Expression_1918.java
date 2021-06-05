import java.util.Scanner;
import java.util.Stack;

class Operator {
    private final char operator;
    private final int priority;

    private final Handler handler;

    Operator(char operator, int priority, Handler handler) {
        this.operator = operator; this.priority = priority;
        this.handler = handler;
    }
    boolean isOperator(char operator) { return this.operator == operator; }
    boolean isHighAndSamePriority(Operator operator) { return priority <= operator.priority; }
    char toChar() { return operator; }

    StringBuilder handle(Stack<Operator> opStack) {
        return handler.handle(this, opStack);
    }
}

interface Handler {
    StringBuilder handle(Operator op, Stack<Operator> opStack);
}

class CommonOperator extends Operator {
    CommonOperator(char operator, int priority) {
        super(operator, priority, (op, opStack) -> {
            StringBuilder result = new StringBuilder();
            while (!opStack.empty()) {
                if (opStack.peek().isHighAndSamePriority(op) && !opStack.peek().isOperator('(')) {
                    result.append(opStack.pop().toChar());
                } else break;
            }
            opStack.push(op);
            return result;
        });
    }
}

class FPOperator extends Operator {
    FPOperator(char operator, int priority) {
        super(operator, priority, (op, opStack) -> {
            StringBuilder result = new StringBuilder();
            opStack.push(op);
            return result;
        });
    }
}

class BPOperator extends Operator {
    BPOperator(char operator, int priority) {
        super(operator, priority, (op, opStack) -> {
            StringBuilder result = new StringBuilder();
            while (!opStack.empty()) {
                if (!opStack.peek().isOperator('(')) {
                    result.append(opStack.pop().toChar());
                } else break;
            }
            opStack.pop();
            return result;
        });
    }
}

class Operators {
    private final Operator[] operators;
    private final int NUMBER = 6;

    public Operators() {
        this.operators = new Operator[NUMBER];
        operators[0] = new CommonOperator('+', 3); operators[1] = new CommonOperator('-', 3);
        operators[2] = new CommonOperator('*', 2); operators[3] = new CommonOperator('/', 2);
        operators[4] = new FPOperator('(', 1); operators[5] = new BPOperator(')', 1);
    }

    boolean isOperator(char operator) {
        return _isOperator(operator) > -1;
    }

    private int _isOperator(char operator) {
        for (int i = 0; i < NUMBER; i++) {
            if (operators[i].isOperator(operator))
                return i;
        }
        return -1;
    }

    public StringBuilder handle(char operator, Stack<Operator> opStack) {
        return operators[_isOperator(operator)].handle(opStack);
    }
}

class Converter {
    static boolean isOperator(char operator) {
        return new Operators().isOperator(operator);
    }

    public static StringBuilder handleOperator(char operator, Stack<Operator> opStack) {
        return new Operators().handle(operator, opStack);
    }
}

public class Expression_1918 {

    public static String _run(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Operator> opStack = new Stack<>();
        for (char ch : expression.toCharArray()) {
            if (Character.isAlphabetic(ch)) {
                result.append(ch);
            } else if (Converter.isOperator(ch)) {
                result.append(Converter.handleOperator(ch, opStack));
            } else {
                throw new RuntimeException();
            }
        }
        while (!opStack.empty()) {
            result.append(opStack.pop().toChar());
        }
        return result.toString();
    }

    public static String run() {
        Scanner sc = new Scanner(System.in);
        String expression = sc.nextLine();
        return _run(expression);
    }

    public static void main(String[] args) {
        System.out.println(Expression_1918.run());
    }
}
