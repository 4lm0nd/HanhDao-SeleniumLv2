package testcases;

import base.BaseTest;
import org.seleLv2.business.PlaceOrders;
import org.seleLv2.common.constant.Constant;
import org.seleLv2.common.enums.AccountMenuItems;
import org.seleLv2.common.enums.HeaderItems;
import org.seleLv2.data.Billing;
import org.seleLv2.data.OrderInfo;
import org.seleLv2.pages.*;
import org.seleLv2.utils.DateUtils;
import org.seleLv2.utils.LogUtils;
import org.testng.annotations.Test;

import java.util.List;

public class TC_05 extends BaseTest {
    private final String emailTC05 = "TC05" + DateUtils.convertDateToString() + "@yopmail.com";
    String firstname = Constant.firstname;
    String lastname = Constant.lastname;
    String street = Constant.street;
    String town = Constant.town;
    String zipcode = Constant.zipcode;
    String phone = Constant.phone;
    String email = Constant.email;

    private final AccountPage accountPage = new AccountPage();
    private final HeaderPage headerPage = new HeaderPage();
    private final OrdersHistoryPage ordersTablePage = new OrdersHistoryPage();


    @Test
    public void TC05(){
        LogUtils.info("Login with valid credentials");
        headerPage.selectHeaderMenu(HeaderItems.MY_ACCOUNT.getItems());
        accountPage.register(emailTC05);
        LogUtils.info("Place Orders");
        Billing billing = new Billing(firstname, lastname, street, town, zipcode, phone, emailTC05);
        List<OrderInfo> orderInfoList = PlaceOrders.placeOrderMultipleTimes(billing,2);
        LogUtils.info(" Go to My Account page");
        headerPage.selectHeaderMenu(HeaderItems.MY_ACCOUNT.getItems());
        LogUtils.info("Click on Orders in left navigation");
        accountPage.selectOrderItem(AccountMenuItems.ORDER.getItems());
        LogUtils.info("Verify Order Detail");
        ordersTablePage.verifyOrderInfo(orderInfoList);
    }
}
