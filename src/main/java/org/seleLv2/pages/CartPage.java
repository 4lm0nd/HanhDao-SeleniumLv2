package org.seleLv2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleLv2.common.constant.Constant;
import org.seleLv2.common.enums.Messages;
import org.seleLv2.data.ProductInfo;
import org.seleLv2.drivers.DriverManager;
import org.seleLv2.utils.AssertUtils;
import org.seleLv2.utils.DataUtils;
import org.seleLv2.utils.LogUtils;
import org.seleLv2.utils.WaitUtils;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.seleLv2.elements.Element.$;
import static org.seleLv2.elements.Elements.$$;
import static org.seleLv2.utils.DataUtils.convertToDouble;

public class CartPage {
    private final String msgEmptyCard = "//div[@class='cart-empty empty-cart-block']/h1";
    private final String btnClearCard = "//a[@class='clear-cart']";
    private final String txtQuantity = "//div[@class='quantity']//input";
    private final String btnPlus = "//div[@class='quantity']//span[@class='plus']";
    private final String btnMinus = "//div[@class='quantity']//span[@class='minus']";
    private final String btnUpdateCard = "//button[@name='update_cart']";
    private final String btnRemove = "//a[@title='Remove this item']";

    public void removeItem() {

            WebElement item =
                    $(btnRemove).find();

            item.click();

        WaitUtils.waitForStaleness(item);
    }

    public void removeAllItems() {
        int maxAttempts = 20;

        while ( $(btnRemove).exists() && maxAttempts > 0) {
            try {
                removeItem();

                Thread.sleep(1000);
            } catch (Exception e) {

                break;
            }
            maxAttempts--;
        }
    }

    public void clearCard() {
        $(btnClearCard).click();
        DriverManager.acceptAlert();
    }

    public void goCheckOutProcess() {
        if ($(msgEmptyCard).exists()) {
            DriverManager.refreshPage();
        }
        String btnCheckOut = "//a[contains(text(),'Proceed to checkout')]";
        $(btnCheckOut).click();
    }
    public List<ProductInfo> getProductsInCart() {

        List<ProductInfo> products = new ArrayList<>();

        String tableProduct =
                "//table[@class='shop_table shop_table_responsive cart woocommerce-cart-form__contents']//tbody";

        List<WebElement> rows =
                $$(tableProduct + "//tr").gets();

        for (WebElement row : rows) {

            String name =
                    row.findElement(
                                    By.cssSelector("td.product-details"))
                            .getText();

            String price =
                    row.findElement(
                                    By.cssSelector("td.product-price"))
                            .getText();

            String quantity =
                    row.findElement(
                                    By.cssSelector("td.product-quantity input"))
                            .getAttribute("value");

            products.add(
                    new ProductInfo(
                            name,
                            price,
                            quantity));
        }

        return products;
    }

    public void verifyCartProducts(List<ProductInfo> expected) {
        List<ProductInfo> actual = getProductsInCart();

        // Safety check: ensure lists aren't null before proceeding
        Assert.assertNotNull(expected, "Expected product list is null");
        Assert.assertNotNull(actual, "Actual product list is null");

        for (ProductInfo exp : expected) {
            boolean found = actual.stream().anyMatch(act -> {
                // Null-safe checks for every field
                boolean nameMatch = act.getProductName() != null && exp.getProductName() != null
                        && act.getProductName().contains(exp.getProductName());

                boolean priceMatch = act.getPrice() != null && exp.getPrice() != null
                        && act.getPrice().contains(exp.getPrice());

                // Objects.equals handles nulls safely (null == null is true, null == "val" is false)
                boolean qtyMatch = Objects.equals(act.getQuantity(), exp.getQuantity());

                return nameMatch && priceMatch && qtyMatch;
            });

            Assert.assertTrue(found, "Product not found in cart: " + exp.getProductName());
        }
    }

    public void verifyShoppingCardIsEmpty() {
        if ($(msgEmptyCard).exists()) {
            AssertUtils.assertContains(()->$(msgEmptyCard).text(), Messages.MSG_EMPTY_CARD.getMessage());
        } else {
            Assert.fail("Shopping Card Is Not Empty");
        }
    }

    public void clickPlusBtn() {
        $(btnPlus).click();

    }

    public void clickMinusBtn() {
        $(btnMinus).click();

    }

    public void enterQuantity(String value) {
        $(txtQuantity).type(value);
        $(btnUpdateCard).click();
    }

    public void verifySubTotal(String actQuantity) {

        String priceText = $("//td[@class='product-price']").text();
        String subtotalText = $("//td[@class='product-subtotal']").text();

        double price = DataUtils.convertToDouble(priceText);
        double actualSubtotal = DataUtils.convertToDouble(subtotalText);

        int quantity = Integer.parseInt(actQuantity);

        double expectedSubtotal = price * quantity;

        LogUtils.info("the actualSubtotal is _" + actualSubtotal + "and the expectedSubtotal is _" + expectedSubtotal);

        Assert.assertEquals(
                actualSubtotal,
                expectedSubtotal,
                0.01,
                "Subtotal is incorrect"
        );

    }

    public void verifyQuantity(String expQuantity) {

        String actQuantity =
                $("//td[@class='product-quantity']//input")
                        .value();

        Assert.assertEquals(
                actQuantity,
                expQuantity,
                "Quantity is incorrect"
        );
    }
}

