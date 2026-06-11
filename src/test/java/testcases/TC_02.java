package testcases;

import base.BaseTest;

import org.seleLv2.common.constant.Constant;
import org.seleLv2.common.enums.HeaderItems;
import org.seleLv2.common.enums.Messages;
import org.seleLv2.data.AccountInfo;
import org.seleLv2.data.Billing;
import org.seleLv2.data.ProductInfo;
import org.seleLv2.pages.*;
import org.seleLv2.utils.AssertUtils;
import org.seleLv2.utils.DataUtils;
import org.seleLv2.utils.LogUtils;
import org.seleLv2.utils.WaitUtils;
import org.testng.annotations.Test;

import java.util.List;



public class TC_02 extends BaseTest {
    String account = Constant.account;
    String password = Constant.password;
    private final AccountPage accountPage = new AccountPage();
    private final HeaderPage headerPage = new HeaderPage();
    private final ProductList productList = new ProductList();
    private final CartPage cartPage = new CartPage();
    private final CheckOutPage checkOutPage = new CheckOutPage();
    private final OrderStatusPage orderStatusPage = new OrderStatusPage();
    private final String emailTC02 = "TC02" + DataUtils.convertDateToString() + "@yopmail.com";
    String firstname = Constant.firstname;
    String lastname = Constant.lastname;
    String street = Constant.street;
    String town = Constant.town;
    String zipcode = Constant.zipcode;
    String phone = Constant.phone;

    @Test
    public void TC02(){

        headerPage.selectHeaderMenu(HeaderItems.MY_ACCOUNT.getItems());
        AccountInfo accountInfo = new AccountInfo(account, password);
        accountPage.login(accountInfo);

        LogUtils.info("Pre-conditions: Clear shopping card");
        headerPage.selectHeaderMenu(HeaderItems.SHOPPING_CARD.getItems());
        cartPage.removeAllItems();

        headerPage.selectHeaderMenu(HeaderItems.TAB_SHOP.getItems());
        List <ProductInfo> addedProductsToCart = productList.addProductsToCart(3,"1");
        WaitUtils.waitForPageLoad(Constant.timeInSecond);
        headerPage.goToShoppingCard();
        cartPage.verifyCartProducts(addedProductsToCart);

        LogUtils.info("Proceed to checkout and confirm order");
        cartPage.goCheckOutProcess();
        Billing billing = new Billing(firstname, lastname, street, town, zipcode, phone, emailTC02);
        checkOutPage.placeOrder(billing);
        WaitUtils.waitForPageLoad(Constant.timeInSecond);

        LogUtils.info("All selected items are purchased and order confirmation is received");
        orderStatusPage.verifyProductsInOrder(addedProductsToCart);
        AssertUtils.assertEquals(orderStatusPage::getMgsOrderConfirmation, Messages.MSG_ORDER_CONFIRMATION.getMessage());
    }
}
