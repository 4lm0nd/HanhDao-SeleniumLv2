package testcases;

import base.BaseTest;
import common.constant.Constant;
import elements.Elements;
import org.testng.annotations.Test;
import pageobjects.BasePage;
import pageobjects.HomePage;
import pageobjects.MyAccountPage;


public class TC01  extends BaseTest {
    private final HomePage homePage = new HomePage();
    private final MyAccountPage loginPage = new MyAccountPage();
    private final BasePage basePage = new BasePage();

    @Test
    public void TC001() {
      homePage.GotoLoginPage();
      loginPage.login(Constant.account,Constant.password);
      basePage.hoverAndClickComponent("Electronic Components & Supplies");
    }

}
