import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class main {
    static final String fileEnding = ".txt";

    public static String readFileAsString(String fileName)throws Exception
    {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    public static void main(String[] args) throws Exception
    {
        // enter file path as E:\\Bachelor\\githubTest\\githubtests.txt")
        String path = "D:\\Dropbox\\shared2\\ChartTestSuite\\tests\\org\\jfree\\chart\\annotations\\junit\\CategoryTextAnnotationTests.java";
        String pathToSave = "D:\\Dropbox\\shared2\\TestCases\\Chart";
        String data = readFileAsString(path);
        splitOnString(data, pathToSave, "void");

    }


    public static void splitOnString(String input, String pathToSave, String delimiter) {
        // Split the inputstring based on spaces " "
        String [] splitInput = input.split("\\s+");

        boolean foundTest = false;
        int testStartIndex = 0;
        int testEndIndex = 0;
        String testName = "";

        /*
        1.  Check if splitInput[i] is void

                if yes:
                    if foundTest == false
                        testStartIndex = index - 1
                        testName = splitInput[index + 1]
                        set foundTest = true
                    else
                        go back in the string array to find the index of last }
                        testEndIndex = index of last }
                        save substring between testStartIndex and testEndIndex in a file, use testName


         */
        // TODO: fix problem: currently algorithm can't find more than 2 tests, but it can find all test names. I assume some problem in the structure
        for (int i = 0; i < splitInput.length; i++) {
            if (splitInput[i].equals(delimiter)) {
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
                        i--;
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

}
