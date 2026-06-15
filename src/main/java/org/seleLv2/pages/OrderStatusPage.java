package org.seleLv2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.seleLv2.common.constant.Constant;
import org.seleLv2.data.ProductInfo;
import org.seleLv2.utils.WaitUtils;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.math.NumberUtils.toDouble;
import static org.seleLv2.common.enums.Conditions.VISIBLE;
import static org.seleLv2.elements.Element.$;
import static org.seleLv2.elements.Elements.$$;
import static org.seleLv2.utils.DataUtils.normalizeProductName;

public class OrderStatusPage {

    public final String tableProduct = "//table[@class='woocommerce-table woocommerce-table--order-details shop_table order_details']//tbody";
    private final String tabOrderStatus = "//a[@href and contains(text(),'Order status')]";
    private final String tableBilling = "//section[contains(@class,'woocommerce-customer-details')]//address";
    private final String mgsOrderConfirmation = "//div[@class='woocommerce-order']/p[1]";
    private final String paymentInfo = "//li[@class='woocommerce-order-overview__payment-method method']/strong";

    public String getPageHeader() {
        $(tabOrderStatus).text();
        return $(tabOrderStatus).text();
    }

    public String getTableBilling() {
        WaitUtils.waitForPageLoad(Constant.timeInSecond);
        $(tableBilling).shouldBe(VISIBLE);
        return $(tableBilling).text();
    }

    public List<ProductInfo> getProductsInOrder() {

        $(tableProduct).shouldBe(VISIBLE);

        List<ProductInfo> products = new ArrayList<>();

        List<WebElement> rows = $$(tableProduct + "//tr").gets();

        Assert.assertFalse(
                rows.isEmpty(),
                "No products found in order table");

        for (WebElement row : rows) {

            String name =
                    row.findElement(By.cssSelector("td.product-name")).getText();

            String price =
                    row.findElement(By.cssSelector("td.product-total")).getText();

            products.add(
                    new ProductInfo(
                            name,
                            price,
                            "1"));
        }

        return products;
    }

    public void verifyProductsInOrder(List<ProductInfo> expected) {

        List<ProductInfo> actual = getProductsInOrder();
        System.out.println("=== EXPECTED ===");

        expected.forEach(p ->
                System.out.println(
                        p.getProductName() + " | " + p.getPrice()));

        System.out.println("=== ACTUAL ===");

        actual.forEach(p ->
                System.out.println(
                        p.getProductName() + " | " + p.getPrice()));

        for (ProductInfo exp : expected){
            boolean found = actual.stream()
                    .anyMatch(act ->
                            normalizeProductName(act.getProductName())
                                    .equals(
                                            normalizeProductName(exp.getProductName()))
                                    &&
                                    Double.compare(
                                            toDouble(act.getPrice()),
                                            toDouble(exp.getPrice())) == 0);

            Assert.assertTrue(found, "Product not found in cart: " + exp.getProductName());
        }

     }

    public String getMgsOrderConfirmation(){
        return $(mgsOrderConfirmation).text();

    }

    public String getPaymentInfo(){
       $(paymentInfo).shouldBe(VISIBLE);
       return $(paymentInfo).text();
    }

}
