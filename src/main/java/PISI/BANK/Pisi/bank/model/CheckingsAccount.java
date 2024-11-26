package PISI.BANK.Pisi.bank.model;



public class CheckingsAccount extends BankAccount{
    private int overdraft;

    public CheckingsAccount() {}

    public CheckingsAccount(int num, int cinClient, float balance, int overdraft) {
        super(num, cinClient, balance);
        this.overdraft = overdraft;
    }

    public void setOverdraft(int overdraft) {
        this.overdraft = overdraft;
    }

    public int getOverdraft() {
        return overdraft;
    }
}