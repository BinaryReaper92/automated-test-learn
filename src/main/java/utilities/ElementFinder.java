package utilities;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static com.codeborne.selenide.Selenide.$;
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
            return $(getWebDriver().findElement(By.cssSelector(locator)));
        } catch (NoSuchElementException e) {
            OwnTrace.trace("Unable to find element: '"+locator+"'");
            System.out.println("Unable to find element " + e.getMessage());
        }
        return null;
    }

    public static SelenideElement myFindElementByXpath(String locator){
        try {
            return $(getWebDriver().findElement(By.xpath(locator)));
        } catch (NoSuchElementException e) {
            OwnTrace.trace("Unable to find element: '"+locator+"'");
            System.out.println("Unable to find element " + e.getMessage());
        }
        return null;
    }

    public static SelenideElement myFindElementByText(String locator){
        try {
            return $(getWebDriver().findElement(By.xpath(".//*[text()='" + locator + "'] ")));
        } catch (NoSuchElementException e) {
            OwnTrace.trace("Unable to find element: '"+locator+"'");
            System.out.println("Unable to find element " + e.getMessage());
        }
        return null;
    }

    public static SelenideElement myFindElementByLinkText(String locator){
        try {
            return $(getWebDriver().findElement(By.linkText(locator)));
        } catch (NoSuchElementException e) {
            OwnTrace.trace("Unable to find element: '"+locator+"'");
            System.out.println("Unable to find element " + e.getMessage());
        }
        return null;
    }

    public static SelenideElement myFindElement(String locator){
        SelenideElement element = null;
        element = element = myFindElementByXpath(locator);

        if (element == null) {
            element = myFindElementById(locator);
        }
        if (element == null) {
            element = myFindElementByText(locator);
        }
        if (element == null) {
            element = myFindElementByLinkText(locator);
        }
        if (element == null) {
            element = myFindElementByCSS(locator);
        }
        if (element == null) {
            OwnTrace.trace("Unable to find element: '"+locator+"'");
            throw new NoSuchElementException("Tag not found: '"+locator+"'");
        }
        return element;
    }

}
