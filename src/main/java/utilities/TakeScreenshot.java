package utilities;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class TakeScreenshot {

    public static String takeScreenshot(String screenshotName) {
        try {
            WebDriver driver = WebDriverRunner.getWebDriver();
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = "./Screenshots/" + screenshotName;
            FileUtils.copyFile(screenshot, new File(screenshotPath));
            return screenshotPath;
        } catch (IOException e) {
            Log4j.error(e.getMessage());
            return null;
        }
    }

    public static File cropImage(File inputFile, int x, int y, int width, int height) throws IOException {
        BufferedImage inputImage = ImageIO.read(inputFile);
        BufferedImage outputImage = inputImage.getSubimage(x, y, width, height);
        File outputFile = new File(inputFile.getParent(), "cropped_" + inputFile.getName());
        ImageIO.write(outputImage, "png", outputFile);
        return outputFile;
    }

    public static String takeElementScreenshot(SelenideElement element, String screenshotName) {
        try {
            File screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.FILE);
            int x = element.getLocation().getX();
            int y = element.getLocation().getY();
            int width = element.getSize().getWidth();
            int height = element.getSize().getHeight();
            File croppedScreenshot = cropImage(screenshot, x, y, width, height);
            String screenshotPath = "./Screenshots/" + screenshotName;
            org.apache.commons.io.FileUtils.copyFile(croppedScreenshot, new File(screenshotPath));
            System.out.println("Screenshot taken: " + screenshotPath);
            return screenshotPath;
        } catch (IOException e) {
            Log4j.error(e.getMessage());;
            return null;
        }
    }
}
