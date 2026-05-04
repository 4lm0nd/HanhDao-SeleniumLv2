package org.seleLv2.business;


import org.seleLv2.common.constant.Constant;
import org.seleLv2.common.enums.HeaderItems;
import org.seleLv2.data.Billing;
import org.seleLv2.data.OrderInfo;
import org.seleLv2.pages.*;
import java.util.ArrayList;
import java.util.List;


import static com.codeborne.selenide.Selenide.refresh;
import static org.seleLv2.elements.Elements.$;
import static org.seleLv2.utils.WaitUtils.waitForPageLoad;

public class PlaceOrders {

    private static final HeaderPage headerPage = new HeaderPage();
    private static final ProductList productList = new ProductList();
    private static final CartPage cartPage = new CartPage();
    private static final CheckOutPage checkOutPage = new CheckOutPage();
    private static final OrderStatusPage orderStatusPage = new OrderStatusPage();

    public static List<OrderInfo> placeOrderMultipleTimes (Billing billing, int times) {

        List<OrderInfo> orderInfo = new ArrayList<>();
        {
            for (int i = 0; i < times; i++) {
                headerPage.selectHeaderMenu(HeaderItems.MY_ACCOUNT.getItems());
                productList.addProductsToCart(1);
                waitForPageLoad(Constant.timeout);
                headerPage.selectHeaderMenu(HeaderItems.SHOPPING_CARD.getItems());
                refresh();
                cartPage.goCheckOutProcess();
                checkOutPage.placeOrder(billing);
                waitForPageLoad(Constant.timeout);
                $(orderStatusPage.tableProduct).isVisible();
                String orderNumber = $("//li[@class='woocommerce-order-overview__order order']/strong").text();
                String date = $("//li[@class='woocommerce-order-overview__date date']/strong").text().toUpperCase();
                String total = $("//li[@class='woocommerce-order-overview__total total']//bdi").text();
                orderInfo.add(new OrderInfo(orderNumber, date, total));
            }
            return orderInfo;
        }

    }
}

