public class State {
    private String name, abbrev, capital;
    public State(String inName, String inAbbrev, String inCapital) {
        name = inName;
        abbrev = inAbbrev;
        capital = inCapital;
    }
    public String getName() {
        return name;
    }
    public String getAbbrev() {
        return abbrev;
    }
    public String getCapital() {
        return capital;
    }
    public String toString() {
        return name + "\n" + abbrev + "\n" + capital;
    }
}
