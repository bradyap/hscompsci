public class BankAccount {
    private String name;
    private double bal;

    public BankAccount(String inName, double inBal) {
        name = inName;
        bal =  inBal;
    }

    public String getName() {
        return name;
    }

    public double getBal() {
        return bal;
    }

    public void deposit(double funds) {
        bal += funds;
    }

    public void withdraw(double funds) {
        if (funds <= bal) {
            bal -= funds;
        }
        else {
            System.out.print("Insufficient Funds.");
        }
    }

    public String toString() {
        String output = "There are $" + bal + " in the account registered under " + name + ".";
        return output;
    }
    
}
