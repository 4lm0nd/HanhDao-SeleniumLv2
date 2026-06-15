package testcases;

import base.BaseTest;

import org.seleLv2.common.enums.HeaderItems;
import org.seleLv2.pages.AccountPage;
import org.seleLv2.pages.CartPage;
import org.seleLv2.pages.HeaderPage;
import org.seleLv2.pages.ManageProduct;
import org.seleLv2.utils.DataUtils;
import org.seleLv2.utils.WaitUtils;
import org.testng.annotations.Test;


public class TC_09_Test extends BaseTest {

    private final AccountPage accountPage = new AccountPage();
    private final HeaderPage headerPage = new HeaderPage();
    private final ManageProduct productList = new ManageProduct();
    private final CartPage cartPage = new CartPage();
    private final String emailTC09 = "TC09" + DataUtils.convertDateToString() + "@yopmail.com";

    @Test
    public void TC09(){
        headerPage.selectHeaderMenu(HeaderItems.MY_ACCOUNT.getItems());
        accountPage.register(emailTC09);
        headerPage.selectHeaderMenu(HeaderItems.TAB_SHOP.getItems());
        productList.addProductsToCart(1,"1");
        headerPage.goToShoppingCard();

        cartPage.verifyQuantity("1");

        cartPage.clickPlusBtn();
        WaitUtils.waitForElementUpdate();
        cartPage.verifyQuantity("2");
        cartPage.verifySubTotal("2");

        cartPage.enterQuantity("4");
        WaitUtils.waitForElementUpdate();
        cartPage.verifyQuantity("4");
        cartPage.verifySubTotal("4");

        cartPage.clickMinusBtn();
        WaitUtils.waitForElementUpdate();
        cartPage.verifyQuantity("3");
        cartPage.verifySubTotal("3");
    }
}
