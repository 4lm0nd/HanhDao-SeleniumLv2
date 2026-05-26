package org.seleLv2.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.Color;
import org.seleLv2.common.constant.Constant;
import org.seleLv2.common.enums.Messages;
import org.seleLv2.data.Billing;
import org.seleLv2.data.ProductInfo;
import org.seleLv2.utils.UrlUtils;
import org.seleLv2.utils.WaitUtils;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static org.apache.commons.lang3.math.NumberUtils.toDouble;
import static org.seleLv2.elements.ElementList.$$;
import static org.seleLv2.elements.Elements.$;
import static org.seleLv2.utils.AssertUtils.retryAssert;

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
    private static final String ERROR_COLOR = "#c62828";


    private final String msgFirstName = "//li[@data-id='billing_first_name']";
    private final String msgLastName = "//li[@data-id='billing_last_name']";
    private final String msgStreet = "//li[@data-id='billing_address_1']";
    private final String msgTown = "//li[@data-id='billing_city']";
    private final String msgPhone = "//li[@data-id='billing_phone']";
    private final String msgEmail = "//li[@data-id='billing_email']";
    private final String msgZipcode = "//li[@data-id='billing_postcode']";


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
        ElementsCollection rows = $$(tableReview + "//tr").gets();
        String quantity = "1";
        for (SelenideElement row : rows) {
            String name = row.$("td.product-name").getText();
            String price = row.$("td.product-total").getText();
            products.add(new ProductInfo(name, price, quantity));
        }
        return products;
    }

    public void selectPaymentMethod(String method) {
        $(String.format(paymentMethod, method)).click();
    }

    public void verifyCheckOutProducts(List<ProductInfo> expected) {
        List<ProductInfo> actual = getCheckOutProducts();
        for (ProductInfo exp : expected) {
            boolean found = actual.stream().anyMatch(act ->
                    act.getProductName().contains(exp.getProductName()) &&
                            toDouble(act.getPrice()) == toDouble(exp.getPrice())
            );
            Assert.assertTrue(found, "Product not found in cart: " + exp.getProductName());
        }
    }

    public void verifyTextboxHighlighted() {

        List<String> textboxes = List.of(
                txtFirstName,
                txtLastName,
                txtStreet,
                txtTown,
                txtZipcode,
                txtPhone,
                txtEmail
        );

        List<String> borderProperties = List.of(
                "border-top-color",
                "border-right-color",
                "border-bottom-color",
                "border-left-color"
        );

    retryAssert(() ->

            textboxes.forEach(locator -> {

                SelenideElement element = $x(locator);

                borderProperties.forEach(property -> {

                    String actualColor = Color.fromString(
                            element.getCssValue(property)
                    ).asHex();

                    Assert.assertEquals(
                            actualColor,
                            ERROR_COLOR,
                            "Incorrect color for property: " + property
                    );
                });
            }),
            5,
            100
            );

    }

    public void verifyErrorMessages() {
        Map<String, String> validations = Map.of(
                msgFirstName, Messages.MSG_ERROR_FIRSTNAME.getMessage(),
                msgLastName, Messages.MSG_ERROR_LASTNAME.getMessage(),
                msgStreet, Messages.MSG_ERROR_STREET.getMessage(),
                msgTown, Messages.MSG_ERROR_TOWN.getMessage(),
                msgZipcode, Messages.MSG_ERROR_ZIP.getMessage(),
                msgPhone, Messages.MSG_ERROR_PHONE.getMessage(),
                msgEmail, Messages.MSG_ERROR_EMAIL.getMessage()
        );

        retryAssert(() ->

        validations.forEach((locator, message) ->
                $x(locator).shouldBe(enabled).shouldHave(exactText(message))
        ),
                5,
                100
        );

    }
}


