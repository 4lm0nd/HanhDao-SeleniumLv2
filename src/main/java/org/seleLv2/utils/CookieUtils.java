package org.seleLv2.utils;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CookieUtils {

    private static final By BTN_ACCEPT =
            By.xpath("//a[@id='cn-accept-cookie']");

    public static void acceptCookiesIfPresent() {

        SelenideElement btn = $(BTN_ACCEPT);

        if (btn.exists()) {

            btn.shouldBe(visible)
                    .click();

            btn.should(disappear);
        }
    }
}
