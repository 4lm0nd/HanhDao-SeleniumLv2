package org.seleLv2.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.seleLv2.common.constant.Constant;
import org.seleLv2.data.ProductInfo;
import org.seleLv2.utils.WaitUtils;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static org.apache.commons.lang3.math.NumberUtils.toDouble;
import static org.seleLv2.elements.ElementList.$$;
import static org.seleLv2.elements.Elements.$;

public class OrderStatusPage {
    public final String tableProduct = "//table[@class='woocommerce-table woocommerce-table--order-details shop_table order_details']//tbody";

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

    public List<ProductInfo> getProductsInOrder() {
        WaitUtils.waitForPageLoad(Constant.timeout);
        List<ProductInfo> products = new ArrayList<>();
        ElementsCollection rows = $$(tableProduct + "//tr").gets();
        for (SelenideElement row : rows.shouldHave(sizeGreaterThan(0))) {
            String name = row.$("td.woocommerce-table__product-name.product-name").getText();
            String price = row.$("td.woocommerce-table__product-total.product-total").getText();
            String quantity = "1";
            products.add(new ProductInfo(name, price, quantity));
        }
        return products;
    }

    public void verifyProductsInOrder(List<ProductInfo> expected) {
        WaitUtils.waitForPageLoad(Constant.timeout);
        List<ProductInfo> actual = getProductsInOrder();
        for (ProductInfo exp : expected){
            boolean found =   actual.stream().anyMatch(act -> act.getProductName().contains(exp.getProductName()) &&
                    toDouble(act.getPrice()) == toDouble(exp.getPrice()));
            Assert.assertTrue(found, "Product not found in cart: " + exp.getProductName());
        }
    }

    public String getMgsOrderConfirmation(){
        String mgsOrderConfirmation = "//div[@class='woocommerce-order']/p[1]";
        return  $(mgsOrderConfirmation).text();

    }

    public String getPaymentInfo(){
       String paymentInfo = "//li[@class='woocommerce-order-overview__payment-method method']/strong";
       return $(paymentInfo).text();
    }

}
