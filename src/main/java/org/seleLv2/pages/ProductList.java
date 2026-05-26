package org.seleLv2.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.seleLv2.data.ProductInfo;
import org.seleLv2.utils.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static org.seleLv2.elements.ElementList.$$;
import static org.seleLv2.elements.Elements.$;

public class ProductList {

    private SelenideElement selectedProduct;
    private final String productList = "//div[@class='text-center product-details']//a[contains(@href,'add-to-cart')]";
    private final ElementsCollection items = $$(productList).gets();

    public SelenideElement getRandomProduct() {
        if (selectedProduct == null) {
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


    public List<ProductInfo> addProductsToCart(int count, String quantity) {
        List<ProductInfo> addedProducts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<SelenideElement> shuffledItems = new ArrayList<>(items);
            Collections.shuffle(shuffledItems);
            selectedProduct = shuffledItems.get(i);
            String key = getSelectedProductKey();
            String product = "//div[@class='text-center product-details']//a[contains(@href,'" + key + "')]";
            $(product).isEnable();
            $(product).scrollTo().click();
            String name = getProductName();
            String price = getProductPrice();
            addedProducts.add(new ProductInfo(name, price, quantity));
            LogUtils.info(name);
            LogUtils.info(price);
        }
        return addedProducts;
    }

    public void switchView(String viewAs) {
        String viewSwitcher = "//div[contains(@class,'view-switcher')]//a[contains(@href,'" + viewAs + "')]/parent::*";

       if(!$(viewSwitcher).isVisible()) {
           $(viewSwitcher).scrollTo().click();
       }
           $(viewSwitcher).click();
       }


    public void filterProducts(String option) {
        String filterProducts = "//select[@class='orderby']";
        $(filterProducts).hover();
        $(filterProducts).selectElementByText(option);
    }

    public boolean isSortedASC() {

        String price = "//span[@class='price']";
        List<String> prices = new ArrayList<>();

        for (SelenideElement product : $$(price).gets()) {

            String priceText;

            if (product.$x(".//ins//bdi").exists()) {
                priceText = product.$x(".//ins//bdi").text();
            } else {
                priceText = product.$x(".//bdi").text();
            }

            prices.add(priceText);
        }

        LogUtils.info("Prices: " + prices);

        return SortUtils.isSortedASC(prices);
    }


    public boolean isSortedDESC() {
        String price = "//span[@class='price']";
        List<String> prices = new ArrayList<>();

        for (SelenideElement product : $$(price).gets()) {

            String priceText;

            if (product.$x(".//ins//bdi").exists()) {
                priceText = product.$x(".//ins//bdi").text();
            } else {
                priceText = product.$x(".//bdi").text();
            }

            prices.add(priceText);
        }

        LogUtils.info("Prices: " + prices);

        return SortUtils.isSortedDESC(prices);
    }

    public boolean isGridViewActive(){
        return Selenide.$(".switch-grid").has(cssClass("switcher-active"));
    }

    public boolean isListViewActive(){
        return Selenide.$(".switch-list").has(cssClass("switcher-active"));
    }

    public void openProductDetail(){
        String key = getSelectedProductKey();
        String product = "//a[contains(@href,'"+key+"')]/ancestor::div[contains(@class,'content-product ')]/div[contains(@class,'product-image-wrapper')]";
        $(product).click();
    }
}



