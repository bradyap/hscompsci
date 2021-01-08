public class Fraction {
    private int num, denom;

    public Fraction(int inNum, int inDenom) {
        num = inNum;
        denom = inDenom;
    }

    public int getNum() {
        return num;
    }

    public int getDenom() {
        return denom;
    }

    public Fraction add(Fraction frac) {
        int newNum = num * frac.getDenom() + frac.getNum() * denom;
        int newDenom = denom * frac.getDenom();
        return new Fraction(newNum, newDenom);
    }

    public Fraction subtract(Fraction frac) {
        int newNum = num * frac.getDenom() - frac.getNum() * denom;
        int newDenom = denom * frac.getDenom();
        return new Fraction(newNum, newDenom);
    }

    public Fraction multiply(Fraction frac) {
        int newNum = num * frac.getNum();
        int newDenom = denom * frac.getDenom();
        return new Fraction(newNum, newDenom);
    }

    public Fraction divide(Fraction frac) {
        int newNum = num * frac.getDenom();
        int newDenom = denom * frac.getNum();
        return new Fraction(newNum, newDenom);
    }

    public String toString() {
        return num + "/" + denom;
    }
}