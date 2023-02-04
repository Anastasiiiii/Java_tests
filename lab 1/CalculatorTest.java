import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;


import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void scan() {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("1 + 3 =".getBytes()));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        PrintStream stdout = System.out;
        System.setOut(ps);

        Calculator.main(new String[0]);

        System.setIn(stdin);
        System.setOut(stdout);

        String outputText = byteArrayOutputStream.toString();
        String key = "Your math operation is: ";
        String output = outputText.substring(outputText.indexOf(key) + key.length());
        String expected = "1 + 3 =\r\n1\r\n+\r\n3\r\n=\r\n";
        assertEquals(expected, output);
    }

    @Test
    void parse() {
    }

    @Test
    void CalculatorState()  {
        try
        {
            InputStream stdin = System.in;
            PrintStream stdout = System.out;

            System.setIn(stdin);
            System.setOut(stdout);

            Calculator calculator = new Calculator(stdin, stdout);
            calculator.setSubStr(new String[]{"1", "+", "3", "="});
            int actual = calculator.CalculatorState();
            int expected = 1;
            assertEquals(expected, actual);
        }
        catch (NumberFormatException nfe)
        {
            System.out.println("NumberFormatException: " + nfe.getMessage());
        }
    }

    @Test
    void Calculate() {
        InputStream stdin = System.in;
        PrintStream stdout = System.out;

        System.setIn(stdin);
        System.setOut(stdout);

        Calculator calculator = new Calculator(stdin, stdout);
        calculator.setSubStr(new String[]{"1", "+", "3", "="});
        calculator.CalculatorState();
        int actualAddition = calculator.Calculate();
        int expectedAddition = 4;
        assertEquals(actualAddition, expectedAddition);

        calculator.setSubStr(new String[]{"3", "-", "1", "="});
        calculator.CalculatorState();
        int actualSubtraction = calculator.Calculate();
        int expectedSubtraction = 2;
        assertEquals(actualSubtraction, expectedSubtraction);

        calculator.setSubStr(new String[]{"2", "*", "3", "="});
        calculator.CalculatorState();
        int actualMultiply = calculator.Calculate();
        int expectedMultiply = 6;
        assertEquals(expectedMultiply, actualMultiply);

        calculator.setSubStr(new String[]{"6", "/", "3", "="});
        calculator.CalculatorState();
        int actualDivision = calculator.Calculate();
        int expectedDivision = 2;
        assertEquals(expectedDivision, actualDivision);

        calculator.setSubStr(new String[]{"6", "#", "3", "="});
        calculator.CalculatorState();
        int actual = calculator.Calculate();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void main() {
    }
}
