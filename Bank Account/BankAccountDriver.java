public class BankAccountDriver {
    public static void main(String[] args) {
        BankAccount b1 = new BankAccount("John Doe", 305.62);
        System.out.println(b1.toString());
        b1.deposit(59.75);
        System.out.println(b1.toString());
        b1.withdraw(109.12);
        System.out.println(b1.toString());
    }
}
