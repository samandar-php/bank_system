package BankServices;

public abstract class Operation {
    double amount;
    double date;

    public Operation(double amount,double date){
        this.amount = amount;
        this.date = date;
    }
    @Override
    public String toString(){
        return super.toString();
    }
}
