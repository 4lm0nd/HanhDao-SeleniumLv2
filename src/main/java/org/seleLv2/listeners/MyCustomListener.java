package org.seleLv2.listeners;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyCustomListener implements WebDriverListener {

    private final WebDriver driver;

    public MyCustomListener(WebDriver driver) {
        this.driver = driver;
    }

    private void waitForPageLoad() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            // 1. Chờ JavaScript ReadyState hoàn thành
            wait.until(d -> ((JavascriptExecutor) d)
                    .executeScript("return document.readyState").equals("complete"));

            // 2. Chờ Ajax/jQuery hoàn thành (nếu trang web có sử dụng jQuery)
            wait.until(d -> (Boolean) ((JavascriptExecutor) d)
                    .executeScript("return window.jQuery != undefined ? jQuery.active == 0 : true"));

            System.out.println("[Listener] DOM và Ajax đã load xong ổn định.");
        } catch (Exception e) {
            System.out.println("[Warning] Quá thời gian chờ DOM load nhưng vẫn tiếp tục test.");
        }
    }


    @Override
    public void afterClick(WebElement element) {
        System.out.println("[Listener] Vừa click xong element. Tiến hành chờ DOM mới...");
        waitForPageLoad();
    }


    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        System.out.println("[Listener] Vừa gõ chữ xong. Tiến hành chờ DOM mới...");
        waitForPageLoad();
    }

}
