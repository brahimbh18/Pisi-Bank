package PISI.BANK.Pisi.bank.model;

public class SavingsAccount extends BankAccount{
    private int interestRate;

    public SavingsAccount() {}

    public SavingsAccount(int num, int cinClient, float balance, int interestRate) {
        this.interestRate = interestRate;
    }

    public void setInterestRate(int taux) {
        this.interestRate= interestRate;
    }

    public int getInterestRate() {
        return interestRate;
    }
}
