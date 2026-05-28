package org.seleLv2.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Alert;
import org.seleLv2.common.constant.Constant;
import org.seleLv2.common.enums.Messages;
import org.seleLv2.data.ProductInfo;
import org.seleLv2.utils.AssertUtils;
import org.seleLv2.utils.WaitUtils;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;
import static org.seleLv2.elements.ElementList.$$;
import static org.seleLv2.elements.Elements.$;

public class CartPage {
    private final String msgEmptyCard = "//div[@class='cart-empty empty-cart-block']/h1";
    private final String btnClearCard = "//a[@class='clear-cart']";
    private final String txtQuantity = "//div[@class='quantity']//input";
    private final String btnPlus = "//div[@class='quantity']//span[@class='plus']";
    private final String btnMinus = "//div[@class='quantity']//span[@class='minus']";
    private final String btnUpdateCard = "//button[@name='update_cart']";

    public void removeItem() {
        String btnRemove = "//td[@class='product-details']//a[@title='Remove this item']";
        $(btnRemove).scrollTo();
        $(btnRemove).click();
    }

    public void removeAllItems() {
        while (!$(msgEmptyCard).isVisible()) {
            removeItem();
        }
    }

    public void clearCard() {
        $(btnClearCard).click();
        Alert alert = switchTo().alert();
        alert.accept();
    }

    public void goCheckOutProcess() {
        if ($(msgEmptyCard).isVisible()) {
            refresh();
        }
        String btnCheckOut = "//a[contains(text(),'Proceed to checkout')]";
        $(btnCheckOut).click();
    }

    public List<ProductInfo> getProductsInCart() {
        List<ProductInfo> products = new ArrayList<>();
        String tableProduct = "//table[@class='shop_table shop_table_responsive cart woocommerce-cart-form__contents']//tbody";
        ElementsCollection rows = $$(tableProduct + "//tr").gets();
        for (SelenideElement row : rows) {
            String name = row.$("td.product-details").getText();
            String price = row.$("td.product-price").getText();
            String quantity = row.$("td.product-quantity input").getValue();
            products.add(new ProductInfo(name, price, quantity));
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
        if ($(msgEmptyCard).isVisible()) {
            AssertUtils.assertContains($(msgEmptyCard).text(), Messages.MSG_EMPTY_CARD.getMessage(),Constant.shortTime);
        } else {
            Assert.fail("Shopping Card Is Not Empty");
        }
    }

    public void clickPlusBtn() {
        $(btnPlus).click();
        WaitUtils.waitUntilElementEnable($x(btnPlus), Constant.timeout);
    }

    public void clickMinusBtn() {
        $(btnMinus).click();
        WaitUtils.waitUntilElementEnable($x(btnMinus), Constant.timeout);
    }

    public void enterQuantity(String value) {
        $(txtQuantity).type(value);
        $(btnUpdateCard).click();
        WaitUtils.waitUntilElementEnable($x(btnUpdateCard), Constant.timeout);
    }

    public void verifySubTotal(String actQuantity) {
        String priceText = $x("//td[@class='product-price']")
                .getText();
        String actSubtotal = $x("//td[@class='product-subtotal']")
                .getText();
        double price = Double.parseDouble(
                priceText.replace("$", "").trim()
        );
        int quantity = Integer.parseInt(actQuantity);
        double expSubtotal = price * quantity;

        Assert.assertTrue(
                actSubtotal.contains(String.format("%.2f", expSubtotal)),
                "Subtotal is incorrect. Actual: " + actSubtotal + " Expected: " + expSubtotal
        );
    }

    public void verifyQuantity(String expQuantity) {

        String actQuantity = $x("//td[@class='product-quantity']//input")
                .getValue();

        assert actQuantity != null;
        Assert.assertEquals(
                actQuantity,
                expQuantity,
                "Quantity is incorrect"
        );


    }
}

