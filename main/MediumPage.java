import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;

public class MediumPage {
    ImageBG mediumPage;
    JLabel mediumEnd;
    String[] found;
    String[] wordsToFind;
    JLabel mediumTimerLabel;
    Timer mediumTimer;
    int secondsElapsed;
    Queue wordsQueue;
    JLabel currentWordLabel = new JLabel("First Word");
    JLabel wordLabel;
    Timer wordCheckTimer;

    public MediumPage(JLabel time, Timer timer) {
        this.mediumTimer = timer;
        this.mediumTimerLabel = time;

        wordCheckTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAndUpdateWords();
            }
        });
        wordCheckTimer.start();

        mediumPage = new ImageBG("images/mediumBG.jpg");
        mediumPage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mediumPage.setLayout(null);
        mediumPage.setOpaque(true);

        LogoPanel logoSmall2 = new LogoPanel("images/logo.png");
        logoSmall2.setOpaque(false);
        logoSmall2.setBounds(50, 50, 100, 100);
        JLabel mediumTitle1 = new JLabel("Medium Mode");
        JLabel colon2 = new JLabel(":");
        JLabel mediumTitle2 = new JLabel("Assisted Word Hunt");
        mediumTitle1.setFont(new Font("SUGAQI Trial", Font.PLAIN, 30));
        mediumTitle2.setFont(new Font("SUGAQI Trial", Font.PLAIN, 30));
        colon2.setFont(new Font("Montserrat", Font.BOLD, 30));
        mediumTitle1.setForeground(Color.WHITE);
        mediumTitle2.setForeground(Color.WHITE);
        colon2.setForeground(Color.WHITE);
        mediumTitle1.setBounds(200, 83, 230, 30);
        colon2.setBounds(440, 83, 200, 30);
        mediumTitle2.setBounds(460, 83, 500, 30);
        mediumEnd = new JLabel("END GAME");
        mediumEnd.setForeground(Color.WHITE);
        mediumEnd.setFont(new Font("SUGAQI Trial", Font.PLAIN, 30));
        mediumEnd.setBounds(710, 710, 200, 30);

        mediumPage.add(mediumEnd);
        mediumPage.add(mediumTitle1);
        mediumPage.add(colon2);
        mediumPage.add(mediumTitle2);
        mediumPage.add(logoSmall2);

        Display game = new Display(15);
        found = game.getFoundWords();
        wordsToFind = game.getWordsToFind();
        wordsQueue = new Queue();
        for (String word : wordsToFind) {
            wordsQueue.enqueue(word);
        }
        currentWordLabel.setText(wordsQueue.dequeue());
        // System.out.println(currentWordLabel.getText());
        // if (isWordFound(currentWordLabel.getText())) {
        // wordsQueue.traversal();
        // }

        JPanel board = game.getContentPanel();
        board.setBounds(60, 200, 500, 560);
        Color backgroundColor = new Color(0, 0, 0, 10);
        board.setBackground(backgroundColor);
        // wordLabel = new JLabel("Find");
        // wordLabel.setBounds(610, 300, 350, 50);
        // wordLabel.setForeground(Color.WHITE);
        // wordLabel.setFont(new Font("SUGAQI Trial", Font.BOLD, 30));
        currentWordLabel.setBounds(680, 400, 350, 50);
        currentWordLabel.setForeground(Color.WHITE);
        currentWordLabel.setBackground(Color.BLACK);
        currentWordLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        currentWordLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        time.setFont(new Font("SansSerif", Font.BOLD, 16));
        time.setBounds(70, 170, 300, 30);
        time.setForeground(Color.WHITE);
        mediumPage.add(time);
        mediumPage.add(board);
        mediumPage.add(currentWordLabel);
        // mediumPage.add(wordLabel);
    }

    public void checkAndUpdateWords() {
        String currentWord = currentWordLabel.getText();
        if (isWordFound(currentWord)) {
            if (!wordsQueue.isEmpty()) {
                currentWordLabel.setText(wordsQueue.dequeue());
                // System.out.println(currentWordLabel.getText());
            } else {
                currentWordLabel.setText("All words found!");
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
        if (mediumTimer != null) {
            mediumTimer.stop();
            secondsElapsed = 0;
            mediumTimer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    secondsElapsed++;
                    mediumTimerLabel.setText("Elapsed Time: " + secondsElapsed + " seconds");
                }
            });
            mediumTimer.start();
        }
    }

    public String[] getFoundWords() {
        return found;
    }

    public ImageBG getPage() {
        return mediumPage;
    }

    public JLabel getLabel() {
        return mediumEnd;
    }
}
