package BankServices;

public class Withdrawal extends Operation {

    public Withdrawal(double amount, double date) {
        super(amount, date);
    }
    @Override
    public String toString() {
        return "Withdrawal: " + date + ", " + amount + " -";
    }
}
