import javax.swing.*;
import java.awt.*;

public class Score {
    GradientPanel indexPage;
    JLabel title;
    JLabel timeElapsed;
    JLabel score;
    JButton easyButton;
    JButton mediumButton;
    JButton hardButton;
    JLabel infoLabel;

    public Score(JFrame frame, JPanel activePanel, JPanel easyPage) {
        indexPage = new GradientPanel();
        indexPage.setLayout(null);
        indexPage.setOpaque(true);
        indexPage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        LogoPanel logo = new LogoPanel("images/logo.png");
        logo.setOpaque(false);
        title = new JLabel("Play Again");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SUGAQI Trial", Font.PLAIN, 30));
        timeElapsed = new JLabel("Time Elapsed: ");
        timeElapsed.setFont(new Font("Montserrat", Font.BOLD, 18));
        timeElapsed.setForeground(Color.WHITE);
        score = new JLabel("Your Score: ");
        score.setFont(new Font("Montserrat", Font.BOLD, 18));
        score.setForeground(Color.WHITE);
        easyButton = new JButton("Easy");
        easyButton.setFocusPainted(false);
        easyButton.setFont(new Font("Montserrat", Font.BOLD, 16));
        mediumButton = new JButton("Medium");
        mediumButton.setFocusPainted(false);
        mediumButton.setFont(new Font("Montserrat", Font.BOLD, 16));
        hardButton = new JButton("Hard");
        hardButton.setFocusPainted(false);
        hardButton.setFont(new Font("Montserrat", Font.BOLD, 16));
        infoLabel = new JLabel("Find out what makes each difficulty challenging");
        infoLabel.setFont(new Font("Montserrat", Font.PLAIN, 18));
        infoLabel.setForeground(Color.WHITE);

        logo.setBounds(385, 110, 250, 250);
        title.setBounds(410, 400, 600, 50);
        timeElapsed.setBounds(380, 460, 290, 30);
        score.setBounds(410, 490, 290, 30);
        easyButton.setBounds(235, 565, 140, 50);
        mediumButton.setBounds(435, 565, 140, 50);
        hardButton.setBounds(635, 565, 140, 50);
        infoLabel.setBounds(290, 630, 500, 30);
        infoLabel.setOpaque(false);
        infoLabel.setPreferredSize(new Dimension(200, 50));

        indexPage.add(logo);
        indexPage.add(title);
        indexPage.add(timeElapsed);
        indexPage.add(score);
        indexPage.add(easyButton);
        indexPage.add(mediumButton);
        indexPage.add(hardButton);
        indexPage.add(infoLabel);

    }

    public void setTimeElapsed(int elapsedTimeInSeconds) {
        timeElapsed.setText("Time Elapsed: " + elapsedTimeInSeconds + " seconds");
    }

    public void setScore(int pointsForWords, int pointsForTime) {
        int totalScore = pointsForWords + pointsForTime;
        if (pointsForWords == 0) {
            score.setText("Your Score: 0");
        } else {
            score.setText("Your Score: " + totalScore);
        }
    }

    public GradientPanel getPage() {
        return indexPage;
    }

    public JButton getEasyButton() {
        return easyButton;
    }

    public JButton getMediumButton() {
        return mediumButton;
    }

    public JButton getHardButton() {
        return hardButton;
    }

    public JLabel getInfoLabel() {
        return infoLabel;
    }
}
