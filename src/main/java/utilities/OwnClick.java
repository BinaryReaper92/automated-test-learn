package utilities;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class OwnClick {

    private static boolean standardClick( SelenideElement element) throws Exception {
        try {
            if (element.isEnabled() && element.isDisplayed()) {
                OwnTrace.trace( element, "Clicking on element with standard click" );
                element.click();
            } else {
                OwnTrace.trace( element, "Unable to click on element" );
                assert false;
                return false;
            }
        } catch (StaleElementReferenceException e) {
            OwnTrace.trace( element, "Element is not attached to the page document", e );
            return false;
        } catch (NoSuchElementException e) {
            OwnTrace.trace( element, "Element was not found in DOM", e );
            return false;
        } catch (Exception e) {
            OwnTrace.trace( element, "Unable to click on element", e );
            return false;
        }
        OwnTrace.trace( element, "Successful standard click on element" );
        return true;
    }

    private static boolean javaScriptClick( SelenideElement element) throws Exception {
        try {
            if (element.isEnabled() && element.isDisplayed()) {
                OwnTrace.trace( element, "Clicking on element with javascript click" );
                JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
                js.executeScript("arguments[0].click();", element);
            } else {
                OwnTrace.trace( element, "Unable to click on element" );
                assert false;
                return false;
            }
        } catch (StaleElementReferenceException e) {
            OwnTrace.trace( element, "Element is not attached to the page document", e );
            return false;
        } catch (NoSuchElementException e) {
            OwnTrace.trace( element, "Element was not found in DOM", e );
            return false;
        } catch (Exception e) {
            OwnTrace.trace( element, "Unable to click on element", e );
            return false;
        }
        OwnTrace.trace( element, "Successful standard click on element" );

        return true;
    }

    public static void baseClick( SelenideElement element ) throws Exception {
        if( !standardClick( element ) )
        javaScriptClick( element );
    }

}
