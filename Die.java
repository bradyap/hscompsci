public class Die {
    private int side = 0;

    public Die() {
        roll();
    }
    
    public void roll() {
        side = (int)(Math.random()*6) + 1;
    }

    public int getSide() {
        return side;
    }

}
