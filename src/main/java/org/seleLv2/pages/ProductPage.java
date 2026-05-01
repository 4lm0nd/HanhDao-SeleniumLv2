package org.seleLv2.pages;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.seleLv2.utils.LogUtils;
import org.seleLv2.utils.RandomUtils;
import org.seleLv2.utils.UrlUtils;

import static org.seleLv2.elements.ElementList.$$;
import static org.seleLv2.elements.Elements.$;

public class ProductPage {

    private SelenideElement selectedProduct;

    public SelenideElement getRandomProduct() {
        if (selectedProduct == null) {
            String productList = "//div[@class='text-center product-details']//a[contains(@href,'add-to-cart')]";
            ElementsCollection items = $$(productList).gets();
            selectedProduct = RandomUtils.getRandomItem(items);
            LogUtils.info("the product is: " + selectedProduct.getAttribute("href"));
        }
            return selectedProduct;
    }

    public String getSelectedProductKey() {
        String productID = getRandomProduct().getAttribute("href");
        assert productID != null;
        return UrlUtils.getQueryParam(productID, "?");
    }

    public String getProductName() {
        String key = getSelectedProductKey();
        String productTitle = "//a[contains(@href,'" + key + "')]/ancestor::div/h2[@class='product-title']";
        return $(productTitle).get().getText();
    }

    public String getProductPrice() {
        String key = getSelectedProductKey();
        String salePrice = "//a[contains(@href,'" + key + "')]/preceding-sibling::span[@class='price']//ins//bdi";
        String normalPrice = "//a[contains(@href,'" + key + "')]/preceding-sibling::span[@class='price']//bdi";
        if ($(salePrice).isVisible()) {
            return $(salePrice).text();
        }
        return $(normalPrice).text();
    }

    public void clickProduct() {
        String key = getSelectedProductKey();
        String product = "//div[@class='text-center product-details']//a[contains(@href,'" + key + "')]";
        $(product).scrollTo().click();
    }
}
