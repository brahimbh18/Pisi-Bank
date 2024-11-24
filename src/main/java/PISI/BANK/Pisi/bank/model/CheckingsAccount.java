package PISI.BANK.Pisi.bank.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

class CheckingsAccount extends BankAccount{
    private int overdraft;

    CheckingsAccount(int num, int cinClient, float balance, int overdraft) {
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