public class Complex {
    private double a;
    private double b;
    public Complex(double A,double Bi){
        a=A;
        b=Bi;
    }
    public Complex(){
        this(0.0,0.0);
    }
    public void setA(double sa){
        a=sa;
    }
    public void setBi(double sb){
        b=sb;
    }
    public double getA(){
        return this.a;
    }
    public double getBi(){
        return this.b;
    }
    public Complex complexSum(Complex Z){
          Complex sum = new Complex(this.getA()+Z.getA(),this.getBi()+Z.getBi());
          return sum;
    }
    public Complex complexMinus(Complex Z){
        Complex minus = new Complex(this.getA()-Z.getA(),this.getBi()-Z.getBi());
        return minus;
    }
    public Complex complexMult(Complex Z){
        Complex mult = new Complex(this.getA()*Z.getA() - this.getBi()*Z.getBi(),this.getA()*Z.getBi()+this.getBi()*Z.getA());
        return  mult;
    }

}
