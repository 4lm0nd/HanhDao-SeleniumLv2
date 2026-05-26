package org.seleLv2.utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleLv2.elements.Elements;

import java.time.Duration;

public class WaitUtils {

    public static void waitForPageLoad(int timeoutInSeconds) {

        WebDriverWait wait = new WebDriverWait(
                WebDriverRunner.getWebDriver(),
                Duration.ofSeconds(timeoutInSeconds)
        );

        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState")
                .equals("complete"));

        // optional: delay for UI render
        Selenide.sleep(timeoutInSeconds);
    }

    public static void waitUntilElementEnable(SelenideElement element, int timeout){
        element.shouldBe(Condition.enabled, Duration.ofSeconds(timeout));
    }
}