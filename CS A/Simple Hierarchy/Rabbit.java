public class Rabbit extends SmallAnimal {
    public Rabbit(String n, int a, double w) {
        super(n, a, w);
    }

    public void thump() {
        System.out.println(getName() + "is thumping.");
    }
}
