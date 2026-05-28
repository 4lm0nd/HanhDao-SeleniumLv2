package testcases;

import base.BaseTest;
import org.seleLv2.business.PlaceOrders;
import org.seleLv2.common.constant.Constant;
import org.seleLv2.common.enums.HeaderItems;
import org.seleLv2.common.enums.Messages;
import org.seleLv2.data.Billing;
import org.seleLv2.pages.HeaderPage;
import org.seleLv2.pages.OrderStatusPage;
import org.seleLv2.pages.ProductList;
import org.seleLv2.utils.AssertUtils;
import org.seleLv2.utils.DataUtils;
import org.seleLv2.utils.LogUtils;
import org.seleLv2.utils.WaitUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class TC_06 extends BaseTest {
    private final HeaderPage headerPage = new HeaderPage();
    private final ProductList productList = new ProductList();
    private final OrderStatusPage orderStatusPage = new OrderStatusPage();
    private final String emailTC06 = "TC06" + DataUtils.convertDateToString() + "@yopmail.com";
    String firstname = Constant.firstname;
    String lastname = Constant.lastname;
    String street = Constant.street;
    String town = Constant.town;
    String zipcode = Constant.zipcode;
    String phone = Constant.phone;

    @Test
    public void TC06(){
        headerPage.selectHeaderMenu(HeaderItems.TAB_SHOP.getItems());
        Assert.assertTrue(productList.isGridViewActive());
        productList.switchView("list");
        WaitUtils.waitForPageLoad(Constant.timeout);
        Assert.assertTrue(productList.isListViewActive());
        Billing billing = new Billing(firstname, lastname, street, town, zipcode, phone, emailTC06);
        PlaceOrders.placeOrderMultipleTimes(billing,1);
        AssertUtils.assertEquals(orderStatusPage.getMgsOrderConfirmation(), Messages.MSG_ORDER_CONFIRMATION.getMessage(),Constant.shortTime);
    }
}
