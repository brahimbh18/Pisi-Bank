package PISI.BANK.Pisi.bank.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

class SavingsAccount extends BankAccount{
    private int interestRate;

    SavingsAccount(int num, int cinClient, float balance, int interestRate) {
        super(num, cinClient, balance);
        this.interestRate = interestRate;
    }

    public void setInterestRate(int taux) {
        this.interestRate= interestRate;
    }

    public int getInterestRate() {
        return interestRate;
    }
}
