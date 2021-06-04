import java.io.*;
import java.util.Scanner;

public class BaseConversion_11576 {

    static class Number {
        int[] digits;
        int numSystem;
        int length;

        Number(int numSystem, int[] digits, int length) {
            this.numSystem = numSystem;
            this.digits = digits;
            this.length = length;
        }

        int toTen() {
            return _toTen(0);
        }

        int _toTen(int index) {
            int last = length - 1 - index;
            if (last == 0) return digits[last] * (int)Math.pow(numSystem, index);
            return _toTen(index + 1) + digits[last] * (int)Math.pow(numSystem, index);
        }

        void toNS(int tenNum) {
            length = 1;
            int tmp = tenNum;
            while (tmp >= numSystem) { length++; tmp /= numSystem; }
            digits = new int[length];
            _toNS(tenNum, 0);
        }

        void _toNS(int tenNum, int index) {
            int last = length - 1 - index;
            if (tenNum < numSystem) {
                digits[last] = tenNum;
            } else {
                digits[last] = tenNum % numSystem;
                _toNS(tenNum / numSystem, index + 1);
            }
        }

        public String toString() {
            String result = "";
            for (int i = 0; i < length; i++) {
                String tmp = digits[i] + " ";
                result += tmp;
            }
            return result;
        }
    }

    public String run() {
        Scanner sc = new Scanner(System.in);
        int aNS = sc.nextInt();
        int bNS = sc.nextInt();
        int aLength = sc.nextInt();
        int[] aDigits = new int[aLength];

        for (int i = 0; i < aLength; i++) {
            aDigits[i] = sc.nextInt();
        }
        Number input = new Number(aNS, aDigits, aLength);
        Number result = new Number(bNS, null, 0);
        _run(input, result);
        return  result.toString();
    }

    public String _run(Number input, Number result) {
        result.toNS(input.toTen());
        return result.toString();
    }

    public static void main(String[] args) throws IOException {
        BaseConversion_11576 m = new BaseConversion_11576();
        System.out.println(m.run());
    }
}
