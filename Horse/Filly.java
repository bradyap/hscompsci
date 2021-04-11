public class Filly extends Horse {
    private int power;

    public Filly() {
        super();
        power = random(30, 90);
    }

    public Filly(int loc, int index, int size, char name) {
        super(loc, index, size, name);
        power = random(30, 90);
    }

    private int random(int min, int max){
        int range = (max - min) + 1;     
        return (int)(Math.random() * range) + min;
    }

    public void raceStride() {
        if(((int)(Math.random()*100) + 1) < power) {
            advance();
        }
    }

    public String toString() {
        return super.toString() + "*";
    }
}
