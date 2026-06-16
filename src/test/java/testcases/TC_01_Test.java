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


public class TC_01_Test extends BaseTest {
    String account = Constant.account;
    String password = Constant.password;
    private final AccountPage accountPage = new AccountPage();
    private final HeaderPage headerPage = new HeaderPage();
    private final ManageProduct productList = new ManageProduct();
    private final CartPage cartPage = new CartPage();
    private final CheckOutPage checkOutPage = new CheckOutPage();
    private final OrderStatusPage orderStatusPage = new OrderStatusPage();
    private final String emailTC01 = "TC01" + DataUtils.convertDateToString() + "@yopmail.com";


    String firstname = Constant.firstname;
    String lastname = Constant.lastname;
    String street = Constant.street;
    String town = Constant.town;
    String zipcode = Constant.zipcode;
    String phone = Constant.phone;


    @Test
    public void TC01()  {

        headerPage.selectHeaderMenu(HeaderItems.MY_ACCOUNT.getItems());
        AccountInfo accountInfo = new AccountInfo(account, password);
        accountPage.login(accountInfo);
        LogUtils.info("Pre-conditions: Clear shopping card");
        headerPage.selectHeaderMenu(HeaderItems.SHOPPING_CARD.getItems());
        cartPage.removeAllItems();
        headerPage.hoverAndClickComponent("Electronic Components & Supplies");
        List<ProductInfo> addedProductsToCart = productList.addProductsToCart(1, "1");
        WaitUtils.waitForPageLoad(Constant.timeInSecond);

        headerPage.goToShoppingCard();
        cartPage.verifyCartProducts(addedProductsToCart);
        cartPage.goCheckOutProcess();
        WaitUtils.waitForProcessing("checkout");
        AssertUtils.assertEquals(checkOutPage::getPageHeader,"CHECKOUT");
        checkOutPage.verifyCheckOutProducts(addedProductsToCart);

        Billing billing = new Billing(firstname, lastname, street, town, zipcode, phone, emailTC01);
        checkOutPage.placeOrder(billing);
        WaitUtils.waitForProcessing("order-received");
        LogUtils.info("Verify Order Status page and receipt detail");
        AssertUtils.assertEquals(orderStatusPage::getPageHeader,"ORDER STATUS");
        AssertUtils.assertContains(orderStatusPage::getTableBilling,firstname);
        AssertUtils.assertContains(orderStatusPage::getTableBilling,lastname);
        AssertUtils.assertContains(orderStatusPage::getTableBilling,street);
        AssertUtils.assertContains(orderStatusPage::getTableBilling,town);
        AssertUtils.assertContains(orderStatusPage::getTableBilling,zipcode);
        AssertUtils.assertContains(orderStatusPage::getTableBilling,phone);
        AssertUtils.assertContains(orderStatusPage::getTableBilling,emailTC01);
        orderStatusPage.verifyProductsInOrder(addedProductsToCart);
        AssertUtils.assertEquals(orderStatusPage::getMgsOrderConfirmation, Messages.MSG_ORDER_CONFIRMATION.getMessage());

    }
}
