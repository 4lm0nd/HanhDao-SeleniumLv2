package testcases;

import base.BaseTest;

import org.seleLv2.common.constant.Constant;
import org.seleLv2.common.enums.HeaderItems;
import org.seleLv2.common.enums.Messages;
import org.seleLv2.data.Billing;
import org.seleLv2.data.ProductInfo;
import org.seleLv2.pages.*;
import org.seleLv2.utils.AssertUtils;
import org.seleLv2.utils.DateUtils;
import org.seleLv2.utils.LogUtils;
import org.seleLv2.utils.WaitUtils;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.refresh;

public class TC_02 extends BaseTest {
    String account = Constant.account;
    String password = Constant.password;
    private final AccountPage accountPage = new AccountPage();
    private final HeaderPage headerPage = new HeaderPage();
    private final ProductList productList = new ProductList();
    private final CartPage cartPage = new CartPage();
    private final CheckOutPage checkOutPage = new CheckOutPage();
    private final OrderStatusPage orderStatusPage = new OrderStatusPage();
    private final String emailTC02 = "TC02" + DateUtils.convertDateToString() + "@yopmail.com";
    String firstname = Constant.firstname;
    String lastname = Constant.lastname;
    String street = Constant.street;
    String town = Constant.town;
    String zipcode = Constant.zipcode;
    String phone = Constant.phone;

    @Test
    public void TC02(){
        LogUtils.info("Login with valid credentials");
        headerPage.selectHeaderMenu(HeaderItems.MY_ACCOUNT.getItems());
        accountPage.register(emailTC02);
        LogUtils.info("Go to Shop page");
        headerPage.selectHeaderMenu(HeaderItems.TAB_SHOP.getItems());
        LogUtils.info("Add multiple products to cart");
        List <ProductInfo> addedProductsToCart = productList.addProductsToCart(3);
        WaitUtils.waitForPageLoad(Constant.timeout);
        LogUtils.info("Go to the cart and verify all selected items");
        headerPage.selectHeaderMenu(HeaderItems.SHOPPING_CARD.getItems());
        refresh();
        cartPage.verifyCartProducts(addedProductsToCart);
        LogUtils.info("Proceed to checkout and confirm order");
        cartPage.goCheckOutProcess();
        Billing billing = new Billing(firstname, lastname, street, town, zipcode, phone, emailTC02);
        checkOutPage.placeOrder(billing);
        WaitUtils.waitForPageLoad(Constant.timeout);
        LogUtils.info("All selected items are purchased and order confirmation is received");
        orderStatusPage.verifyProductsInOrder(addedProductsToCart);
        AssertUtils.assertEquals(orderStatusPage.getMgsOrderConfirmation(), Messages.MSG_ORDER_CONFIRMATION.getMessage());
    }
}
