package testcases;

import base.BaseTest;

import org.seleLv2.common.constant.Constant;
import org.seleLv2.common.enums.Messages;
import org.seleLv2.data.AccountInfo;
import org.seleLv2.data.ProductInfo;
import org.seleLv2.pages.*;
import org.seleLv2.utils.AssertUtils;
import org.seleLv2.utils.LogUtils;
import org.seleLv2.utils.WaitUtils;
import org.testng.annotations.Test;

import java.util.List;

import static org.seleLv2.elements.Elements.$;

public class TC_02 extends BaseTest {
    String account = Constant.account;
    String password = Constant.password;
    private final MyAccountPage accountPage = new MyAccountPage();
    private final HeaderPage headerPage = new HeaderPage();
    private final ProductList productList = new ProductList();
    private final CartPage cartPage = new CartPage();
    private final CheckOutPage checkOutPage = new CheckOutPage();
    private final OrderStatusPage orderStatusPage = new OrderStatusPage();

    @Test
    public void TC02(){
        LogUtils.info("Login with valid credentials");
        headerPage.gotoLoginPage();
        AccountInfo accountInfo = new AccountInfo(account, password);
        accountPage.login(accountInfo);
        LogUtils.info("Pre-conditions: Clear shopping card");
        headerPage.gotoShoppingCard();
        cartPage.removeAllItems();
        LogUtils.info("Go to Shop page");
        headerPage.gotoShopPage();
        LogUtils.info("Add multiple products to cart");
        List <ProductInfo> addedProductsToCart = productList.addProductsToCart(3);
        LogUtils.info("Go to the cart and verify all selected items");
        headerPage.gotoShoppingCard();
        WaitUtils.waitForPageLoad(Constant.timeout);
        cartPage.verifyCartProducts(addedProductsToCart);
        LogUtils.info("Proceed to checkout and confirm order");
        cartPage.goCheckOutProcess();
        $(checkOutPage.btnPlaceOrder).click();
        WaitUtils.waitForPageLoad(Constant.timeout);
        LogUtils.info("All selected items are purchased and order confirmation is received");
        orderStatusPage.verifyProductsInOrder(addedProductsToCart);
        AssertUtils.assertEquals(orderStatusPage.getMgsOrderConfirmation(), Messages.MSG_ORDER_CONFIRMATION.getMessage());
    }
}
