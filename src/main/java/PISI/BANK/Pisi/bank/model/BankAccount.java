package PISI.BANK.Pisi.bank.model;

import java.time.LocalDateTime;

public class BankAccount {
    private int num;
    private int customerCin;
    private String date;
    private double balance;
    String type;
    private int overdraft, interestRate;
    private String code;

    public BankAccount() {
        this.balance = 0;
        this.date = LocalDateTime.now().toString();
    }

    public BankAccount(int customerCin, String type, int overdraft, int interestRate, String code) {
        //this.num = num;
        this.customerCin = customerCin;
        this.balance = 0;
        this.date = LocalDateTime.now().toString();
        this.type = type;
        this.overdraft = overdraft;
        this.interestRate = interestRate;
        this.code = code;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setCustomerCin(int customerCin) {
        this.customerCin = customerCin;
    }

    public int getCustomerCin() {
        return customerCin;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setOverdraft(int overdraft) {
        this.overdraft = overdraft;
    }

    public int getOverdraft() {
        return overdraft;
    }
    public void setInterestRate(int interestRate) {
        this.interestRate= interestRate;
    }

    public int getInterestRate() {
        return interestRate;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "num=" + this.num +
                ", customerCin=" + this.customerCin +
                ", date='" + this.date + '\'' +
                ", balance=" + this.balance +
                ", type='" + this.type + '\'' +
                ", overdraft=" + this.overdraft +
                ", interestRate=" + this.interestRate +
                '}';
    }

}