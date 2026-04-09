package pageobjects;

import elements.Elements;
import elements.Textbox;
import org.openqa.selenium.By;

public class MyAccountPage {
    private final By txtEmail = By.id("username");
    private final By txtPassword = By.id("password");
    private final By btnLogin = By.xpath("//button[@name='login']");

    public void login(String username, String password) {
        Textbox.sendKeys(txtEmail,username);
        Textbox.sendKeys(txtPassword,password);
        Elements.click(btnLogin);
    }
}
