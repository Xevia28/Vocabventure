import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class GenerateWord {
    Random generator = new Random();
    String[] dict;
    int dictSize;
    int[] generatedLineNum;
    int generatedCount;

    public GenerateWord() {
        try {
            FileInputStream fstream = new FileInputStream("dict.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

            // Count the lines in the file to determine dictionary size
            int lineCount = 0;
            while (br.readLine() != null) {
                lineCount++;
            }
            in.close();

            dict = new String[lineCount];
            generatedLineNum = new int[lineCount];
            generatedCount = 0;

            // Reopen the file to read the words into the dictionary
            fstream = new FileInputStream("dict.txt");
            in = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(in));

            int index = 0;
            while ((strLine = br.readLine()) != null) {
                dict[index] = strLine;
                index++;
            }
            dictSize = lineCount;
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String generateWord() {
        if (generatedCount == dictSize) {
            return "";
        }

        int n;
        while (true) {
            n = generator.nextInt(dictSize);

            // Check if this number has already been generated
            boolean alreadyGenerated = false;
            for (int i = 0; i < generatedCount; i++) {
                if (generatedLineNum[i] == n) {
                    alreadyGenerated = true;
                    break;
                }
            }

            if (!alreadyGenerated) {
                generatedLineNum[generatedCount] = n;
                generatedCount++;
                return dict[n].toUpperCase();
            }
        }
    }
}
