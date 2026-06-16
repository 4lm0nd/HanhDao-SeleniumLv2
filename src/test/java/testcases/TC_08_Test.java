package testcases;

import base.BaseTest;
import org.seleLv2.common.constant.Constant;
import org.seleLv2.common.enums.HeaderItems;
import org.seleLv2.data.AccountInfo;
import org.seleLv2.pages.AccountPage;
import org.seleLv2.pages.CartPage;
import org.seleLv2.pages.HeaderPage;
import org.seleLv2.pages.ManageProduct;
import org.testng.annotations.Test;

public class TC_08_Test extends BaseTest {

    String account = Constant.account;
    String password = Constant.password;
    private final AccountPage accountPage = new AccountPage();
    private final HeaderPage headerPage = new HeaderPage();
    private final ManageProduct productList = new ManageProduct();
    private final CartPage cartPage = new CartPage();

    @Test
    public void TC08(){
        headerPage.selectHeaderMenu(HeaderItems.MY_ACCOUNT.getItems());
        AccountInfo accountInfo = new AccountInfo(account, password);
        accountPage.login(accountInfo);
        headerPage.selectHeaderMenu(HeaderItems.TAB_SHOP.getItems());
        productList.addProductsToCart(1,"1");
        headerPage.goToShoppingCard();
        cartPage.clearCard();
        cartPage.verifyShoppingCardIsEmpty();
       }
}
