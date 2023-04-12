package utilities;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Helper {
    public static void waitForElementToAppear(SelenideElement element, long timeoutMillis) {
        FluentWait<SelenideElement> wait = new FluentWait<>(element)
                .withTimeout(Duration.ofMillis(timeoutMillis))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);
        wait.until(SelenideElement::isDisplayed);
    }

    public static void waitForPageLoad(WebDriver driver, long timeoutSeconds) {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until(pageLoadCondition);
    }


    public static void waitForElementToDisappear(SelenideElement element, long timeoutMillis) {
        element.should(Condition.disappear, Duration.ofMillis(timeoutMillis));
    }


    public static void waitForElementsToDisappear(ElementsCollection collection, long timeoutMillis) {
        Selenide.Wait()
                .withTimeout(Duration.ofMillis(timeoutMillis))
                .until(driver -> collection.size() <= 0);
    }


    public static void scrollToElement(SelenideElement element) {
        element.scrollTo();
    }


    public static void scrollToBottom() {
        Selenide.executeJavaScript("window.scrollTo(0, document.body.scrollHeight);");
    }


    public static void scrollToTop() {
        Selenide.executeJavaScript("window.scrollTo(0, 0);");
    }

}
