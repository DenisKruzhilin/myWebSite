import com.codeborne.selenide.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;

public class TestLinkTechnicalSupport {

    @BeforeAll
    public static void setUp() {
        Configuration.browserSize = "1440x900";
        Configuration.timeout = 6000;
    }

    private String resultFilePath = Paths.get(System.getProperty("user.dir"), "result__TestLinkTechnicalSupport.txt").toString();

    @Test
    @DisplayName("TechnicalSupportLink")
    public void TechnicalSupportLink() {
        deleteResultFileIfExists();
        Configuration.pageLoadTimeout = 160000;

        openUrl(Variables.getUrl());

        waitUntilPageIsLoaded();

        checkElementVisibilityAndLog(Variables.technicalSupport, "Product");
    }

    private void openUrl(String url) {
        Selenide.open(url);
    }

    private void deleteResultFileIfExists() {
        File file = new File(resultFilePath);
        if (file.exists()) {
            file.delete();
        }
    }

    private void waitUntilPageIsLoaded() {
        Wait().until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    private void checkElementVisibilityAndLog(SelenideElement element, String elementName) {
        Variables.footer.click();
        element.shouldBe(visible, Duration.ofSeconds(10)).click();
        element.setValue("test text");
        element.pressEnter();

        boolean isDisplayed = Variables.technicalSupportText.shouldBe(visible).exists();

        logVisibilityResult(elementName, isDisplayed);
        writeTestResult(elementName + " - Успешный переход на страницу cлужба поддержки клиентов", isDisplayed);
    }

    private void logVisibilityResult(String elementName, boolean isDisplayed) {
        String result = isDisplayed ? "SUCCESS" : "FAIL";
        String colorCode = isDisplayed ? "\u001B[32m" : "\u001B[31m";
        System.out.println(colorCode + elementName + " - Успешный переход на страницу cлужба поддержки клиентов: " + result);
    }

    private void writeTestResult(String testCaseInfo, boolean isSuccess) {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultFilePath, true))) {
            String result = isSuccess ? "SUCCESS" : "FAIL";
            writer.write(formattedTime + " - " + testCaseInfo + " " + result + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
