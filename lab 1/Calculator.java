import java.util.Objects;
import java.util.Scanner;
import java.io.InputStream;
import java.io.PrintStream;

public class Calculator {
    Scanner in;
    String math;
    PrintStream printStream;
   public String[] subStr;
    public String[] getSubStr() {
        return subStr;
    }

    public void setSubStr(String[] subStr) {
        this.subStr = subStr;
    }

    public Calculator (InputStream inputStream, PrintStream printStream) {
        this.in = new Scanner(inputStream);
        this.printStream = printStream;
    }

    public void scan() {
        System.out.print("Enter a math operation: ");
        this.math = in.nextLine();
        System.out.println("Your math operation is: " + math);
    }

    public void parse() {
        this.subStr = this.math.split("\\s");
        for(int i = 0; i < this.subStr.length; i++) {
            System.out.println(this.subStr[i]);
        }
    }

    int m = 0;
    int n = 0;
    public int CalculatorState() {
        m = Integer.parseInt(this.subStr[0].trim());
        n = Integer.parseInt(this.subStr[2].trim());
        System.out.println("first number = " + m + "\n" + "second number = " + n);
        return m;
    }

    public int Calculate() {

        int answer = 0;
        if (Objects.equals(this.subStr[1], "+")) {
            answer = m + n;
            System.out.println(this.math + " " + answer);
        } else if (Objects.equals(this.subStr[1], "-")) {
            answer = m - n;
            System.out.println(this.math + " " + answer);
        } else if (Objects.equals(this.subStr[1], "*")) {
            answer = m * n;
            System.out.println(this.math + " " + answer);
        } else if (Objects.equals(this.subStr[1], "/")) {
            answer = m / n;
            System.out.println(this.math + " " + answer);
        } else {
            System.out.println("This operation is incorrect. Please, try again");
        }
        return answer;
    }


    public static void main(String[] args) {
        //Pattern pattern = Pattern.compile("[0-9]\t+[+-/*]\t+[0-9]\t+[=])");
        Calculator calculator = new Calculator(System.in, System.out);
        calculator.scan();
        calculator.parse();
        calculator.CalculatorState();
        calculator.Calculate();
    }
}

