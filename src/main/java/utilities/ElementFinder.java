package utilities;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ElementFinder {
    public static SelenideElement myFindElementById(String locator){
        try {
            return $(getWebDriver().findElement(By.id(locator)));
        } catch (NoSuchElementException e) {
            OwnTrace.trace("Unable to find element: '"+locator+"'");
            System.out.println("Unable to find element " + e.getMessage());
        }
        return null;
    }

    public static SelenideElement myFindElementByCSS(String locator){
        try {
            return $(locator);
        } catch (NoSuchElementException e) {
            OwnTrace.trace("Unable to find element: '"+locator+"'");
            System.out.println("Unable to find element " + e.getMessage());
        }
        return null;
    }

    public static SelenideElement myFindElementByXpath(String locator){
        try {
            return $(By.xpath(locator));
        } catch (NoSuchElementException e) {
            Log4j.error("Unable to find element: '"+locator+"'");
            System.out.println("Unable to find element " + e.getMessage());
        }
        return null;
    }

    public static SelenideElement myFindElementByText(String locator){
        try {
            return $(By.xpath(".//*[text()='" + locator + "']")).shouldBe(visible);
        } catch (NoSuchElementException e) {
            OwnTrace.trace("Unable to find element: '"+locator+"'");
            System.out.println("Unable to find element " + e.getMessage());
        }
        return null;
    }
    public static SelenideElement myFindElementByContainedText(String locator){
        try {
            return $(getWebDriver().findElement(By.xpath("//span[contains(text(), '" + locator + "')]")));
        } catch (NoSuchElementException e) {
            OwnTrace.trace("Unable to find element: '"+locator+"'");
            System.out.println("Unable to find element " + e.getMessage());
        }
        return null;
    }

    public static SelenideElement myFindElementByLinkText(String locator){
        try {
            return $(By.linkText(locator)).shouldBe(visible);
        } catch (NoSuchElementException e) {
            OwnTrace.trace("Unable to find element: '"+locator+"'");
            System.out.println("Unable to find element " + e.getMessage());
        }
        return null;
    }
    public static SelenideElement myFindElementByClass(String locator){
        try {
            return $(By.className(locator));
        } catch (NoSuchElementException e) {
            OwnTrace.trace("Unable to find element: '"+locator+"'");
            System.out.println("Unable to find element " + e.getMessage());
        }
        return null;
    }

    public static SelenideElement myFindElement(String locator){
        SelenideElement element = null;
        element = myFindElementByXpath(locator);

        if (element == null) {
            element = myFindElementByCSS(locator);
        }
        if (element == null) {
            element = myFindElementById(locator);
        }
        if (element == null) {
            element = myFindElementByClass(locator);
        }
        if (element == null) {
            element = myFindElementByText(locator);
        }
        if (element == null) {
            element = myFindElementByContainedText(locator);
        }
        if (element == null) {
            element = myFindElementByLinkText(locator);
        }
        if (element == null) {
            Log4j.error("Unable to find element: '"+locator+"'");
            throw new NoSuchElementException("Tag not found: '"+locator+"'");
        }
        return element;
    }

}
