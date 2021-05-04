import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.FileFilter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class FractalExplorer {
    private int size_fractal;
    private JImageDisplay content;
    private FractalGenerator fractalGenerator;
    private FractalGenerator mandelbrot;
    private FractalGenerator tricorn;
    private FractalGenerator burningship;
    private Rectangle2D.Double rect;
    private JFrame frame;
    private JButton buttonReset;
    private JButton buttonSave;
    private JComboBox<Object> combo;
    private JPanel panelNorth;
    private JPanel panelSouth;
    private JLabel labelCmb;
    private JFileChooser save = new JFileChooser();
    private FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
    public FractalExplorer(int size){
        size_fractal=size;

        fractalGenerator = new Mandelbrot();
        mandelbrot = new Mandelbrot();
        tricorn = new Tricorn();
        burningship = new BurningShip();
        content = new JImageDisplay(size_fractal,size_fractal);
        rect = new Rectangle2D.Double();
        fractalGenerator.getInitialRange(rect);

    }
    public void createAndShowGUI(){
        content.setLayout(new BorderLayout());
        frame = new JFrame("Фрактал");
        combo = new JComboBox<Object>();
        buttonReset = new JButton("Сброс");
        buttonSave = new JButton("Сохранить изображение");
        panelNorth = new JPanel();
        panelSouth = new JPanel();
        labelCmb = new JLabel("Fractal:");
        ActionListener resetlistener = new ResetActionListener();
        buttonReset.addActionListener(resetlistener);
        buttonSave.addActionListener(resetlistener);
        MouseListener click = new MouseZoomListener();
        content.addMouseListener(click);
        combo.addItem(mandelbrot);
        combo.addItem(tricorn);
        combo.addItem(burningship);
        combo.addActionListener(resetlistener);
        panelNorth.add(labelCmb);
        panelNorth.add(combo);
        panelSouth.add(buttonSave);
        panelSouth.add(buttonReset);
        frame.add(panelNorth, BorderLayout.NORTH);
        frame.add(panelSouth, BorderLayout.SOUTH);
        frame.add(content, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack ();
        frame.setVisible (true);
        frame.setResizable (false);
        save.setFileFilter(filter);
        save.setAcceptAllFileFilterUsed(false);
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
            if (e.getSource() == buttonSave){
                if (save.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION){
                    File filesave = new File(save.getSelectedFile().getPath() + ".png");
                    try{
                    ImageIO.write(content.getImg(),"png",filesave);}
                    catch (IOException er){
                        JOptionPane.showMessageDialog(frame,er,"Ошибка",JOptionPane.ERROR_MESSAGE);

                    }
                }

            }
            if (e.getSource() == combo){
               if (combo.getSelectedItem() == tricorn){
                   fractalGenerator = tricorn;
                   fractalGenerator.getInitialRange(rect);
                   drawFractal();
               }
                if (combo.getSelectedItem() == mandelbrot){
                    fractalGenerator = mandelbrot;
                    fractalGenerator.getInitialRange(rect);
                    drawFractal();
                }
                if (combo.getSelectedItem() == burningship){
                    fractalGenerator = burningship;
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
