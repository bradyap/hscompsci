public class FractionDriver {
    public static void main(String[] args) {
        Fraction frac1 = new Fraction(5, 7);
        Fraction frac2 = new Fraction(3, 4);

        System.out.println(frac1.getNum());
        System.out.println(frac1.getDenom());

        System.out.println(frac1.add(frac2));
        System.out.println(frac1.subtract(frac2));
        System.out.println(frac1.multiply(frac2));
        System.out.println(frac1.divide(frac2));

        System.out.println(frac1.toString());
    }
}
