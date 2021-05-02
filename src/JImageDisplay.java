import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JImageDisplay extends JComponent {
    int w;
    int h;
    public JImageDisplay(int width, int height){
        w = width;
        h = height;
        this.setPreferredSize(new Dimension(w,h));
    };
    private BufferedImage img= new BufferedImage(w,h,BufferedImage.TYPE_INT_BGR);
    @Override
    protected void paintComponent (Graphics g){
        super.paintComponent(g);
        g.drawImage (img, 0, 0, img.getWidth(), img.getHeight(), null);
    }
    public void clearImage (){
        for (int i =0; i==img.getHeight();i++){
            for (int k =0; k==img.getWidth();k++){
                img.setRGB(i,k,0);
            }
        }
    }
    public void drawPixel(int x, int y, int rgbColor){
        img.setRGB(x,y,rgbColor);
    }
}
