package PISI.BANK.Pisi.bank.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component

public class Client implements Serializable {
    
    private int cin;
    private  String firstName, lastName, phoneNumber, email, passwdHash;

    public Client() {}

    public Client(int cin, String firstName, String lastName, String phoneNumber, String email, String passwdHash) {
        this.cin = cin;
        this.firstName= firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.passwdHash = passwdHash;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPasswdHash(String passwdHash) {
        this.passwdHash = passwdHash;
    }

    public String getPasswdHash() {
        return passwdHash;
    }
}

