package PISI.BANK.Pisi.bank.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

class CheckingsAccount extends BankAccount{
    private int decouverte;

    CheckingsAccount(int num, int cinClient, float balance, int decouverte) {
        super(num, cinClient, balance);
        this.decouverte = decouverte;
    }

    public void setDecouverte(int decouverte) {
        this.decouverte = decouverte;
    }

    public int getDecouverte() {
        return decouverte;
    }
}