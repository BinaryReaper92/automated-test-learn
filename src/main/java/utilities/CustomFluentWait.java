package utilities;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class CustomFluentWait {
    public static SelenideElement fluentWait(SelenideElement element, Condition condition, long timeoutInSeconds, long pollingIntervalInMillis) {
        Wait<SelenideElement> wait = new FluentWait<>(element)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(pollingIntervalInMillis))
                .ignoring(NoSuchElementException.class);

        return wait.until(input -> {
            if (input.is(condition)) {
                return input;
            }
            return null;
        });
    }
}
