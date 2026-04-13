package org.seleLv2.pages;

import org.seleLv2.elements.Elements;
import org.seleLv2.elements.Textbox;
import org.openqa.selenium.By;

public class MyAccountPage extends BasePage  {
    private final By txtEmail = By.id("username");
    private final By txtPassword = By.id("password");
    private final By btnLogin = By.xpath("//button[@name='login']");

    public void login(String username, String password) {
        Textbox.sendKeys(txtEmail,username);
        Textbox.sendKeys(txtPassword,password);
        Elements.click(btnLogin);
    }
}
