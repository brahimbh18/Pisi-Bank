package PISI.BANK.Pisi.bank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

public class Client {
    
    private int cin;
    private  String firstName, lastName, phoneNumber;

    Client(int cin, String firstName, String lastName, String phoneNumber) {
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


}

