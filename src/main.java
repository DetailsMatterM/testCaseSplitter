import com.sun.source.tree.BindingPatternTree;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class main {


    public static String readFileAsString(String fileName)throws Exception
    {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    public static void main(String[] args) throws Exception
    {
        // enter file path as E:\\Bachelor\\githubTest\\githubtests.txt")

        String path = "D:\\Dropbox\\shared2\\ChartTestSuite\\tests\\org\\jfree\\chart\\block\\junit";
        String filename = "BlockBorderTests.java";

        // removes the file ending ".java" from the filename that will be printed
        String filenameToPrint = filename.substring(0, filename.length() - 5);
        String pathToSave = "E:\\Bachelor\\splitterTests\\Chart" + "\\" + filenameToPrint;
        String data = readFileAsString(path + "\\" + filename);
        //splitOnString(data, pathToSave, "void");
        FileSplitter.splitOnVoid(data,pathToSave);

    }



}

