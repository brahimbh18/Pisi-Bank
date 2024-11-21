package PISI.BANK.Pisi.bank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Client {
    
    @Id
    private int cin;
    private int phoneNumber;
    private  String firstName, lastName;

    Client(int cin, String firstName, String lastName, int phoneNumber) {
        this.cin = cin;
        this.firstName= firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public int getCin() {
        return cin;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getNumTel() {
        return phoneNumber;
    }


}

