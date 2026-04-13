package org.seleLv2.pages;

import org.seleLv2.drivers.DriverManager;
import org.seleLv2.elements.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.seleLv2.utils.LogUtils;


public class BasePage {
    private final By allDepartmentsMenu = By.xpath("//div[@class='secondary-title']//span[contains(text(),'All departments')]");
    private final String departmentComponent = "//*[@id='menu-all-departments-1']//a[contains(text(),'%s')]";

    public void hoverAndClickComponent(String item) {
        try {
            By locator = By.xpath(String.format(departmentComponent, item));
            Elements.hover(allDepartmentsMenu);
            Elements.click(locator);
        } catch (StaleElementReferenceException e) {
            LogUtils.info(e.getMessage());
        }
    }

    public boolean isElementVisible(By locator) {
        try {
            return DriverManager.getDriver().findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
