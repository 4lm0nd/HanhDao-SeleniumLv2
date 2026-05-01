package org.seleLv2.pages;

import org.openqa.selenium.By;
import org.seleLv2.elements.Tables;
import org.seleLv2.utils.LogUtils;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.refresh;
import static org.seleLv2.elements.Elements.$;

public class CartPage {
    private final String btnRemove = "//td[@class='product-details']//a[@title='Remove this item']";
    private final String msgEmptyCard = "//div[@class='cart-empty empty-cart-block']/h1";
    private final String tableProduct = "//table[@class='shop_table shop_table_responsive cart woocommerce-cart-form__contents']//tbody";
    private final String btnCheckOut = "//a[contains(text(),'Proceed to checkout')]";

    public void removeItem() {
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
        $(btnCheckOut).click();
    }

    public String getFirstRowValueByColumnName(String columnName) {
        return $x(tableProduct + "//tr[1]//td[@class='" + columnName + "']")
                .getText();
    }
}
