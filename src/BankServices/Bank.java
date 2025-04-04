package BankServices;


import library.list.MyArrayList;
import library.list.MyList;

public class Bank {
    private final String name;
    private final Account[] accounts;
    private double totalMoney;
    private final MyArrayList allAccounts;
    private final MyArrayList zeroAccounts;
    private final MyArrayList accountsByBalance;
    private final MyArrayList numberHigher;

    public static final int DEFAULT_CAPACITY = 10;

    private int accountId = 0;

    public Bank(String name){
        this.name = name;
        accounts = new Account[DEFAULT_CAPACITY];
        allAccounts = new MyArrayList();
        zeroAccounts = new MyArrayList();
        numberHigher = new MyArrayList();
        accountsByBalance = new MyArrayList();

    }

    public String getName(){
        return name;
    }

    public int createAccount(String name,int date,double initial){
        accounts[accountId++] = new Account(accountId,name,date,initial);

        allAccounts.add(accounts[accountId - 1]);
        setTotalMoney(initial);

        return accountId;
    }

    public Account deleteAccount(int id, int date){
       if (id <= 0 || id > accountId){
           System.err.println("Error, Noto'g'ri account ID");
           return null;
       }
        accounts[id - 1].setDate(date);
        accounts[id - 1].setBalance(0);
        zeroAccounts.add(accounts[id - 1]);
        return accounts[id - 1];

    }

    public void deposit(int id, int date, double value){
        if(isValid(id)){
            System.err.println("This account is closed");
            return;
        }
        accounts[id - 1].setDate(Math.max(accounts[id - 1].getDate(), date));
        accounts[id - 1].setBalance(accounts[id - 1].getBalance() + value);
        accounts[id - 1].getDeposits().add(new Deposit(value, date));
    }

    public void withdraw(int id,int date,double value){
        if(isValid(id)){
            System.err.println("This account is closed");
            return;
        }
        if(accounts[id - 1].getBalance() < value){
            System.err.println("Insufficient balance");
            return;
        }
        accounts[id - 1].setDate(Math.max(accounts[id - 1].getDate(), date));
        accounts[id - 1].setBalance(accounts[id - 1].getBalance() - value);
        accounts[id - 1].getWithdrawals().add(new Withdrawal(value, date));
    }

    public void transfer(int fromId,int toId,int date,double amount){
        if(isValid(fromId)||isValid(toId)){
            System.err.println("This account closed");
            return;
        } else {
            if (fromId > accountId || toId > accountId || fromId <= 0 || toId <= 0) {
                System.err.println("Error");
                return;
            }

            if (accounts[toId - 1].getBalance() < amount) {
                System.err.println("Error  transfer");
                return;
            }

            accounts[toId - 1].setDate(Math.max(accounts[toId - 1].getDate(),date));
            accounts[fromId - 1].setDate(Math.max(accounts[fromId - 1].getDate(),date));
            accounts[toId - 1].setBalance(accounts[toId - 1].getBalance() - amount);
            accounts[fromId - 1].setBalance(accounts[fromId - 1].getBalance() + amount);
            accounts[fromId - 1].getWithdrawals().add(new Withdrawal(amount, date));
            accounts[toId - 1].getDeposits().add(new Deposit(amount, date));
        }
    }

    public double getTotalDeposit(){
        return totalMoney;
    }

    public void setTotalMoney(double initial){
        this.totalMoney += initial;
    }

    public MyList getAccounts(){
        return allAccounts;
    }

    public Account getAccount(int id){
        return accounts[id - 1];
    }

    public MyList getZeroAccounts(){
        return zeroAccounts;
    }

    public MyList getAccountsByBalance(double low,double high){
        accountsByBalance.clear();
        for (int i = 0; i < accountId; i++) {
            if (accounts[i].getBalance() >= low && accounts[i].getBalance() <= high){
                accountsByBalance.add(accounts[i]);
            }
        }
        return accountsByBalance;
    }

    public long getNumberHigher(double min){
        int count = 0;
        for (int i = 0;i < accountId;i++){
            if (accounts[i].getBalance() >= min){
                count++;
            }
        }
        return count;
    }

    public boolean isValid(int id){
        return accounts[id - 1].getBalance() == 0;
    }
}
