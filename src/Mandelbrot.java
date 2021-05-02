import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator{
    public static final int MAX_ITERATIONS = 2000;
    public void getInitialRange(Rectangle2D.Double range) {
        range.setRect(-2,-1.5,3,3);
    }

    public int numIterations(double x, double y){
        int itr = 0;
        Complex Z = new Complex(0,0);
        Complex C = new Complex(x,y);
        while ((Z.getA()*Z.getA() + Z.getBi()* Z.getBi())<4 && itr<MAX_ITERATIONS){
            Z = Z.complexMult(Z).complexSum(C);
            itr++;
        }
        if (itr < 2000){
            return itr;
        }else return -1;
    }
}
