package org.seleLv2.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleLv2.utils.LogUtils;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class Elements {

    private final SelenideElement element;
    private final String xpath;

    // ===== CONSTRUCTOR =====
    public Elements(String xpath) {
        this.xpath = xpath;
        this.element = $x(xpath);
    }

    public static Elements $(String xpath) {
        return new Elements(xpath);
    }

    // ===== GET =====
    public SelenideElement get() {
        return element;
    }

    // ===== CLICK =====
    public Elements click() {
        element.shouldBe(visible, enabled);
        element.click();
        LogUtils.info("Clicked: " + xpath);
        return this;
    }

    // ===== TYPE =====
    public Elements type(String text) {
        element.shouldBe(visible).setValue(text);
        LogUtils.info("Typed into: " + xpath);
        return this;
    }

    // ===== HOVER =====
    public Elements hover() {
        element.shouldBe(visible).hover();
        LogUtils.info("Hovered: " + xpath);
        return this;
    }

    // ===== SCROLL =====
    public Elements scrollTo() {
        element.scrollIntoView("{block: 'center'}");
        LogUtils.info("Scrolled to: " + xpath);
        return this;
    }

    // ===== TEXT =====
    public String text() {
        return element.shouldBe(visible).getText();
    }

    // ===== VISIBLE =====
    public boolean isVisible() {
        return element.isDisplayed();
    }

    // ===== ENABLE =====
    public boolean isEnable() {
        return element.isEnabled();
    }

    public void waitUntilElementEnable(int timeout){
        element.shouldBe(Condition.enabled, Duration.ofSeconds(timeout));
    }

    public Elements selectElementByText( String option) {
        element.shouldBe(visible).selectOption(option);
        return this;
    }
}