package testcases;

import base.BaseTest;
import org.seleLv2.common.constant.Constant;
import org.seleLv2.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TC01  extends BaseTest {
    private final HomePage homePage = new HomePage();
    private final MyAccountPage loginPage = new MyAccountPage();
    private final BasePage basePage = new BasePage();
    private final ProductPage productPage = new ProductPage();
    private final CartPage cartPage = new CartPage();

    @Test
    public void TC001() {
      homePage.GotoLoginPage();
      loginPage.login(Constant.account,Constant.password);
      basePage.hoverAndClickComponent("Electronic Components & Supplies");
      productPage.clickRandomProduct();
      homePage.GotoMyCard();
      cartPage.removeAllItems();
        Assert.fail();
    }
}
