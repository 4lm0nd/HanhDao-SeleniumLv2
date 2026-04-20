package org.seleLv2.pages;

import org.seleLv2.common.constant.Constant;
import org.seleLv2.utils.LogUtils;
import org.seleLv2.utils.UrlUtils;
import org.seleLv2.utils.WaitUtils;

import static org.seleLv2.elements.Elements.$;

public class OrderStatusPage {

    public String getPageHeader() {
        String tabOrderStatus = "//a[@href and contains(text(),'Order status')]";
        WaitUtils.waitForPageLoad(Constant.timeout);
        $(tabOrderStatus).text();
        return $(tabOrderStatus).text();
    }

    public String getTableBilling() {
        WaitUtils.waitForPageLoad(Constant.timeout);
        String tableBilling = "//section[@class='woocommerce-customer-details']/address";
        $(tableBilling).text();
        return $(tableBilling).text();
    }

    public String getTableProduct() {
        WaitUtils.waitForPageLoad(Constant.timeout);
        String tableProduct = "//table[@class='woocommerce-table woocommerce-table--order-details shop_table order_details']//tbody";
        $(tableProduct).text();
        return $(tableProduct).text();
    }
}
