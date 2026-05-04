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


public class TC_01 extends BaseTest {
    String account = Constant.account;
    String password = Constant.password;
    private final AccountPage accountPage = new AccountPage();
    private final HeaderPage headerPage = new HeaderPage();
    private final ProductList productList = new ProductList();
    private final CartPage cartPage = new CartPage();
    private final CheckOutPage checkOutPage = new CheckOutPage();
    private final OrderStatusPage orderStatusPage = new OrderStatusPage();
    private final String emailTC01 = "TC01" + DateUtils.convertDateToString() + "@yopmail.com";


    String firstname = Constant.firstname;
    String lastname = Constant.lastname;
    String street = Constant.street;
    String town = Constant.town;
    String zipcode = Constant.zipcode;
    String phone = Constant.phone;
    String email = Constant.email;

    @Test
    public void TC01()  {
        LogUtils.info("Login with valid credentials");
        headerPage.selectHeaderMenu(HeaderItems.MY_ACCOUNT.getItems());
        accountPage.register(emailTC01);
        LogUtils.info(" Hover over the All departments section then click Electronic Components & Supplies");
        headerPage.hoverAndClickComponent("Electronic Components & Supplies");
        LogUtils.info("Add a randomly product to cart");
        List<ProductInfo> addedProductsToCart = productList.addProductsToCart(1);
        WaitUtils.waitForPageLoad(Constant.timeout);
        headerPage.selectHeaderMenu(HeaderItems.SHOPPING_CARD.getItems());
        LogUtils.info("Verify product information in the cart");
        cartPage.verifyCartProducts(addedProductsToCart);
        LogUtils.info("Click on 'Proceed to checkout'");
        cartPage.goCheckOutProcess();
        LogUtils.info("Verify Checkout page and item details");
        AssertUtils.assertEquals(checkOutPage.getPageHeader(),"CHECKOUT");
        checkOutPage.verifyCheckOutProducts(addedProductsToCart);
        LogUtils.info("Fill the billing details and place order");
        Billing billing = new Billing(firstname, lastname, street, town, zipcode, phone, emailTC01);
        checkOutPage.placeOrder(billing);
        WaitUtils.waitForPageLoad(Constant.timeout);
        LogUtils.info("Verify Order Status page and receipt detail");
        AssertUtils.assertEquals(orderStatusPage.getPageHeader(),"ORDER STATUS");
        AssertUtils.assertContains(orderStatusPage.getTableBilling(),firstname);
        AssertUtils.assertContains(orderStatusPage.getTableBilling(),lastname);
        AssertUtils.assertContains(orderStatusPage.getTableBilling(), street);
        AssertUtils.assertContains(orderStatusPage.getTableBilling(), town);
        AssertUtils.assertContains(orderStatusPage.getTableBilling(),zipcode);
        AssertUtils.assertContains(orderStatusPage.getTableBilling(),phone);
        AssertUtils.assertContains(orderStatusPage.getTableBilling(),emailTC01);
        orderStatusPage.verifyProductsInOrder(addedProductsToCart);
        AssertUtils.assertEquals(orderStatusPage.getMgsOrderConfirmation(), Messages.MSG_ORDER_CONFIRMATION.getMessage());
    }
}
