import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class FileSplitter {
    static final String fileEnding = ".txt";

    /**
     * Splits input String based on delimiter
     * PROBLEM: it creates a string without linebreaks, which is not the same as a test case with linebreaks
     * @param input
     * @param pathToSave
     * @param delimiter
     */
    public static void splitOnString(String input, String pathToSave, String delimiter) {
        // Split the inputstring based on spaces " "
        String [] splitInput = input.split("\\s+");

        boolean foundTest = false;
        int testStartIndex = 0;
        int testEndIndex = 0;
        String testName = "";

        for (int i = 0; i < splitInput.length; i++) {
            if (splitInput[i].equals(delimiter) || i == splitInput.length - 1) {
                if (!foundTest) {
                    foundTest = true;
                    testStartIndex = i - 1;
                    testName = splitInput[i + 1];
                } else {
                    boolean foundEnd = false;
                    for (int y = 1; y < i && !foundEnd; y++) {
                        if (splitInput[i - y].equals("}")) {
                            testEndIndex = i - y;
                            foundEnd = true;
                            foundTest = false;
                        }
                    }
                    try{
                        PrintWriter writer = new PrintWriter(pathToSave + testName +
                                fileEnding, StandardCharsets.UTF_8);
                        StringBuilder outputString = new StringBuilder();
                        for (int n = testStartIndex; n <= testEndIndex; n++) {
                            outputString.append(splitInput[n]);
                            outputString.append(" ");
                        }

                        writer.print(outputString.toString());
                        writer.close();
                        if(i != splitInput.length - 1) {
                            i--;
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }

    /**
     * If all test cases are located in a single file
     * Uses a char as delimiter between test cases
     * @param input
     * @throws IOException
     */
    public static void splitOnChar(String input, String pathToSave, char delimiter) throws IOException {
        int indexLastKey = 0;

        int indexCurrentKey = 0;
        // char delimiter = '@'; // example from test file
        int fileIndexName = 0;
        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '@') {
                indexCurrentKey = i;
                PrintWriter writer = new PrintWriter(pathToSave + "Test" + fileIndexName + fileEnding, StandardCharsets.UTF_8);
                String substring = input.substring(indexLastKey,indexCurrentKey);
                indexLastKey = indexCurrentKey;
                writer.print(substring);
                writer.close();
                fileIndexName++;
            }
        }
    }

    /**
     * Splits an input string based on the keyword "void" into files with single test cases
     * Analysis the input string character by character. This preserves the file structure with linebreaks
     * @param input
     * @param pathToSave
     */
    public static void splitOnVoid(String input, String pathToSave) {
        int testStartIndex = 0;
        int testEndIndex = 0;
        // char delimiter = '@'; // example from test file
        int fileIndexName = 0;
        boolean foundTest = false;
        String testName = "";
        for (int i = 0; i < input.length(); i++) {
            try {
                if (i == input.length() - 4 || input.substring(i, i + 4).equals("void")) {
                    if (!foundTest) {
                        testStartIndex = i - 7;
                        foundTest = true;
                        boolean foundName = false;
                        for (int x = 1; x < input.length() && !foundName; x++) {
                            if (input.charAt(i + x) == ')') {
                                foundName = true;
                                testName = input.substring(i + 5, i + (x + 1));
                                System.out.println(testName);
                            }

                        }
                    } else {
                        boolean foundEnd = false;
                        for (int y = 1; y < i && !foundEnd; y++) {
                            if (input.charAt(i - y) == '}') {
                                testEndIndex = i - y;
                                foundEnd = true;
                                foundTest = false;
                            }
                        }
                        try {
                            String substring = input.substring(testStartIndex,testEndIndex + 1);
                            printCharactersToFile(pathToSave, testName, substring);
                            fileIndexName++;
                            if (i != input.length() - 1) {
                                i--;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (StringIndexOutOfBoundsException e) {
                if (i == input.length() - 1) {
                    System.out.println("Reached end of file");
                }
            }
        }
    }

    public static void printCharactersToFile(String pathToSave, String testName, String substring) throws IOException {
        PrintWriter writer = new PrintWriter(pathToSave + testName + fileEnding, StandardCharsets.UTF_8);
        writer.print(substring);
        writer.close();
    }
}
