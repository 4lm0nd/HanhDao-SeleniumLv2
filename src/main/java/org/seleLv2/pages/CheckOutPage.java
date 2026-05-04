package org.seleLv2.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.seleLv2.common.constant.Constant;
import org.seleLv2.common.enums.PaymentMethods;
import org.seleLv2.data.Billing;
import org.seleLv2.data.ProductInfo;
import org.seleLv2.utils.UrlUtils;
import org.seleLv2.utils.WaitUtils;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.seleLv2.elements.ElementList.$$;
import static org.seleLv2.elements.Elements.$;

public class CheckOutPage {

    private final String link = "//a[@href='%s']";
    private final String href = UrlUtils.getUrl("checkout/");
    public final String btnPlaceOrder = "//button[@id='place_order']";
    private final String txtFirstName = "//input[@id='billing_first_name']";
    private final String txtLastName = "//input[@id='billing_last_name']";
    private final String txtStreet = "//input[@id='billing_address_1']";
    private final String txtTown = "//input[@id='billing_city']";
    private final String txtPhone = "//input[@id='billing_phone']";
    private final String txtEmail = "//input[@id='billing_email']";
    private final String txtZipcode = "//input[@id='billing_postcode']";
    private final String tableReview = "//table[@class='shop_table woocommerce-checkout-review-order-table']//tbody";
    private final String paymentMethod = "//div[@id='payment']//li/input[@value='%s']";

    public String getPageHeader() {
        String tabCheckOut = String.format(link, href);
        $(tabCheckOut).text();
        return $(tabCheckOut).text();
    }

    public void fillBilling(Billing billing) {
        $(txtFirstName).type(billing.getFirstname());
        $(txtLastName).type(billing.getLastname());
        $(txtStreet).type(billing.getStreet());
        $(txtTown).type(billing.getTown());
        $(txtZipcode).type(billing.getZipcode());
        $(txtPhone).type(billing.getPhone());
        $(txtEmail).type(billing.getEmail());
    }
    public void placeOrder(Billing billing) {
        fillBilling(billing);
        WaitUtils.waitForPageLoad(Constant.timeout);
        $(btnPlaceOrder).click();
    }

    public List<ProductInfo> getCheckOutProducts() {
        List<ProductInfo> products = new ArrayList<>();
        ElementsCollection rows = $$(tableReview  + "//tr").gets();
        for (SelenideElement row : rows) {
            String name = row.$("td.product-name").getText();
            String price = row.$("td.product-total").getText();
            products.add(new ProductInfo(name, price));
        }
        return products;
    }

    public void selectPaymentMethod(String method) {
        $(String.format(paymentMethod, method)).click();
    }

    public void verifyCheckOutProducts(List<ProductInfo> expected) {
        List<ProductInfo> actual = getCheckOutProducts();
        for (ProductInfo exp : expected){
            boolean found =   actual.stream().anyMatch(act -> act.getProductName().contains(exp.getProductName()) && act.getPrice().contains(exp.getPrice()));
            Assert.assertTrue(found, "Product not found in cart: " + exp.getProductName());
        }
    }
}

