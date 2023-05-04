package utilities;

import com.codeborne.selenide.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class Helper {
    public static void waitForElementToAppear(SelenideElement element, long timeoutMillis) {
        FluentWait<SelenideElement> wait = new FluentWait<>(element)
                .withTimeout(Duration.ofMillis(timeoutMillis))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);
        wait.until(SelenideElement::isDisplayed);
    }

    public static void compareText(SelenideElement element, String expectedText) {
        String actualText = element.shouldBe(Condition.visible).getText();
        Log4j.info("Comparing actual text: "+actualText +" to expected text: "+expectedText+ " .");
        Assert.assertEquals(actualText, expectedText, "Text comparison failed: ");
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

    public static void doubleClick(SelenideElement element) {
        Actions actions = new Actions(getWebDriver());
        actions.doubleClick(element).perform();
    }

    public static void rightClick(SelenideElement element) {
        Actions actions = new Actions(getWebDriver());
        actions.contextClick(element).perform();
    }
    public static void hover(SelenideElement element) {
        element.hover();
    }

    public static void dragAndDrop(SelenideElement source, SelenideElement target) {
        Actions actions = new Actions(getWebDriver());
        actions.dragAndDrop(source, target).perform();
    }

    public static void selectOption(SelenideElement element, String option) {
        element.selectOption(option);
    }

    public static void setClipboardContent(String content) {
        StringSelection stringSelection = new StringSelection(content);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    public static void windowsHelper() throws AWTException, InterruptedException {
        Robot robot = new Robot();
        // Press Ctrl + V to paste the file path
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        // Press Enter to confirm the file selection
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

}
