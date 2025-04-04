package BankServices;

public class Account {
	private final int accountNumber;
	private final String ownerName;
	private int date;
	private double deposit;
	private final MyList deposits;
	private final MyList withdrawals;
	private final MyList operations;

	public Account(int accountNumber, String ownerName, int date, double deposit) {
		this.accountNumber = accountNumber;
		this.ownerName = ownerName;
		this.date = date;
		this.deposit = deposit;

		this.deposits = new MyList();
		this.withdrawals = new MyList();
		this.operations = new MyList();

		Deposit initialDeposit = new Deposit(deposit, date);
		operations.add(initialDeposit);
		deposits.add(initialDeposit);
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		if (this.date < date) {
			this.date = date;
		}
	}

	public double getDeposite() {
		return deposit;
	}

	public void setAmountDeposits(double amount) {
		Deposit newDeposit = new Deposit(amount, this.date);
		operations.add(newDeposit);
		deposits.add(newDeposit);
		this.deposit += amount;
	}

	public void setAmountWithdraw(double amount) {
		Withdrawal newWithdrawal = new Withdrawal(amount, this.date);
		operations.add(newWithdrawal);
		withdrawals.add(newWithdrawal);
		this.deposit -= amount;
	}

	public MyList getDeposits() {
		return deposits;
	}

	public MyList getWithdrawals() {
		return withdrawals;
	}

	public MyList getMovements() {
		return operations;
	}

	@Override
	public String toString() {
		return "Account[" +
				accountNumber +
				", " + ownerName +
				", " + date +
				", " + deposit +
				']';
	}
}
