import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by Jittapan on 1/4/2017.
 */
public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(){
        this(1,1);
    }

    public Fraction(int integer){
        this(integer, 1);
    }

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public String toString(){
        return numerator + "/" + denominator;
    }

    public Fraction add(Fraction val){
        int lcm = lcm(this.getDenominator(), val.getDenominator());
        Fraction f1 = rebase(this, lcm);
		Fraction f2 = rebase(val, lcm);
        return new Fraction(f1.getNumerator()+f2.getNumerator(), f1.getDenominator());
    }

    public Fraction subtract(Fraction val){
        return add(new Fraction(val.getNumerator()*-1, val.getDenominator()));
    }

    public Fraction multiply(Fraction val){
        return new Fraction(this.getNumerator()*val.getNumerator(), this.getDenominator()*val.getDenominator());
    }

    public Fraction divide(Fraction val){
        return multiply(new Fraction(val.getDenominator(), val.getNumerator()));
    }

    public Fraction reduce(){
        int gcd = gcd(this.getNumerator(),this.getDenominator());
        if(gcd == 1) return this;
        return new Fraction(this.getNumerator()/gcd, this.getDenominator()/gcd);

    }

    private static Fraction rebase(Fraction val, int lcm){
        int multiplier = lcm/val.getDenominator();
        return new Fraction(val.getNumerator() * multiplier, val.getDenominator()*multiplier);
    }

    private static int gcd(int x, int y) {
        BigInteger a = BigInteger.valueOf(x);
        return a.gcd(BigInteger.valueOf(y)).intValue();
        //return (y == 0) ? x : gcd(y, x % y);
    }

    public static int lcm(int... numbers) {
        return Arrays.stream(numbers).reduce(1, (x, y) -> x * (y / gcd(x, y)));
    }
}
