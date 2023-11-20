import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;

public class HardPage {
    ImageBG hardPage;
    JLabel hardEnd;
    String[] found;
    String[] wordsToFind;
    JLabel hardTimerLabel;
    Timer hardTimer;
    int secondsElapsed;
    JLabel wordCount = new JLabel("Words left to find");
    LinkedList wordList;
    Timer wordCheckTimer;
    String currentWordCheck;

    public HardPage(JLabel time, Timer timer) {
        this.hardTimer = timer;
        this.hardTimerLabel = time;

        hardPage = new ImageBG("images/hardBG.jpg");
        hardPage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        hardPage.setLayout(null);
        hardPage.setOpaque(true);

        LogoPanel logoSmall3 = new LogoPanel("images/logo.png");
        logoSmall3.setOpaque(false);
        logoSmall3.setBounds(50, 50, 100, 100);
        JLabel hardTitle1 = new JLabel("Hard Mode");
        JLabel colon3 = new JLabel(":");
        JLabel hardTitle2 = new JLabel("Find the Words Yourself");
        hardTitle1.setFont(new Font("SUGAQI Trial", Font.PLAIN, 30));
        hardTitle2.setFont(new Font("SUGAQI Trial", Font.PLAIN, 30));
        colon3.setFont(new Font("Montserrat", Font.BOLD, 30));
        hardTitle1.setForeground(Color.WHITE);
        hardTitle2.setForeground(Color.WHITE);
        colon3.setForeground(Color.WHITE);
        hardTitle1.setBounds(200, 83, 180, 30);
        colon3.setBounds(390, 83, 200, 30);
        hardTitle2.setBounds(410, 83, 500, 30);
        hardEnd = new JLabel("END GAME");
        hardEnd.setForeground(Color.WHITE);
        hardEnd.setFont(new Font("SUGAQI Trial", Font.PLAIN, 30));
        hardEnd.setBounds(710, 710, 200, 30);

        hardPage.add(hardEnd);
        hardPage.add(hardTitle1);
        hardPage.add(colon3);
        hardPage.add(hardTitle2);
        hardPage.add(logoSmall3);

        Display game = new Display(15);

        wordCheckTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentWordCheck = game.getFoundWord();
                checkAndUpdateWords();
            }
        });
        wordCheckTimer.start();

        found = game.getFoundWords();
        wordsToFind = game.getWordsToFind();
        wordList = new LinkedList();
        for (String word : wordsToFind) {
            wordList.insertAtEnd(word);
        }
        wordCount.setText(String.valueOf(wordList.getSize()));
        currentWordCheck = game.getFoundWord();

        wordCount.setBounds(750, 400, 350, 50);
        wordCount.setForeground(Color.WHITE);
        wordCount.setBackground(Color.BLACK);
        wordCount.setBorder(new EmptyBorder(10, 10, 10, 10));
        wordCount.setFont(new Font("SUGAQI Trial", Font.BOLD, 40));

        JLabel leftToFind = new JLabel("Words Left to Find:");
        leftToFind.setFont(new Font("SansSerif", Font.BOLD, 28));
        leftToFind.setBounds(640, 300, 350, 50);
        leftToFind.setForeground(Color.WHITE);

        JPanel board = game.getContentPanel();
        // JPanel words = game.getBottomPanel();
        board.setBounds(60, 200, 500, 560);
        Color backgroundColor = new Color(0, 0, 0, 10);
        board.setBackground(backgroundColor);
        // words.setBounds(615, 300, 350, 300);
        time.setFont(new Font("SansSerif", Font.BOLD, 16));
        time.setBounds(70, 170, 300, 30);
        time.setForeground(Color.WHITE);
        hardPage.add(time);
        hardPage.add(board);
        hardPage.add(wordCount);
        hardPage.add(leftToFind);
    }

    public void checkAndUpdateWords() {
        String currentWord = currentWordCheck;
        if (isWordFound(currentWord)) {
            if (!wordList.isEmpty()) {
                wordList.removeByValue(currentWord);
                wordCount.setText(String.valueOf(wordList.getSize()));
            } else {
                wordCount.setText("0");
            }
        }
    }

    boolean isWordFound(String word) {
        if (found != null) {
            for (String foundWord : found) {
                if (foundWord != null && foundWord.equals(word)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void startTimer() {
        if (hardTimer != null) {
            hardTimer.stop();
            secondsElapsed = 0;
            hardTimer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    secondsElapsed++;
                    hardTimerLabel.setText("Elapsed Time: " + secondsElapsed + " seconds");
                }
            });
            hardTimer.start();
        }
    }

    public String[] getFoundWords() {
        return found;
    }

    public ImageBG getPage() {
        return hardPage;
    }

    public JLabel getLabel() {
        return hardEnd;
    }
}
