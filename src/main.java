import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
        String path = "E:\\Bachelor\\githubTest\\githubtests.txt";
        String data = readFileAsString(path);
        splitString(data);
    }

    public static void splitString(String input) throws IOException {
        int indexLastKey = 0;
        int indexCurrentKey = 0;
        int fileIndexName = 0;
        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '@') {
                indexCurrentKey = i;
                PrintWriter writer = new PrintWriter("E:\\Bachelor\\githubTest\\" + "Test" + fileIndexName + ".txt", StandardCharsets.UTF_8);
                String substring = input.substring(indexLastKey,indexCurrentKey);
                indexLastKey = indexCurrentKey;
                writer.print(substring);
                writer.close();
                fileIndexName++;
            }
        }
    }

}
