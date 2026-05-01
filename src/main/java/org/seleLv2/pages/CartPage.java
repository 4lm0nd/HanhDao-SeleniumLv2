package org.seleLv2.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.seleLv2.data.ProductInfo;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.refresh;
import static org.seleLv2.elements.ElementList.$$;
import static org.seleLv2.elements.Elements.$;

public class CartPage {
    private final String msgEmptyCard = "//div[@class='cart-empty empty-cart-block']/h1";
    private final String tableProduct = "//table[@class='shop_table shop_table_responsive cart woocommerce-cart-form__contents']//tbody";

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

    public void goCheckOutProcess() {
        if ($(msgEmptyCard).isVisible()) {
            refresh();
        }
        String btnCheckOut = "//a[contains(text(),'Proceed to checkout')]";
        $(btnCheckOut).click();
    }

    public String getTableProductInfo(String columnName, int row) {
        return $x(tableProduct + "//tr["+row+"]//td[@class='" + columnName + "']")
                .getText();
    }

    public List<ProductInfo> getProductsInCart() {
        List<ProductInfo> products = new ArrayList<>();
        ElementsCollection rows = $$(tableProduct + "//tr").gets();
        for (SelenideElement row : rows) {
            String name = row.$("td.product-details").getText();
            String price = row.$("td.product-price").getText();
            products.add(new ProductInfo(name, price));
        }
        return products;
    }

    public void verifyCartProducts(List<ProductInfo> expected) {
        List<ProductInfo> actual = getProductsInCart();
        for (ProductInfo exp : expected){
            boolean found =   actual.stream().anyMatch(act -> act.getProductName().contains(exp.getProductName()) && act.getPrice().contains(exp.getPrice()));
            Assert.assertTrue(found, "Product not found in cart: " + exp.getProductName());
        }
    }
}
