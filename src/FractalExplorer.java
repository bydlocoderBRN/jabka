public class FractalExplorer {
    public static void main(String[] args){
        Complex A=new Complex(4.2,8.3);
        Complex B=new Complex(9.3,7.6);
        System.out.println(Double.toString(A.complexMult(B).getA()) + " " + Double.toString(A.complexMult(B).getBi()));
    }
}
