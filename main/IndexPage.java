import javax.swing.*;
import java.awt.*;

public class IndexPage {
    GradientPanel indexPage;
    JLabel title;
    JLabel levels;
    JButton easyButton;
    JButton mediumButton;
    JButton hardButton;
    JLabel infoLabel;

    public IndexPage(JFrame frame, JPanel activePanel, JPanel easyPage) {
        indexPage = new GradientPanel();
        indexPage.setLayout(null);
        indexPage.setOpaque(true);
        indexPage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        LogoPanel logo = new LogoPanel("images/logo.png");
        logo.setOpaque(false);
        title = new JLabel("A Word Hunt Challenge Awaits");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SUGAQI Trial", Font.PLAIN, 30));
        levels = new JLabel("Select difficulty level:");
        levels.setFont(new Font("Montserrat", Font.BOLD, 18));
        levels.setForeground(Color.WHITE);
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
        title.setBounds(250, 400, 600, 50);
        levels.setBounds(410, 470, 290, 30);
        easyButton.setBounds(235, 525, 140, 50);
        mediumButton.setBounds(435, 525, 140, 50);
        hardButton.setBounds(635, 525, 140, 50);
        infoLabel.setBounds(290, 590, 500, 30);
        infoLabel.setOpaque(false);
        infoLabel.setPreferredSize(new Dimension(200, 50));

        indexPage.add(logo);
        indexPage.add(title);
        indexPage.add(levels);
        indexPage.add(easyButton);
        indexPage.add(mediumButton);
        indexPage.add(hardButton);
        indexPage.add(infoLabel);

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
