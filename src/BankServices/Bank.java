package BankServices;

public class Bank {
    private final String name;
    private final Account[] accounts;
    public static final int count = 5;
    private int accountCount = 0;
    private double totalMoney;
    private final MyList allAccounts;
    private final MyList zeroAccounts;
    private final MyList accountsByBalance;
    private final MyList numberHigher;

    public Bank(String name) {
        this.name = name;
        accounts = new Account[count];
        allAccounts = new MyList();
        zeroAccounts = new MyList();
        accountsByBalance = new MyList();
        numberHigher = new MyList();
    }

    public String getName() {
        return name;
    }

    public int createAccount(String ownerName, int date, double deposit) {
        accounts[accountCount] = new Account(accountCount + 1, ownerName, date, deposit);
        allAccounts.add(accounts[accountCount]);
        setTotalMoney(deposit);
        return ++accountCount;
    }

    public Account getAccount(int id) {
        if (id > accountCount || id <= 0) {
            System.err.println("Invalid account ID.");
            return null;
        }

        if (close(id)) {
            System.err.println("This account is closed.");
            System.err.println("This account was closed on: " + accounts[id - 1].getDate());
            return null;
        }

        return accounts[id - 1];
    }

    public void deposit(int id, int date, double amount) {
        if (id > accountCount || id <= 0) {
            System.err.println("Invalid account ID.");
            return;
        }

        if (close(id)) {
            System.err.println("This account is closed.");
            return;
        }

        setTotalMoney(amount);
        accounts[id - 1].setDate(date);
        accounts[id - 1].setAmountDeposits(amount);
    }

    public void withdraw(int id, int date, double amount) {
        if (id > accountCount || id <= 0) {
            System.err.println("Invalid account ID.");
            return;
        }

        if (close(id)) {
            System.err.println("This account is closed.");
            return;
        }

        if (amount > accounts[id - 1].getDeposite()) {
            System.err.println("Withdrawal amount exceeds current balance.");
            return;
        }

        if (accounts[id - 1].getDate() > date) {
            date = accounts[id - 1].getDate();
        }

        accounts[id - 1].setDate(date);
        accounts[id - 1].setAmountWithdraw(amount);
    }

    public void transfer(int id, int transferId, int date, double amount) {
        if (id > accountCount || transferId > accountCount || id <= 0 || transferId <= 0) {
            System.err.println("Invalid account ID(s).");
            return;
        }

        if (close(id) || close(transferId)) {
            System.err.println("One or both accounts are closed.");
            return;
        }

        if (accounts[id - 1].getDeposite() < amount) {
            System.err.println("Insufficient funds for transfer.");
            return;
        }

        accounts[id - 1].setDate(date);
        accounts[transferId - 1].setDate(date);
        accounts[id - 1].setAmountWithdraw(amount);
        accounts[transferId - 1].setAmountDeposits(amount);
    }

    public Account deleteAccount(int id, int date) {
        if (id > accountCount || id <= 0) {
            System.err.println("Invalid account ID.");
            return null;
        }

        if (close(id)) {
            System.err.println("This account is already closed.");
            return accounts[id - 1];
        }

        accounts[id - 1].setDate(date);
        accounts[id - 1].setAmountWithdraw(accounts[id - 1].getDeposite());
        zeroAccounts.add(accounts[id - 1]);
        return accounts[id - 1];
    }

    public double getTotalDeposit() {
        return totalMoney;
    }

    public void setTotalMoney(double amount) {
        this.totalMoney += amount;
    }

    public boolean close(int id) {
        return accounts[id - 1].getDeposite() == 0;
    }

    public MyList getAccounts() {
        return allAccounts;
    }
    public MyList getAccountsByBalance(double lower, double upper) {
        for (int i = 0; i < accountCount; i++) {
            double balance = accounts[i].getDeposite();
            if (balance > lower && balance < upper) {
                accountsByBalance.add(accounts[i]);
            }
        }
        return accountsByBalance;
    }

    public MyList getNumberHigher(double amount) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getDeposite() >= amount) {
                numberHigher.add(accounts[i]);
            }
        }
        return numberHigher;
    }
}
