package PISI.BANK.Pisi.bank.model;

import java.time.LocalDateTime;

public class BankAccount {
    private int num;
    private int cinClient;
    private String date;
    private double balance;

    public BankAccount() {}

    public BankAccount(int num, int cinClient, float balance) {
        this.num = num;
        this.cinClient = cinClient;
        this.balance = balance;
        this.date = LocalDateTime.now().toString();
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setCinClient(int cinClient) {
        this.cinClient = cinClient;
    }

    public int getCinClient() {
        return cinClient;
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
}