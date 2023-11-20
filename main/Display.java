import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Display implements ActionListener {
    JFrame frame;
    LetterButton[] btnArr;
    JLabel[] labels;
    int length;
    boolean letterVerticalOrientation;
    String[] foundWords;
    LetterButton[] selectedBtns = new LetterButton[0];
    String[] wordsToFind;
    String[] wordList;
    String word = "";
    int numOfWords;
    JPanel content;
    JPanel bottomPanel;
    String foundWord = "";

    public Display(int length) {
        this.length = length;
        this.numOfWords = 10;

        ImageIcon img = new ImageIcon("images/logo.png");
        frame = new JFrame();
        frame.setResizable(false);
        frame.setIconImage(img.getImage());
        labels = new JLabel[numOfWords];

        // Initialize selectedBtns with null values
        for (int i = 0; i < selectedBtns.length; i++) {
            selectedBtns[i] = null;
        }
        buildGrid();

    }

    public void buildGrid() {
        GridGenerator grid = new GridGenerator(10, 15);
        grid.setUp();
        String[][] board = grid.getBoard();
        wordList = grid.getListOfWords();
        wordsToFind = new String[numOfWords];
        foundWords = new String[numOfWords];
        System.arraycopy(wordList, 0, wordsToFind, 0, numOfWords);

        // frame.setVisible(true);
        content = new JPanel();
        content.setLayout(new GridLayout(length, length));
        String[] randLetters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
                "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
        Random r = new Random();
        btnArr = new LetterButton[length * length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int n = Math.abs(r.nextInt()) % 26;
                LetterButton btn;

                // populate the empty with random in grid
                if (board[i][j] == null)
                    btn = new LetterButton(randLetters[n], i, j);
                else
                    btn = new LetterButton(board[i][j], i, j);

                content.add(btn);
                btn.addActionListener(this);
                btnArr[i * length + j] = btn;
            }
        }

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(5, 2, 30, 0));
        // Color transparentColor = new Color(0, 0, 0, 0);
        bottomPanel.setBackground(Color.BLACK);

        for (int i = 0; i < numOfWords; i++) {
            labels[i] = new JLabel(wordList[i]);
            labels[i].setForeground(Color.WHITE);
            labels[i].setFont(new Font("Roboto", Font.BOLD, 18));
            bottomPanel.add(labels[i]);
        }

        bottomPanel.setBorder(new EmptyBorder(10, 20, 20, 20));
        content.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    public JPanel getContentPanel() {
        return content;
    }

    public JPanel getBottomPanel() {
        return bottomPanel;
    }

    public boolean containsWord(String word) {
        for (String w : wordsToFind) {
            if (w != null && w.equals(word)) {
                return true;
            }
        }
        return false;
    }

    public int findWordIndex(String word) {
        for (int i = 0; i < numOfWords; i++) {
            if (wordsToFind[i] != null && wordsToFind[i].equals(word)) {
                return i;
            }
        }
        return -1;
    }

    public boolean allWordsFound() {
        for (String word : wordsToFind) {
            if (word != null) {
                return false;
            }
        }
        return true;
    }

    public void clearSelectedBtns() {
        for (LetterButton b : selectedBtns) {
            b.setSelected(false);
        }
        selectedBtns = new LetterButton[0];
        word = "";
    }

    public int getLength() {
        return length;
    }

    public String[] getWordsToFind() {
        return wordsToFind;
    }

    public String[] getFoundWords() {
        return foundWords;
    }

    public String getFoundWord() {
        return foundWord;
    }

    void insert(LetterButton element) {
        LetterButton[] newArr = new LetterButton[selectedBtns.length + 1];
        for (int i = 0; i < selectedBtns.length; i++) {
            newArr[i] = selectedBtns[i];
        }
        newArr[newArr.length - 1] = element;
        selectedBtns = newArr;
    }

    void add(int index, LetterButton element) {
        LetterButton[] newArr = new LetterButton[selectedBtns.length + 1];
        for (int i = 0; i < index; i++) {
            newArr[i] = selectedBtns[i];
        }
        newArr[index] = element;
        for (int i = index + 1; i < selectedBtns.length + 1; i++) {
            newArr[i] = selectedBtns[i - 1];
        }
        selectedBtns = newArr;
    }

    void remove(int index) {
        if (index < 0 || index >= selectedBtns.length) {
            return;
        }
        LetterButton[] newArr = new LetterButton[selectedBtns.length - 1];
        for (int i = 0, j = 0; i < selectedBtns.length; i++) {
            if (i != index) {
                newArr[j++] = selectedBtns[i];
            }
        }
        selectedBtns = newArr;
    }

    void wordRemove(int index) {
        if (index < 0 || index >= wordsToFind.length) {
            return;
        }
        String[] newArr = new String[wordsToFind.length - 1];
        for (int i = 0, j = 0; i < wordsToFind.length; i++) {
            if (i != index) {
                newArr[j++] = wordsToFind[i];
            }
        }
        wordsToFind = newArr;
    }

    void labelRemove(int index) {
        if (index < 0 || index >= labels.length) {
            return;
        }
        JLabel[] newArr = new JLabel[labels.length - 1];
        for (int i = 0, j = 0; i < labels.length; i++) {
            if (i != index) {
                newArr[j++] = labels[i];
            }
        }
        labels = newArr;
    }

    boolean doesContain(String word) {
        for (String wordEl : wordsToFind) {
            if (wordEl != null && wordEl.equals(word)) {
                return true;
            }
        }
        return false;
    }

    int indexOf(String word) {
        for (int i = 0; i < wordsToFind.length; i++) {
            if (wordsToFind[i] != null && wordsToFind[i].equals(word)) {
                return i;
            }
        }
        return -1;
    }

    void push(String word) {
        for (int i = 0; i < foundWords.length; i++) {
            if (foundWords[i] == null) {
                foundWords[i] = word;
                return;
            }
        }
    }

    public void checkMatch() {
        if (doesContain(word)) {
            int index = indexOf(word);
            wordRemove(index);
            foundWord = word;
            push(word);
            // System.out.println(labels[index].getText());
            labels[index].setForeground(Color.GRAY);
            labelRemove(index);
            for (LetterButton b : selectedBtns) {
                b.setSelected(false);
                b.setFoundWord(true);
            }
            clearSelectedBtns();
            word = "";
        }

        // Check if all the words were found
        if (wordsToFind.length == 0) {
            JOptionPane.showMessageDialog(frame,
                    "Well Done!! You have successfully found all the words! You may end game to see your score.",
                    "Congratulations",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof LetterButton) {
            LetterButton btn = (LetterButton) e.getSource();
            if (btn.getSelected()) {
                if (btn == selectedBtns[selectedBtns.length - 1]) {
                    remove(selectedBtns.length - 1);
                    word = word.substring(0, word.length() - 1);
                } else if (btn == selectedBtns[0]) {
                    remove(0);
                    if (word.length() == 1)
                        word = "";
                    else
                        word = word.substring(1);
                } else
                    return;
            } else {
                if (selectedBtns.length == 0) {
                    insert(btn);
                    word = btn.getLetter();
                } else {
                    // get orientation of button is only one button was already selected
                    if (selectedBtns.length == 1) {
                        // if the button is in the same row as the other selected button
                        if (selectedBtns[0].getXPos() == btn.getXPos())
                            letterVerticalOrientation = false;
                        // if the button is in the same column as the other selected button
                        else if (selectedBtns[0].getYPos() == btn.getYPos())
                            letterVerticalOrientation = true;
                        else {
                            clearSelectedBtns();
                            insert(btn);
                            word = btn.getLetter();
                            btn.toggle();
                            return;
                        }
                    }

                    if (letterVerticalOrientation) {
                        // if the button is beside the reference, on the top
                        if (btn.getXPos() == selectedBtns[0].getXPos() - 1
                                && btn.getYPos() == selectedBtns[0].getYPos()) {
                            add(0, btn);
                            word = btn.getLetter() + word;
                        } // if the button is beside the reference, on the bottom
                        else if (btn.getXPos() == selectedBtns[selectedBtns.length - 1].getXPos() + 1
                                && btn.getYPos() == selectedBtns[selectedBtns.length - 1].getYPos()) {
                            insert(btn);
                            word = word + btn.getLetter();
                        } else {
                            clearSelectedBtns();
                            insert(btn);
                            word = btn.getLetter();
                        }
                    } else {
                        // if the button is beside the reference, on the left
                        if (btn.getYPos() == selectedBtns[0].getYPos() - 1
                                && btn.getXPos() == selectedBtns[0].getXPos()) {
                            add(0, btn);
                            word = btn.getLetter() + word;
                        } // if the button is beside the reference, on the right
                        else if (btn.getYPos() == selectedBtns[selectedBtns.length - 1].getYPos() + 1
                                && btn.getXPos() == selectedBtns[selectedBtns.length - 1].getXPos()) {
                            word = word + btn.getLetter();
                            insert(btn);
                        } else {
                            clearSelectedBtns();
                            insert(btn);
                            word = btn.getLetter();
                        }
                    }

                }
            }
            btn.toggle(); // toggle the button to be selected or not
            checkMatch(); // Check if a word was found
        }
    }

}
