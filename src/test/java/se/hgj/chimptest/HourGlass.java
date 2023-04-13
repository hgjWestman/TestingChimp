package se.hgj.chimptest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HourGlass {
    private static WebDriverWait webWait;

    public static HourGlass setDriver (WebDriver driver) {
        webWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return null;
    }

    private void checkSubmittedPage() {
                webWait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".invalid-error")),
                ExpectedConditions.urlContains("/success"))
        );
    }
    public void posted() {
        checkSubmittedPage();
        }


}
