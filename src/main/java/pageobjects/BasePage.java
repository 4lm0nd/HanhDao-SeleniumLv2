package pageobjects;

import common.constant.Constant;
import drivers.DriverManager;
import elements.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LogUtils;

import java.time.Duration;

public class BasePage {
    private final By allDepartmentsMenu = By.xpath("//div[@class='secondary-title']//span[contains(text(),'All departments')]");
    private final String departmentComponent = "//*[@id='menu-all-departments-1']//a[contains(text(),'%s')]";

    public void hoverAndClickComponent(String item) {

        try {
            By locator = By.xpath(String.format(departmentComponent,item));
            Actions actions = new Actions(DriverManager.getDriver());
            actions.moveToElement(DriverManager.getDriver().findElement(allDepartmentsMenu)).perform();
            Elements.click(locator);
        } catch (StaleElementReferenceException e){
            LogUtils.info(e.getMessage());
        }
    }
}
