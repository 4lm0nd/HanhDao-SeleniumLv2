package org.seleLv2.data;

public class AccountInfo {
    private String email;
    private String pass;

    public AccountInfo(String mail, String password) {
        this.email = mail;
        this.pass = password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPass() {
        return this.pass;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }

    public void setPassword(String password) {
        this.pass = password;
    }
}
