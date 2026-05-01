package org.seleLv2.pages;

import org.seleLv2.common.constant.Constant;
import org.seleLv2.data.Billing;
import org.seleLv2.utils.LogUtils;
import org.seleLv2.utils.UrlUtils;
import org.seleLv2.utils.WaitUtils;

import static org.seleLv2.elements.Elements.$;

public class CheckOutPage {

    private final String link = "//a[@href='%s']";
    private final String href = UrlUtils.getUrl("checkout/");
    private final String btnPlaceOrder = "//button[@id='place_order']";
    private final String txtFirstName = "//input[@id='billing_first_name']";
    private final String txtLastName = "//input[@id='billing_last_name']";
    private final String txtStreet = "//input[@id='billing_address_1']";
    private final String txtTown = "//input[@id='billing_city']";
    private final String txtPhone = "//input[@id='billing_phone']";
    private final String txtEmail = "//input[@id='billing_email']";
    private final String txtZipcode = "//input[@id='billing_postcode']";
    private final String tableReview = "//table[@class='shop_table woocommerce-checkout-review-order-table']//tbody";

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
    public void placeOrder(String firstname, String lastname,
                           String street, String town, String zipcode,
                           String phone, String email) {
        Billing billing = new Billing(firstname, lastname, street, town, zipcode, phone, email);
        fillBilling(billing);
        WaitUtils.waitForPageLoad(Constant.timeout);
        $(btnPlaceOrder).click();
    }
    public String getProductName() {
        String productTitle = tableReview + "//td[@class='product-name']";
        return $(productTitle).text();
    }

    public String getProductPrice() {
        String productPrice = tableReview + "//span[@class='woocommerce-Price-amount amount']//bdi";
        return  $(productPrice).text();
    }
}

