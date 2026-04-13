package org.seleLv2.pages;

import org.seleLv2.elements.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.seleLv2.utils.LogUtils;
import org.seleLv2.utils.RandomUtils;

import java.util.List;

public class ProductPage extends BasePage {

    private final By productList = By.xpath("//div[@class='text-center product-details']//a[contains(@href,'add-to-cart')]");

    public void clickRandomProduct() {
        List<WebElement> items = Elements.getElements(productList);
        WebElement randomItem = RandomUtils.getRandomItem(items);
        int index = RandomUtils.getRandomIndex(items.size());
        String className = randomItem.getAttribute("href");
        LogUtils.info("Selected index: " + className);
        Elements.scrollToElement(randomItem);
        randomItem.click();
    }
}
