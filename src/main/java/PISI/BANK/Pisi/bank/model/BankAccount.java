package PISI.BANK.Pisi.bank.model;

import java.time.LocalDateTime;



public class BankAccount {
    private int num;
    private int cinClient;
    private String date;
    private float balance;

    BankAccount(int num, int cinClient, float balance) {
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

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getBalance() {
        return balance;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}