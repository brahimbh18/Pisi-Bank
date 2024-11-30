package PISI.BANK.Pisi.bank.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component

public class Customer implements Serializable {
    
    private int cin;
    private  String firstName, lastName, dateOfBirth, placeOfBirth, maritalStatus, gender, phoneNumber, email, passwdHash;

    public Customer() {}

    public Customer(int cin, String firstName, String lastName, String dateOfBirth, String placeOfBirth, String maritalStatus, String phoneNumber, String email, String passwdHash) {
        this.cin = cin;
        this.firstName= firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.passwdHash = passwdHash;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.maritalStatus = maritalStatus;
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

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}

