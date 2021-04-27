import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator{
    public static final int MAX_ITERATIONS = 2000;
    public void getInitialRange(Rectangle2D.Double range) {
        range.setRect(-2,-1.5,3,3);
    }
    public int numIterations(double x, double y){
        return 0;
    }
}
