package BankServices;

public class Deposit extends Operation {

    public Deposit(double amount, int date) {
        super(amount, date);
    }
    @Override
    public String toString() {
        return "Deposit: " + date + ", " + amount + " +";
    }
}
