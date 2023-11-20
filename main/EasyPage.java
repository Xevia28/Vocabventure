import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EasyPage extends JPanel {
    JLabel easyEnd;
    ImageBG easyPage;
    String[] found;
    JLabel easyTimerLabel;
    Timer easyTimer;
    int secondsElapsed;

    public EasyPage(JLabel time, Timer timer) {
        this.easyTimer = timer;
        this.easyTimerLabel = time;
        easyPage = new ImageBG("images/easyBG.jpg");
        easyPage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        easyPage.setLayout(null);
        easyPage.setOpaque(true);

        LogoPanel logoSmall1 = new LogoPanel("images/logo.png");
        logoSmall1.setOpaque(false);
        logoSmall1.setBounds(50, 50, 100, 100);
        JLabel easyTitle1 = new JLabel("Easy Mode");
        JLabel colon1 = new JLabel(":");
        JLabel easyTitle2 = new JLabel("Find the Given Words");
        easyTitle1.setFont(new Font("SUGAQI Trial", Font.PLAIN, 30));
        easyTitle2.setFont(new Font("SUGAQI Trial", Font.PLAIN, 30));
        colon1.setFont(new Font("Montserrat", Font.BOLD, 30));
        easyEnd = new JLabel("END GAME");
        easyEnd.setForeground(Color.WHITE);
        easyEnd.setFont(new Font("SUGAQI Trial", Font.PLAIN, 30));
        easyTitle1.setForeground(Color.WHITE);
        easyTitle2.setForeground(Color.WHITE);
        colon1.setForeground(Color.WHITE);
        easyTitle1.setBounds(200, 83, 180, 30);
        colon1.setBounds(390, 83, 200, 30);
        easyTitle2.setBounds(410, 83, 500, 30);
        easyEnd.setBounds(710, 710, 200, 30);

        easyPage.add(logoSmall1);
        easyPage.add(easyTitle1);
        easyPage.add(colon1);
        easyPage.add(easyTitle2);
        easyPage.add(easyEnd);

        Display game = new Display(15);
        found = game.getFoundWords();

        JPanel board = game.getContentPanel();
        JPanel words = game.getBottomPanel();
        board.setBounds(60, 200, 500, 560);
        Color backgroundColor = new Color(0, 0, 0, 30);
        board.setBackground(backgroundColor);
        JLabel wordsToFind = new JLabel("Words to Find");
        wordsToFind.setFont(new Font("SUGAQI Trial", Font.BOLD, 24));
        wordsToFind.setForeground(Color.WHITE);
        wordsToFind.setBounds(635, 250, 300, 30);
        words.setBounds(615, 300, 350, 300);
        time.setFont(new Font("SansSerif", Font.BOLD, 16));
        time.setBounds(70, 170, 300, 30);
        time.setForeground(Color.WHITE);
        easyPage.add(time);
        easyPage.add(wordsToFind);

        easyPage.add(board);
        easyPage.add(words);
    }

    public void startTimer() {
        if (easyTimer != null) {
            easyTimer.stop();
            secondsElapsed = 0;
            easyTimer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    secondsElapsed++;
                    easyTimerLabel.setText("Elapsed Time: " + secondsElapsed + " seconds");
                }
            });
            easyTimer.start();
        }
    }

    public ImageBG getPage() {
        return easyPage;
    }

    public String[] getFoundWords() {
        return found;
    }

    public JLabel getLabel() {
        return easyEnd;
    }
}
