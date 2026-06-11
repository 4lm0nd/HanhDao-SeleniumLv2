package testcases;

import base.BaseTest;
import org.seleLv2.common.constant.Constant;
import org.seleLv2.common.enums.HeaderItems;
import org.seleLv2.data.Billing;
import org.seleLv2.pages.*;
import org.seleLv2.utils.LogUtils;
import org.seleLv2.utils.WaitUtils;
import org.testng.annotations.Test;


public class TC_07 extends BaseTest {

    private final HeaderPage headerPage = new HeaderPage();
    private final ProductList productList = new ProductList();
    private final CartPage cartPage = new CartPage();
    private final CheckOutPage checkOutPage = new CheckOutPage();


@Test
    public void TC07() {
        headerPage.selectHeaderMenu(HeaderItems.TAB_SHOP.getItems());
        productList.addProductsToCart(1,"1");
        WaitUtils.waitForPageLoad(Constant.timeInSecond);
        headerPage.goToShoppingCard();
        cartPage.goCheckOutProcess();

        LogUtils.info("Complete the payment process and Verify the order");
        Billing billing = new Billing("","","","","","","");
        checkOutPage.placeOrder(billing);
        checkOutPage.verifyTextboxHighlighted();
        checkOutPage.verifyErrorMessages();
    }
}