import java.util.Random;

public class GridGenerator {
    int numOfWords;
    String[] listOfWords;
    String[][] board;
    GenerateWord generator;
    int length;

    public GridGenerator(int numOfWords, int length) {
        generator = new GenerateWord();
        this.numOfWords = numOfWords;
        this.length = length;
        listOfWords = new String[numOfWords];
    }

    public void setUp() {
        int numGenerated = 0;
        board = new String[length][length];
        Random rand = new Random();
        while (numGenerated < numOfWords) {
            String word = generator.generateWord();
            listOfWords[numGenerated] = word;

            // Determine if the word will be vertical or horizontal
            boolean vertical = (rand.nextInt(2) == 1);

            // Generate a row 0 to the length - 1
            if (vertical) {
                int col = rand.nextInt(length);
                int row = rand.nextInt(length - word.length() + 1);

                boolean success = true;
                for (int k = 0; k < word.length(); k++) {
                    if (board[row + k][col] != null) {
                        if (!board[row + k][col].equals(word.substring(k, k + 1))) {
                            success = false;
                            break;
                        }
                    }
                }
                if (success) {
                    for (int k = 0; k < word.length(); k++) {
                        board[row + k][col] = word.substring(k, k + 1);
                    }
                    numGenerated++;
                }
            } else {
                int row = rand.nextInt(length);
                int col = rand.nextInt(length - word.length() + 1);

                boolean success = true;
                for (int k = 0; k < word.length(); k++) {
                    if (board[row][col + k] != null) {
                        if (!board[row][col + k].equals(word.substring(k, k + 1))) {
                            success = false;
                            break;
                        }
                    }
                }
                if (success) {
                    for (int k = 0; k < word.length(); k++) {
                        board[row][col + k] = word.substring(k, k + 1);
                    }
                    numGenerated++;
                }
            }
        }
    }

    public int getNumOfWords() {
        return numOfWords;
    }

    public void setNumOfWords(int numOfWords) {
        this.numOfWords = numOfWords;
    }

    public String[][] getBoard() {
        return board;
    }

    public String[] getListOfWords() {
        return listOfWords;
    }
}
