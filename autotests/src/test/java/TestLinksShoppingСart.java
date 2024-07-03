import com.codeborne.selenide.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.JavascriptExecutor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;

public class TestLinksShoppingСart {

    @BeforeAll
    public static void setUp() {
        Configuration.browserSize = "1440x900";
    }

    private String resultFilePath = Paths.get(System.getProperty("user.dir"), "result__TestLinksShoppingСart.txt").toString();

    @Test
    @DisplayName("ShoppingCartLink")
    public void ShoppingCartLink() {
        deleteResultFileIfExists();
        Configuration.pageLoadTimeout = 160000;

        openUrl(Variables.getUrl());

        waitUntilPageIsLoaded();

        checkElementVisibilityAndLog(Variables.homePrice, "Product");
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
        element.click();

        sleep(10000);

        boolean isDisplayed = Variables.product.shouldBe(visible).exists();
        logVisibilityResult(elementName, isDisplayed);
        writeTestResult(elementName + " - Товар добавлен в корзину", isDisplayed);
    }

    private void logVisibilityResult(String elementName, boolean isDisplayed) {
        String result = isDisplayed ? "FAIL" : "SUCCESS";
        String colorCode = isDisplayed ? "\u001B[31m" : "\u001B[32m";
        System.out.println(colorCode + elementName + " - Товар добавлен в корзину: " + result);
    }

    private void writeTestResult(String testCaseInfo, boolean isSuccess) {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultFilePath, true))) {
            String result = isSuccess ? "FAIL" : "SUCCESS";
            writer.write(formattedTime + " - " + testCaseInfo + " " + result + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}