package PISI.BANK.Pisi.bank.model;

public class Transaction {
    private int id;
    private long accountNum, receivingAccountNum;
    private String date, type; // type can be 'deposit', 'withdraw', or 'transfer'
    private double amount;

    // In case the transaction is 'transfer', otherwise its null

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(long accountNum) {
        this.accountNum = accountNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public long getReceivingAccountNum() {
        return receivingAccountNum;
    }

    public void setReceivingAccountNum(long receivingAccountNum) {
        this.receivingAccountNum = receivingAccountNum;
    }
}