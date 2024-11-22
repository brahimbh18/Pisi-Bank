package PISI.BANK.Pisi.bank.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

class SavingsAccount extends BankAccount{
    private int taux;

    SavingsAccount(int num, int cinClient, float balance, int taux) {
        super(num, cinClient, balance);
        this.taux = taux;
    }

    public void setTaux(int taux) {
        this.taux = taux;
    }

    public int getTaux() {
        return taux;
    }
}
