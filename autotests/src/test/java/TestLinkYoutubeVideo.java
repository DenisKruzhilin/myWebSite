import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.Wait;
import static com.codeborne.selenide.Selenide.sleep;


public class TestLinkYoutubeVideo {
    @BeforeAll
    public static void setUp() {
        Configuration.browserSize = "1440x900";
    }

    private String resultFilePath = Paths.get(System.getProperty("user.dir"), "result__TestLinkYoutubeVideo.txt").toString();

    @Test
    @DisplayName("YoutubeVideo")
    public void YoutubeVideo() {
        deleteResultFileIfExists();
        Configuration.pageLoadTimeout = 160000;

        openUrl(Variables.getUrl());

        waitUntilPageIsLoaded();

        checkYoutubeVideo(Variables.videoOne, "Video Content one on");
        checkYoutubeVideo(Variables.videoTwo, "Video Content two on");

        checkYoutubeVideo(Variables.videoOne, "Video Content one off");
        checkYoutubeVideo(Variables.videoTwo, "Video Content two off");

        checkYoutubeVideo(Variables.videoTwo, "Video Content two on");
        checkYoutubeVideo(Variables.videoTwo, "Video Content two off");

        checkYoutubeVideo(Variables.videoOne, "Video Content one on");
        checkYoutubeVideo(Variables.videoOne, "Video Content one off");
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

    private void checkYoutubeVideo(SelenideElement YouTubeFrame, String elementName) {

        Variables.sliderInner.scrollIntoView(true);

        Variables.bullets.scrollIntoView(true);

        sleep(10000);

        YouTubeFrame.click();

        sleep(5000);

        boolean isSliderDisplayed = YouTubeFrame.isDisplayed();


        logVisibilityResult(elementName + " YouTube", isSliderDisplayed);
        writeTestResult(elementName + " YouTube - воспроизводится.", isSliderDisplayed);
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
