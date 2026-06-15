package org.seleLv2.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.seleLv2.common.enums.HeaderItems;
import org.seleLv2.drivers.DriverManager;
import org.seleLv2.utils.LogUtils;
import org.seleLv2.utils.UrlUtils;
import static org.seleLv2.elements.Element.$;
import static org.seleLv2.elements.Elements.$$;


public class HeaderPage {
    private final String link = "//a[@href='%s']";

    public void hoverAndClickComponent(String item) {
        try {
            String departmentComponent = "//*[@id='menu-all-departments-1']//a[contains(text(),'%s')]";
            String locator = String.format(departmentComponent,item);
            String allDepartmentsMenu = "//div[@class='secondary-title']//span[contains(text(),'All departments')]";
            $(allDepartmentsMenu).hover();
            $(locator).scrollTo().click();
        } catch (StaleElementReferenceException e) {
            LogUtils.info(e.getMessage());
        }
    }

    public void selectHeaderMenu(String item) {
        String itemLink =
                String.format(link,
                        UrlUtils.getUrl(item));

        for (WebElement element : $$(itemLink).gets()) {

            if (element.isDisplayed()) {

                new Actions(DriverManager.getDriver())
                        .moveToElement(element)
                        .perform();

                element.click();

                return;
            }
        }

            throw new NoSuchElementException(
                    "No visible menu found: " + item
            );
        }


    public void goToShoppingCard(){
        selectHeaderMenu(HeaderItems.SHOPPING_CARD.getItems());
        DriverManager.refreshPage();
    }
}
