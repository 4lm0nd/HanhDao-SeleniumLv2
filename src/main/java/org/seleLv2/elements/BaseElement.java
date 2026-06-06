package org.seleLv2.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import org.seleLv2.common.constant.Constant;
import org.seleLv2.common.enums.Conditions;
import org.seleLv2.drivers.DriverManager;
import java.time.Duration;


public class BaseElement {

    protected final String xpath;
    protected final By locator;

    private static final Duration TIMEOUT =
            Duration.ofSeconds(Constant.timeInSecond);

    public BaseElement(String xpath) {
        this.xpath = xpath;
        this.locator = By.xpath(xpath);
    }

    public static BaseElement $(String xpath) {
        return new BaseElement(xpath);
    }

    protected WebDriver driver() {
        return DriverManager.getDriver();
    }

    protected WebDriverWait waiter() {
        return new WebDriverWait(driver(), TIMEOUT);
    }

    public WebElement find() {
        return driver().findElement(locator);
    }

    public WebElement findVisible() {

        List<WebElement> elements = driver().findElements(locator);

        for (WebElement e : elements) {
            if (e.isDisplayed()) {
                return e;
            }
        }

        throw new NoSuchElementException(
                "No visible element found: " + locator);
    }


    // ========================
    // ACTIONS
    // ========================

    public BaseElement click() {

        By overlay = By.cssSelector(".blockUI.blockOverlay");

        waiter().until(
                ExpectedConditions.invisibilityOfElementLocated(overlay));

        waiter().until(
                        ExpectedConditions.elementToBeClickable(locator));

        driver().findElement(locator).click();

        return this;
    }

    public void type(String value) {

        WebElement element =
                waiter().until(
                        ExpectedConditions.visibilityOfElementLocated(locator));

        element.clear();
        element.sendKeys(value);
    }

    public void hover() {

        WebElement element =
                waiter().until(driver -> {

                    List<WebElement> elements =
                            driver().findElements(locator);
                    return elements.stream()
                            .filter(WebElement::isDisplayed)
                            .findFirst()
                            .orElse(null);
                });

        new Actions(driver())
                .moveToElement(element)
                .perform();

    }

    public BaseElement scrollTo() {

        WebElement element = findVisible();

        ((JavascriptExecutor) driver())
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        element);

        return this;

    }

    // ========================
    // GETTERS
    // ========================

    public String text() {
        return find().getText();
    }

    public String value() {
        return attribute("value");
    }

    public String attribute(String name) {
        return find().getAttribute(name);
    }

    public String cssValue(String property) {
        return find().getCssValue(property);
    }

    public boolean hasClass(String className) {

        String classes = attribute("class");

        return classes != null &&
                Arrays.asList(classes.split("\\s+"))
                        .contains(className);
    }

    // ========================
    // STATES
    // ========================

    public boolean exists() {

        System.out.println("Checking exists: " + locator);

        try {
            List<WebElement> elements =
                    driver().findElements(locator);

            System.out.println("Found: " + elements.size());

            return !elements.isEmpty();

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    public boolean isEnabled() {

        try {
            return find().isEnabled();
        } catch (Exception e) {
            return false;
        }
    }


    // ========================
    // SELECT
    // ========================

    public void selectByText(String text) {

        new Select(find())
                .selectByVisibleText(text);

    }


    // ========================
    // SHOULD BE
    // ========================


    public void shouldBe(Conditions condition) {

        WebDriverWait wait =
                new WebDriverWait(driver(), Duration.ofSeconds(Constant.timeInSecond));

        switch (condition) {

            case VISIBLE:
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(locator));
                break;

            case CLICKABLE:
                wait.until(
                        ExpectedConditions.elementToBeClickable(locator));
                break;

            case PRESENT:
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(locator));
                break;
        }

    }
}

