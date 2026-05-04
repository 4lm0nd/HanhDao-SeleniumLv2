package testcases;

import base.BaseTest;

import org.seleLv2.common.constant.Constant;
import org.seleLv2.common.enums.Messages;
import org.seleLv2.data.AccountInfo;
import org.seleLv2.dataprovider.PaymentData;
import org.seleLv2.pages.*;
import org.seleLv2.utils.AssertUtils;
import org.seleLv2.utils.LogUtils;
import org.seleLv2.utils.WaitUtils;
import org.testng.annotations.Test;

import static org.seleLv2.elements.Elements.$;

public class TC_03 extends BaseTest {
    String account = Constant.account;
    String password = Constant.password;
    private final MyAccountPage accountPage = new MyAccountPage();
    private final HeaderPage headerPage = new HeaderPage();
    private final ProductList productList = new ProductList();
    private final CartPage cartPage = new CartPage();
    private final CheckOutPage checkOutPage = new CheckOutPage();
    private final OrderStatusPage orderStatusPage = new OrderStatusPage();

    @Test(dataProvider = "paymentMethods", dataProviderClass = PaymentData.class)
    public void TC03(String paymentMethod, String paymentName){
        LogUtils.info("Login with valid credentials");
        headerPage.gotoLoginPage();
        AccountInfo accountInfo = new AccountInfo(account, password);
        accountPage.login(accountInfo);
        LogUtils.info("Pre-conditions: Clear shopping card");
        headerPage.gotoShoppingCard();
        cartPage.removeAllItems();
        LogUtils.info("Go to Shop page and add an item to cart");
        headerPage.gotoShopPage();
        productList.addProductsToCart(1);
        headerPage.gotoShoppingCard();
        WaitUtils.waitForPageLoad(Constant.timeout);
        LogUtils.info("Go to Checkout page and Choose a different payment method");
        cartPage.goCheckOutProcess();
        checkOutPage.selectPaymentMethod(paymentMethod);
        LogUtils.info("Complete the payment process and Verify the order");
        $(checkOutPage.btnPlaceOrder).click();
        WaitUtils.waitForPageLoad(Constant.timeout);
        AssertUtils.assertEquals(orderStatusPage.getMgsOrderConfirmation(), Messages.MSG_ORDER_CONFIRMATION.getMessage());
        AssertUtils.assertContains(orderStatusPage.getPaymentInfo(), paymentName);
    }
}
