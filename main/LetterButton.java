import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
// import java.awt.event.MouseListener;

import javax.swing.JButton;

public class LetterButton extends JButton {

	String letter;
	boolean selected;
	int x;
	int y;
	boolean found;

	public LetterButton(String letter, int xPos, int yPos) {
		super(letter);
		this.letter = letter;
		x = xPos;
		y = yPos;
		setPreferredSize(new Dimension(30, 30));
		setMargin(new Insets(0, 0, 0, 0));
		setSelected(false);
		// Color grayColor = new Color(49, 49, 49);
		setFocusPainted(false);
		setBackground(Color.WHITE);
		setForeground(Color.DARK_GRAY);

		// addMouseListener(new MouseAdapter() {
		// @Override
		// public void mouseEntered(MouseEvent e) {
		// if (!selected) {
		// setBackground(Color.LIGHT_GRAY);
		// }
		// }

		// @Override
		// public void mouseExited(MouseEvent e) {
		// if (!selected) {
		// setBackground(Color.WHITE);
		// }
		// }
		// });
		// addActionListener(e -> {
		// setBackground(Color.BLACK);
		// });
	}

	public void toggle() {
		setSelected(!selected);
	}

	public void setLetter(String l) {
		letter = l.substring(0, 1);
		setText(letter);
	}

	public String getLetter() {
		return letter;
	}

	public void setSelected(boolean s) {
		this.selected = s;
		if (s) {
			setBackground(new Color(78, 78, 78));
			setForeground(Color.WHITE);
		} else if (found) {
			setBackground(new Color(57, 255, 20));
			setForeground(Color.DARK_GRAY);
		} else {
			setBackground(Color.WHITE);
			setForeground(Color.DARK_GRAY);
		}
	}

	public boolean getSelected() {
		return selected;
	}

	public int getXPos() {
		return x;
	}

	public void setXPos(int xPos) {
		x = xPos;
	}

	public int getYPos() {
		return y;
	}

	public void setYPos(int yPos) {
		y = yPos;
	}

	public boolean isFoundWord() {
		return found;
	}

	public void setFoundWord(boolean found) {
		this.found = found;
		if (found)
			setBackground(Color.CYAN);
	}
}
