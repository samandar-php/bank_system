package BankServices;

import library.list.MyArrayList;
import library.list.MyList;

public class Account {
    private final String owner;
    private int date;
    private double balance;
    private final int accountNumber;
    private final MyList deposits;
    private final MyList withdrawals;
    private final MyList operations;

    public Account(int accountNumber,String owner, int date, double balance){
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.date = date;
        this.balance = balance;
        this.deposits = new MyArrayList();
        this.withdrawals = new MyArrayList();
        this.operations = new MyArrayList();

    }

    @Override
    public String toString(){
        return accountNumber + "," + owner + "," + date + "," + balance;
    }

    public MyList getMovements(){
        return operations;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public int getDate(){
        return date;
    }

    public void setDate(int date){
        if (this.date < date){
            this.date = date;
        }
    }


    public MyList getDeposits(){
        return deposits;
    }

    public void setAmountDeposites(double amount){
        deposits.add(new Deposit(amount,date));
        operations.add(new Deposit(amount,date));
        balance += amount;
    }

    public void setAmountWithdraw(double amount){
        withdrawals.add(new Withdrawal(amount,date));
        operations.add(new Withdrawal(amount,date));
        balance -= amount;

    }

    public MyList getWithdrawals(){
        return withdrawals;
    }


}
