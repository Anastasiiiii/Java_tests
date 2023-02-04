import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public Messages getMessages() {
        return messages;
    }

    Messages messages = new Messages("Please, pass me an input file", "Input file does not exist", "Input file contains something wrong.");

    interface MainOutput {
        default void printLine(String msg) {
            System.out.println(msg);
        }
    }

    interface FileSystem {
        default boolean ifFileExists(String filePath) {
            return true;
        }

    }

    List<String> list = new ArrayList<>();

    void getGameBoardFromInputFIle(MainOutput output) throws IOException {

        String s = "D:\\kpi\\input.txt";
        Path path = Path.of(s);
        list = Files.readAllLines(path);

        for (String str : list)
            System.out.println(str);
        if (Objects.equals(list, " ")) {
            output.printLine(getMessages().inputFileContainsSmithWrong);
        }
        list.toString();
    }

    String[] words = new String[list.size()];

    String[] parseGameBoard() {
        System.out.println(" ");
        String str = list.toString();
        words = str.split(" ");
        for (String word : words) {
            System.out.println(word);
        }

        System.out.println(" ");
        return words;
    }

    String[] moveFigures() {
        System.out.println(" ");
        Collections.swap(Arrays.asList(words), 1, 2);
        Collections.swap(Arrays.asList(words), 3, 2);
        Collections.swap(Arrays.asList(words), 2, 1);
        Collections.swap(Arrays.asList(words), 1, 0);
        Collections.swap(Arrays.asList(words), 3, 2);

        for (String word : words) {
            System.out.println(word);
        }
        return words;
    }

    public void mainHandler(String[] args, MainOutput output, FileSystem fs) {

        if (args.length == 0) {
            output.printLine(getMessages().noArgs);
            return;
        }

        String inputFilePath = args[0];

        if (fs.ifFileExists(inputFilePath)) {
            output.printLine(getMessages().noInput);
            return;
        }
        return;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        MainOutput output = new MainOutput() {
            @Override
            public void printLine(String msg) {
                MainOutput.super.printLine(msg);
            }
        };
        main.getGameBoardFromInputFIle(output);
        main.parseGameBoard();
        main.moveFigures();
    }
}

class Messages {
    String noArgs;
    String noInput;
    String inputFileContainsSmithWrong;

    public Messages(String noArgs, String noInput, String inputFileContainsSmithWrong) {
        this.noArgs = noArgs;
        this.noInput = noInput;
        this.inputFileContainsSmithWrong = inputFileContainsSmithWrong;
    }
}
