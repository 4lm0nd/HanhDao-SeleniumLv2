package org.seleLv2.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleLv2.common.constant.Constant;
import org.seleLv2.data.ProductInfo;
import org.seleLv2.drivers.DriverManager;
import org.seleLv2.utils.*;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.seleLv2.elements.Element.$;
import static org.seleLv2.elements.Elements.$$;
import static org.seleLv2.utils.AssertUtils.retryAssert;


public class ProductList {

    private WebElement selectedProduct;

    private List<WebElement> getItems() {
        String productList = "//div[@class='text-center product-details']//a[contains(@href,'add-to-cart')]";
        return $$(productList).gets();
    }

    public String getSelectedProductKey() {
        String productID = selectedProduct.getAttribute("href");
        assert productID != null;
        return UrlUtils.getQueryParam(productID, "?");
    }

    public String getProductName() {
        String key = getSelectedProductKey();
        String productTitle = "//a[contains(@href,'" + key + "')]/ancestor::div/h2[@class='product-title']";
        return $(productTitle).text();
    }

    public String getProductPrice() {
        String key = getSelectedProductKey();
        String salePrice = "//a[contains(@href,'" + key + "')]/preceding-sibling::span[@class='price']//ins//bdi";
        String normalPrice = "//a[contains(@href,'" + key + "')]/preceding-sibling::span[@class='price']//bdi";
        if ($(salePrice).exists()) {
            return $(salePrice).text();
        }
        return $(normalPrice).text();
    }


    public List<ProductInfo> addProductsToCart(int count, String quantity)
        {
        List<ProductInfo> addedProducts = new ArrayList<>();
        List<WebElement> shuffledItems =
                new ArrayList<>(getItems());
        Collections.shuffle(shuffledItems);
        count = Math.min(count, shuffledItems.size());
            for (int i = 0; i < count; i++) {

                selectedProduct = shuffledItems.get(i);
                String name = getProductName();
                LogUtils.info("Selected Product:_" + name);
                String price = getProductPrice();

                addedProducts.add(
                        new ProductInfo(
                                name,
                                price,
                                quantity));

                String product =
                        "//div[@class='text-center product-details']" +
                                "//a[contains(@href,'" + getSelectedProductKey() + "')]";

                $(product)
                        .scrollTo()
                        .click();
            }

        return addedProducts;
    }

    public void switchView(String viewAs) {
        String viewSwitcher = "//div[contains(@class,'view-switcher')]//a[contains(@href,'" + viewAs + "')]/parent::*";

       if(!$(viewSwitcher).exists()) {
           $(viewSwitcher).scrollTo().click();
       }
           $(viewSwitcher).click();
       }


    public void filterProducts(String option) {
        String filterProducts = "//select[@class='orderby']";
        By productLocator =
                By.xpath("//span[@class='price']");

        WebElement firstProduct =
                DriverManager.getDriver()
                        .findElements(productLocator)
                        .get(0);

        $(filterProducts).selectByText(option);

        new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(10))
                .until(ExpectedConditions.stalenessOf(firstProduct));
    }

    public boolean isSortedASC() {

        String price = "//span[@class='price']";
        List<String> prices = new ArrayList<>();

        for (WebElement product : $$(price).gets()) {

            String priceText;

            List<WebElement> salePrice =
                    product.findElements(
                            By.xpath(".//ins//bdi"));

            if (!salePrice.isEmpty()) {

                priceText =
                        salePrice.get(0).getText();

            } else {

                priceText =
                        product.findElement(
                                        By.xpath(".//bdi"))
                                .getText();
            }

            prices.add(priceText);
        }
        LogUtils.info("Prices: " + prices);
        return SortUtils.isSortedASC(prices);
    }


    public boolean isSortedDESC() {
        String price = "//span[@class='price']";
        List<String> prices = new ArrayList<>();

        for (WebElement product : $$(price).gets()) {

            String priceText;

            List<WebElement> salePrice =
                    product.findElements(
                            By.xpath(".//ins//bdi"));

            if (!salePrice.isEmpty()) {

                priceText =
                        salePrice.get(0).getText();

            } else {

                priceText =
                        product.findElement(
                                        By.xpath(".//bdi"))
                                .getText();
            }

            prices.add(priceText);
        }

        LogUtils.info("Prices: " + prices);

        return SortUtils.isSortedDESC(prices);
    }

    public boolean isGridViewActive(){
        return $("//div[contains(@class,'switch-grid')]").hasClass("switcher-active");
    }

    public boolean isListViewActive(){
           return $("//div[contains(@class,'switch-list')]").hasClass("switcher-active");
    }

    public void openProductDetail(){
        int index = RandomUtils.getRandomIndex(getItems().size());
        selectedProduct  = getItems().get(index);
        String key = getSelectedProductKey();
        String product = "//a[contains(@href,'"+key+"')]/ancestor::div[contains(@class,'content-product ')]/div[contains(@class,'product-image-wrapper')]";
        $(product).click();
    }

}



