package org.seleLv2.data;

public class Billing {

    private String firstname;
    private String lastname;
    private String street;
    private String town;
    private String zipcode;
    private String phone;
    private String email;

    public Billing(String firstname, String lastname,
                   String street, String town, String zipcode,
                   String phone, String email) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.street = street;
        this.town = town;
        this.zipcode = zipcode;
        this.email = email;
        this.phone = phone;

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}

