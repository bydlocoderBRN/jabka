import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JImageDisplay extends JComponent {
    int w;
    int h;
    private BufferedImage img;
    public JImageDisplay(int width, int height){
        img = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        w = width;
        h = height;
        super.setPreferredSize(new Dimension(w,h));
    };

    @Override
    protected void paintComponent (Graphics g){
        super.paintComponent(g);
        g.drawImage (img, 0, 0, img.getWidth(), img.getHeight(), null);
    }
    public void clearImage (){
        for (int i =0; i<img.getHeight();i++){
            for (int k =0; k<img.getWidth();k++){
                img.setRGB(i,k,0);
            }
        }
    }
    public void drawPixel(int x, int y, int rgbColor){
        img.setRGB(x,y,rgbColor);
    }
}
