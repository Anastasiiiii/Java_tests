import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MainTests {

    @BeforeEach
    void clearMessages() {
        messages.clear();
    }

    Main main = new Main();
    ArrayList<String> messages = new ArrayList<>();
    Main.MainOutput output = new Main.MainOutput() {
        @Override
        public void printLine(String msg) {
            //Main.MainOutput.super.printLine(msg);
            messages.add(msg);
        }
    };

    Main.FileSystem fsMock = new Main.FileSystem() {
        @Override
        public boolean ifFileExists(String filePath) {
            Main.FileSystem.super.ifFileExists(filePath);
            throw new RuntimeException("ifFileExist has been called unexpectedly");
            //return false;
        }
    };

    @Test
    void IfArrayIsEmpty() {
        String[] emptyArray = {};

        main.mainHandler(emptyArray, output, fsMock);

        assertEquals(1, messages.size());
        assertEquals(main.getMessages().noArgs, messages.get(0));
    }

    @Test
    void IfInputFileExists() {

        Main.FileSystem fsMock = new Main.FileSystem() {

            @Override
            public boolean ifFileExists(String filePath) {
                Main.FileSystem.super.ifFileExists(filePath);
                return true;
            }

        };

        String input = "input.txt";

        main.mainHandler(new String[]{input}, output, fsMock);

        assertEquals(1, messages.size());
        assertEquals(main.getMessages().noInput, messages.get(0));
    }

    @Test
    void ParseAndCheckInputFileTest() throws IOException {

        Main.FileSystem fsMock = new Main.FileSystem() {
            @Override
            public boolean ifFileExists(String filePath) {
                Main.FileSystem.super.ifFileExists(filePath);
                return true;
            }
        };

        Path path = Path.of("D:\\kpi\\input.txt");
        String list = Files.readString(path);

        main.mainHandler(new String[]{list}, output, fsMock);

        assertEquals(1, messages.size());
        assertEquals(main.getMessages().noInput, messages.get(0));
    }

    @Test
    void InputParseTest() throws IOException {
        String[] expected = {"[..p.....,", ".ppp....,", "..p.....,", "........,", "...#....,", "...#....,", "...#...#,", "#..#####]" };
        Main main = new Main();
        main.getGameBoardFromInputFIle(output);
        String[] actual = main.parseGameBoard();

        assertArrayEquals(expected, actual);
    }

    @Test
    void MoveFigureMethodTest() throws IOException {
        String[] expected = {"........,", "[..p.....,", ".ppp....,", "..p.....,", "...#....,", "...#....,", "...#...#,", "#..#####]" };

        Main main = new Main();
        main.getGameBoardFromInputFIle(output);
        main.parseGameBoard();
        String[] actual = main.moveFigures();

        assertArrayEquals(expected, actual);
    }

}
