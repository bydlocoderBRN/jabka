import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class FractalExplorer {
    private int size_fractal;
    private JImageDisplay content;
    private FractalGenerator fractalGenerator;
    private Rectangle2D.Double rect;
    private JFrame frame;
    private JButton buttonReset;
    private JComboBox combo = new JComboBox();
    private FractalGenerator tricornGenerator;
    public FractalExplorer(int size){
        size_fractal=size;
        fractalGenerator = new Mandelbrot();
        tricornGenerator = new Tricorn();
        content = new JImageDisplay(size_fractal,size_fractal);
        rect = new Rectangle2D.Double();
        fractalGenerator.getInitialRange(rect);


    }
    public void createAndShowGUI(){
        content.setLayout(new BorderLayout());
        frame = new JFrame("Фрактал");
        buttonReset = new JButton("Сброс");
        ActionListener resetlistener = new ResetActionListener();
        buttonReset.addActionListener(resetlistener);
        MouseListener click = new MouseZoomListener();
        content.addMouseListener(click);
        combo.addItem(tricornGenerator);
        combo.addActionListener(resetlistener);
        frame.add(combo, BorderLayout.NORTH);
        frame.add(content, BorderLayout.CENTER);
        frame.add(buttonReset,BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack ();
        frame.setVisible (true);
        frame.setResizable (false);

    }
    private void drawFractal (){
        double xCoord;
        double yCoord;
        float hue;
        int rgbColor;
        for (int x= 0; x <size_fractal; x++){
            for (int y = 0; y < size_fractal; y++){
                xCoord = FractalGenerator.getCoord (rect.x, rect.x + rect.width,
                        size_fractal, x);
                yCoord = FractalGenerator.getCoord (rect.y, rect.y + rect.height,
                        size_fractal, y);
                if (fractalGenerator.numIterations(xCoord,yCoord) == -1)
                    content.drawPixel(x,y,0);
                else {
                    hue = 0.7f + (float) fractalGenerator.numIterations(xCoord,yCoord) / 200f;
                    rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    content.drawPixel(x,y,rgbColor);

                };
            }
        }
        content.repaint();

    }
    class ResetActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonReset){
            fractalGenerator.getInitialRange(rect);
            drawFractal();
            }
            if (e.getSource() == combo){
               if (combo.getSelectedItem() == tricornGenerator){
                   fractalGenerator = tricornGenerator;
                   fractalGenerator.getInitialRange(rect);
                   drawFractal();
               }
            }
        }
    }
    class MouseZoomListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e){
            double xCoord = FractalGenerator.getCoord (rect.x, rect.x + rect.width,
                    size_fractal, e.getX());
            double yCoord = FractalGenerator.getCoord (rect.y, rect.y + rect.height,
                    size_fractal, e.getY());
           fractalGenerator.recenterAndZoomRange(rect,xCoord,yCoord,0.5);
           drawFractal();
        }
    }


    public static void main(String[] args){
       FractalExplorer fractal = new FractalExplorer(800);
       fractal.createAndShowGUI();
       fractal.drawFractal();

    }
}
