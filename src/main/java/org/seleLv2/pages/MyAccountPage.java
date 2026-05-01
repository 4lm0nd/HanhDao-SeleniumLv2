package org.seleLv2.pages;

import org.openqa.selenium.By;
import org.seleLv2.data.AccountInfo;

import static org.seleLv2.elements.Elements.$;

public class MyAccountPage {

    String txtEmail = "//input[@id='username']";
    String txtPassword = "//input[@id='password']";
    String btnLogin = "//button[@name='login']";

    public void login(AccountInfo accountinfo) {
        $(txtEmail).type(accountinfo.getEmail());
        $(txtPassword).type(accountinfo.getPass());
        $(btnLogin).click();
    }
}
