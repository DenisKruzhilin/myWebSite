import com.codeborne.selenide.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.Wait;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;

public class TestLinks {

    @BeforeAll
    public static void setUp() {
        Configuration.browserSize = "1440x900";
    }

    private String resultFilePath = Paths.get(System.getProperty("user.dir"), "result__TestLinks.txt").toString();



    @Test
    @DisplayName("MenuLinks")
    public void MenuLinks() {
        deleteResultFileIfExists();
        Configuration.pageLoadTimeout = 160000;

        Selenide.open(Variables.getUrl());

        waitUntilPageIsLoaded();

        Variables variables = new Variables();

        checkElementVisibilityAndLog(variables.specs, "Specs");
        checkElementVisibilityAndLog(variables.footer, "Footer");
        checkElementVisibilityAndLog(variables.slider, "Slider");
        checkElementVisibilityAndLog(variables.home, "Home");
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
        element.click();
        element.scrollIntoView(true);

        boolean isDisplayed = element.isDisplayed();
        logVisibilityResult(elementName, isDisplayed);
        writeTestResult(elementName + " - Элемент виден в области экрана", isDisplayed);
    }

    private void logVisibilityResult(String elementName, boolean isDisplayed) {
        String result = isDisplayed ? "SUCCESS" : "FAIL";
        String colorCode = isDisplayed ? "\u001B[32m" : "\u001B[31m";
        System.out.println(colorCode + elementName + " - Элемент виден в области экрана: " + result);
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
