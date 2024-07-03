import com.codeborne.selenide.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.Wait;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestSlider {
    @BeforeAll
    public static void setUp() {
        Configuration.browserSize = "1440x900";
    }

    private String resultFilePath = Paths.get(System.getProperty("user.dir"), "result__TestSlider.txt").toString();

    @Test
    @DisplayName("SlidesLinks")
    public void SlidesLinks() {
        deleteResultFileIfExists();
        Configuration.pageLoadTimeout = 160000;

        openUrl(Variables.getUrl());

        waitUntilPageIsLoaded();

        checkSliderAndImage(Variables.slideTwo, "Two");
        checkSliderAndImage(Variables.slideThree, "Three");
        checkSliderAndImage(Variables.slideFour, "Four");
        checkSliderAndImage(Variables.slideFive, "Five");
        checkSliderAndImage(Variables.slideOne, "One");
        checkSliderAndImage(Variables.slideFive, "Five");
        checkSliderAndImage(Variables.slideThree, "Three");
        checkSliderAndImage(Variables.slideFour, "Four");
        checkSliderAndImage(Variables.slideTwo, "Two");
    }

    private void openUrl(String url) {
        Selenide.open(url);
    }

    private void waitUntilPageIsLoaded() {
        Wait().until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    private void deleteResultFileIfExists() {
        try {
            Files.deleteIfExists(Path.of(resultFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkSliderAndImage(SelenideElement sliderBullets, String elementName) {

        Variables.sliderInner.scrollIntoView(true);

        sleep(500);

        sliderBullets.click();

        boolean isSliderDisplayed = sliderBullets.isDisplayed();


        logVisibilityResult(elementName + " Slider", isSliderDisplayed);
        writeTestResult(elementName + " Slider - слайд переключен успешно.", isSliderDisplayed);
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
