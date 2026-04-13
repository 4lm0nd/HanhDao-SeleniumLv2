package org.seleLv2.pages;

import org.openqa.selenium.By;
import org.seleLv2.common.enums.Messages;
import org.seleLv2.drivers.DriverManager;
import org.seleLv2.elements.Elements;

public class CartPage extends BasePage {
    private final By btnRemove = By.xpath("//td[@class='product-details']//a[@title='Remove this item']");
    private final By msgEmptyCard = By.xpath("//div[@class='cart-empty empty-cart-block']/h1");

    public void removeItem() {
        Elements.scrollToElement(DriverManager.getDriver().findElement(btnRemove));
        Elements.click(btnRemove);
    }

    public void removeAllItems() {
            while (!isElementVisible(msgEmptyCard)) {
                removeItem();
            }
    }
}
