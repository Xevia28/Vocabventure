import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

class LogoPanel extends JPanel {
    BufferedImage image;

    public LogoPanel(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            int containerWidth = getWidth();
            int containerHeight = getHeight();

            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();

            double widthRatio = (double) containerWidth / imageWidth;
            double heightRatio = (double) containerHeight / imageHeight;

            double scaleFactor = Math.min(widthRatio, heightRatio);

            int scaledWidth = (int) (imageWidth * scaleFactor);
            int scaledHeight = (int) (imageHeight * scaleFactor);

            int x = (containerWidth - scaledWidth) / 2;
            int y = (containerHeight - scaledHeight) / 2;

            g.drawImage(image, x, y, scaledWidth, scaledHeight, this);
        }
    }
}
