package util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JImagePanel extends JPanel {

    private BufferedImage image;

    public JImagePanel(BufferedImage image){
        this.image = image;
    }

    public JImagePanel(String filePath){
        try{
            image = ImageIO.read(new File(filePath));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image , 0 , 0 , this);
    }
}
