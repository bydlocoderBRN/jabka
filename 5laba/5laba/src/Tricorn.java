import java.awt.geom.Rectangle2D;

public class Tricorn extends FractalGenerator{
    public static final int MAX_ITERATIONS = 2000;
    public void getInitialRange(Rectangle2D.Double range) {
        range.setFrame(-2,-2,4,4);
    }

    public int numIterations(double x, double y){
        int itr = 0;
        Complex Z = new Complex(0,0);
        Complex C = new Complex(x,y);

        while ((Z.getA()*Z.getA() + Z.getBi()* Z.getBi())<4 && itr<MAX_ITERATIONS){
            Z.setBi(-Z.getBi());
            Z = Z.complexMult(Z).complexSum(C);
            itr++;
        }
        if (itr < 2000){
            return itr;
        }else return -1;
    }

    @Override
    public String toString() {
        return "Tricorn";
    }
}

