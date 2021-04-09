import com.sun.source.tree.BindingPatternTree;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;


public class main {


    public static String readFileAsString(String fileName)throws Exception
    {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    public static void main(String[] args) throws Exception
    {
        // enter file path like E:\\Bachelor\\githubTest\\githubtests.txt")
        // here I will need the path to the parent folder
        // String path = "D:\\Dropbox\\shared2\\TestCases\\Chart\\onlyTests";
        String path = args[0];

        try {
            File f =new File(path);

            FilenameFilter filter = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".java");
                }
            };

            File[] files = f.listFiles(filter);

            for (int i = 0; i < files.length; i++) {

                // has to be taken from file-list
                String filename = files[i].getName();

                // removes the file ending ".java" from the filename that will be printed
                String filenameToPrint = filename.substring(0, filename.length() - 5);

                // FOR WINDOWS String pathToSave = "E:\\Bachelor\\splitterTests\\Chart" + "\\" + filenameToPrint;
                String pathToSave = args[1] + "/" + filenameToPrint;

                // FOR WINDOWS USE \\
                String data = readFileAsString(path + "/" + filename);

                FileSplitter.splitOnBrac(data,pathToSave);
            }
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}

