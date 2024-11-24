package PISI.BANK.Pisi.bank.model;

public class Transaction {
    private int id, accountNum;
    private String date, type; // type can be 'deposit', 'withdraw', or 'transfer'
    private double amount;

    // In case the transaction is 'transfer', otherwise its null
    private Integer targetAccountNum;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }
}