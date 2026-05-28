package testcases;

import base.BaseTest;

import org.seleLv2.common.constant.Constant;
import org.seleLv2.common.enums.HeaderItems;
import org.seleLv2.pages.AccountPage;
import org.seleLv2.pages.CartPage;
import org.seleLv2.pages.HeaderPage;
import org.seleLv2.pages.ProductList;
import org.seleLv2.utils.DataUtils;
import org.seleLv2.utils.WaitUtils;
import org.testng.annotations.Test;


public class TC_09 extends BaseTest {

    private final AccountPage accountPage = new AccountPage();
    private final HeaderPage headerPage = new HeaderPage();
    private final ProductList productList = new ProductList();
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
        WaitUtils.waitForPageLoad(Constant.timeout);
        cartPage.verifyQuantity("2");
        WaitUtils.waitForPageLoad(Constant.timeout);
        cartPage.verifySubTotal("2");
        cartPage.enterQuantity("4");
        WaitUtils.waitForPageLoad(Constant.timeout);
        cartPage.verifyQuantity("4");
        WaitUtils.waitForPageLoad(Constant.timeout);
        cartPage.verifySubTotal("4");
        cartPage.clickMinusBtn();
        WaitUtils.waitForPageLoad(Constant.timeout);
        cartPage.verifyQuantity("3");
        WaitUtils.waitForPageLoad(Constant.timeout);
        cartPage.verifySubTotal("3");
    }
}
