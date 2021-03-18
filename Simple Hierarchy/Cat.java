public class Cat extends Pet {
    public Cat(String n, int a, double w) {
        super(n, a, w);
    }

    public void purr() {
        System.out.println(super.getName() + "is purring.");
    }

    public void scratch() {
        System.out.println(super.getName() + "is scratching.");
    }
}
