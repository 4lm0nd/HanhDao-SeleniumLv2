package org.seleLv2.pages;

import org.seleLv2.data.AccountInfo;
import org.seleLv2.utils.UrlUtils;

import static org.seleLv2.elements.Elements.$;

public class AccountPage {

    String txtEmail = "//input[@id='username']";
    String txtPassword = "//input[@id='password']";
    String btnLogin = "//button[@name='login']";
    String txtEmailAddress = "//input[@id='reg_email']";
    String btnRegister = "//button[@name='register']";
    String menuAccount = "//nav[@class='woocommerce-MyAccount-navigation']//a[@href='%s']";


    public void login(AccountInfo accountinfo) {
        $(txtEmail).type(accountinfo.getEmail());
        $(txtPassword).type(accountinfo.getPass());
        $(btnLogin).click();
    }

    public void register(String email){
        $(txtEmailAddress).type(email);
        $(btnRegister).click();
    }

    public void selectMenuAccount(String item){
        String itemLink = String.format(menuAccount, UrlUtils.getUrl(item));
        $(itemLink).scrollTo();
        $(itemLink).click();
    }
}
