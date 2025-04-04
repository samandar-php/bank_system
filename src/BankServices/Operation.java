package BankServices;

public abstract class Operation {
	protected double amount;
	protected double date;

	public Operation(double amount, double date) {
		this.amount = amount;
		this.date = date;
	}

	@Override
	public abstract String toString();
}
