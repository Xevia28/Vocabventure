import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;

public class HelpPage {
    GradientPanel helpPage;
    JLabel helpTitle;
    JLabel instructionsLabel;
    JLabel backLabel;

    public HelpPage() {
        helpPage = new GradientPanel();
        helpPage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        helpPage.setLayout(null);
        helpPage.setOpaque(true);

        helpTitle = new JLabel("VocabVenture Player Guide");
        Font titleFont = new Font("SUGAQI Trial", Font.PLAIN, 30);
        helpTitle.setFont(titleFont);
        helpTitle.setForeground(Color.WHITE);

        instructionsLabel = new JLabel(
                "<html><p>The objective of this game is to find hidden words within a grid of letters. Each level of difficulty offers a unique experience:</p>"
                        + "<ol>"
                        + "<li><strong>Easy Mode:</strong><br>"
                        + "<ul><li>In Easy mode, you are given a list of words to find.</li>"
                        + "<li>You'll receive immediate feedback if you've found a word.</li></ul></li>"
                        + "<li><strong>Medium Mode:</strong><br>"
                        + "<ul><li>In Medium mode, you are given words to find one by one.</li>"
                        + "<li>Unlike easy mode where all the words are given, only word word will be given at a time..</li>"
                        + "<li>You'll receive feedback on whether you've found the correct word.</li></ul></li>"
                        + "<li><strong>Hard Mode:</strong><br>"
                        + "<ul><li>Hard mode is the most challenging option.</li>"
                        + "<li>You won't be given any words or definitions.</li>"
                        + "<li>Instead, you have to find words entirely on your own.</li>"
                        + "<li>Only the number of words left to find will be shown, so pay attention and stay focused!</li></ul></li>"
                        + "</ol>"
                        + "<strong>How to Play:</strong><br>"
                        + "<ul><li>Select a Difficulty Level: Start by choosing your desired level of difficulty (Easy, Medium, or Hard) when you begin the game.</li>"
                        + "<li>Game Grid: A grid of letters will appear on your screen. This grid contains hidden words that you need to find.</li>"
                        + "<li>Finding Words: Find words by clicking on their first and last letters.</li>"
                        + "<li>Word Highlighting: When you correctly identify a word, it will be highlighted on the grid, and it will be crossed off the list of words to find.</li>"
                        + "<li>Winning the Game: In Easy and Medium modes, your goal is to find and highlight all the words on the list. In Hard mode, you must discover all the words without any assistance.</li>"
                        + "<li>Scoring: The faster you complete the game, the higher your score will be. Try to beat your own records!</li></ul>"
                        +
                        "<p><strong>Tips for Success:</strong><br>"
                        + "Pay attention to the orientation of words: they can go horizontally, vertically, or diagonally.<br>"
                        + "Use your mouse or touch screen to click on letters and highlight words.<br>"
                        + "Stay patient and focused, especially in Hard mode where no hints are provided.</p><br>"
                        +
                        "<p><strong>Have Fun!</strong><br>"
                        + "The Word Search Game is all about challenging your word-finding skills and having fun while doing it. Choose your difficulty level, start your search, and enjoy the game!</p></html>");
        Font custFont = new Font("Montserrat", Font.PLAIN, 12);
        instructionsLabel.setFont(custFont);
        instructionsLabel.setForeground(Color.WHITE);

        backLabel = new JLabel("BACK");
        backLabel.setForeground(Color.WHITE);
        backLabel.setFont(titleFont);
        backLabel.setOpaque(false);

        helpTitle.setBounds(100, 60, 700, 25);
        instructionsLabel.setBounds(100, 90, 785, 600);
        backLabel.setBounds(880, 720, 100, 30);

        helpPage.add(helpTitle);
        helpPage.add(instructionsLabel);
        helpPage.add(backLabel);
    }

    public GradientPanel getPage() {
        return helpPage;
    }

    public JLabel getLabel() {
        return backLabel;
    }
}
