package testcases;

import base.BaseTest;

import org.seleLv2.common.constant.Constant;
import org.seleLv2.common.enums.HeaderItems;
import org.seleLv2.common.enums.Messages;
import org.seleLv2.data.AccountInfo;
import org.seleLv2.data.Billing;
import org.seleLv2.dataprovider.PaymentData;
import org.seleLv2.pages.*;
import org.seleLv2.utils.AssertUtils;
import org.seleLv2.utils.DataUtils;
import org.seleLv2.utils.LogUtils;
import org.seleLv2.utils.WaitUtils;
import org.testng.annotations.Test;

public class TC_03 extends BaseTest {
    String account = Constant.account;
    String password = Constant.password;
    private final AccountPage accountPage = new AccountPage();
    private final HeaderPage headerPage = new HeaderPage();
    private final ProductList productList = new ProductList();
    private final CartPage cartPage = new CartPage();
    private final CheckOutPage checkOutPage = new CheckOutPage();
    private final OrderStatusPage orderStatusPage = new OrderStatusPage();
    private final String emailTC03 = "TC03" + DataUtils.convertDateToString() + "@yopmail.com";
    String firstname = Constant.firstname;
    String lastname = Constant.lastname;
    String street = Constant.street;
    String town = Constant.town;
    String zipcode = Constant.zipcode;
    String phone = Constant.phone;

    @Test(dataProvider = "paymentMethods", dataProviderClass = PaymentData.class)
    public void TC03(String paymentMethod, String paymentName){

        headerPage.selectHeaderMenu(HeaderItems.MY_ACCOUNT.getItems());
        AccountInfo accountInfo = new AccountInfo(account, password);
        accountPage.login(accountInfo);

        LogUtils.info("Pre-conditions: Clear shopping card");
        headerPage.selectHeaderMenu(HeaderItems.SHOPPING_CARD.getItems());
        cartPage.removeAllItems();
        headerPage.selectHeaderMenu(HeaderItems.TAB_SHOP.getItems());
        productList.addProductsToCart(1,"1");
        WaitUtils.waitForPageLoad(Constant.timeInSecond);
        headerPage.goToShoppingCard();
        cartPage.goCheckOutProcess();
        checkOutPage.selectPaymentMethod(paymentMethod);

        LogUtils.info("Complete the payment process and Verify the order");
        Billing billing = new Billing(firstname, lastname, street, town, zipcode, phone, emailTC03);
        checkOutPage.placeOrder(billing);
        WaitUtils.waitForProcessing("order-received");
        AssertUtils.assertEquals(orderStatusPage::getMgsOrderConfirmation, Messages.MSG_ORDER_CONFIRMATION.getMessage());
        AssertUtils.assertContains(orderStatusPage::getPaymentInfo, paymentName);
    }
}
