package org.seleLv2.business;

import org.seleLv2.common.constant.Constant;
import org.seleLv2.common.enums.HeaderItems;
import org.seleLv2.data.Billing;
import org.seleLv2.data.OrderInfo;
import org.seleLv2.drivers.DriverManager;
import org.seleLv2.pages.*;
import org.seleLv2.utils.WaitUtils;

import java.util.ArrayList;
import java.util.List;


import static org.seleLv2.elements.Element.$;
import static org.seleLv2.utils.WaitUtils.waitForPageLoad;

public class PlaceOrders {

    private static final HeaderPage headerPage = new HeaderPage();
    private static final ManageProduct productList = new ManageProduct();
    private static final CartPage cartPage = new CartPage();
    private static final CheckOutPage checkOutPage = new CheckOutPage();
    private static final OrderStatusPage orderStatusPage = new OrderStatusPage();

    public static List<OrderInfo> placeOrderProcess(Billing billing, int times) {
        String quantity ="1";
        List<OrderInfo> orderInfo = new ArrayList<>();
        {
            for (int i = 0; i < times; i++) {
                headerPage.selectHeaderMenu(HeaderItems.TAB_SHOP.getItems());
                productList.addProductsToCart(1,quantity);
                waitForPageLoad(Constant.timeInSecond);
                headerPage.selectHeaderMenu(HeaderItems.SHOPPING_CARD.getItems());
                DriverManager.refreshPage();
                cartPage.goCheckOutProcess();
                WaitUtils.waitForProcessing("checkout");
                checkOutPage.placeOrder(billing);
                WaitUtils.waitForProcessing("order-received");
                $(orderStatusPage.tableProduct).exists();
                String orderNumber = $("//li[@class='woocommerce-order-overview__order order']/strong").text();
                String date = $("//li[@class='woocommerce-order-overview__date date']/strong").text().toUpperCase();
                String total = $("//li[@class='woocommerce-order-overview__total total']//bdi").text();
                orderInfo.add(new OrderInfo(orderNumber, date, total));
            }
            return orderInfo;
        }

    }
}

