package testcases;

import base.BaseTest;
import org.seleLv2.common.constant.Constant;
import org.seleLv2.data.AccountInfo;
import org.seleLv2.pages.*;
import org.seleLv2.utils.AssertUtils;
import org.seleLv2.utils.WaitUtils;
import org.testng.annotations.Test;



public class TC001 extends BaseTest {
    String account = Constant.account;
    String password = Constant.password;
    private final MyAccountPage accountPage = new MyAccountPage();
    private final HeaderPage headerPage = new HeaderPage();
    private final ProductPage productPage = new ProductPage();
    private final CartPage cartPage = new CartPage();
    private final CheckOutPage checkOutPage = new CheckOutPage();
    private final OrderStatusPage orderStatusPage = new OrderStatusPage();


    String firstname = Constant.firstname;
    String lastname = Constant.lastname;
    String street = Constant.street;
    String town = Constant.town;
    String zipcode = Constant.zipcode;
    String phone = Constant.phone;
    String email = Constant.email;

    @Test
    public void TC01()  {
        headerPage.GotoLoginPage();
        AccountInfo accountInfo = new AccountInfo(account, password);
        accountPage.login(accountInfo);
        headerPage.GotoShoppingCard();
        cartPage.removeAllItems();
        headerPage.hoverAndClickComponent("Electronic Components & Supplies");
        productPage.clickProduct();
        String productName = productPage.getProductName();
        String productPrice = productPage.getProductPrice();
        headerPage.GotoShoppingCard();
        AssertUtils.assertContains(cartPage.getFirstRowValueByColumnName("product-details"),productName);
        AssertUtils.assertContains(cartPage.getFirstRowValueByColumnName("product-price"),productPrice);
        cartPage.goCheckOutProcess();
        AssertUtils.assertEquals(checkOutPage.getPageHeader(),"CHECKOUT");
        AssertUtils.assertContains(checkOutPage.getProductName(),productName);
        AssertUtils.assertContains(checkOutPage.getProductPrice(),productPrice);
        checkOutPage.placeOrder(firstname, lastname, street, town, zipcode, phone, email);
        WaitUtils.waitForPageLoad(Constant.timeout);
        AssertUtils.assertEquals(orderStatusPage.getPageHeader(),"ORDER STATUS");
        AssertUtils.assertContains(orderStatusPage.getTableBilling(),firstname);
        AssertUtils.assertContains(orderStatusPage.getTableBilling(),lastname);
        AssertUtils.assertContains(orderStatusPage.getTableBilling(), street);
        AssertUtils.assertContains(orderStatusPage.getTableBilling(), town);
        AssertUtils.assertContains(orderStatusPage.getTableBilling(),zipcode);
        AssertUtils.assertContains(orderStatusPage.getTableBilling(),phone);
        AssertUtils.assertContains(orderStatusPage.getTableBilling(),email);
        AssertUtils.assertContains(orderStatusPage.getTableProduct(),productName);
        AssertUtils.assertContains(orderStatusPage.getTableProduct(),productPrice);
    }
}
