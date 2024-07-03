import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;

public class TestSuite {

    private static PrintWriter writer;

    public static void main(String[] args) {
        TestSuite testSuite = new TestSuite();
        testSuite.runTests();
    }

    public void runTests() {
        initializeReportFile();

        runTest("MenuLinks", () -> {
            TestLinks testLinks = new TestLinks();
            testLinks.setUp();
            testLinks.MenuLinks();
        });

        runTest("ShoppingCartLink", () -> {
            TestLinksShoppingСart testCart = new TestLinksShoppingСart();
            testCart.ShoppingCartLink();
        });

        runTest("DiscountLink", () -> {
            TestLinkDiscount testDiscount = new TestLinkDiscount();
            testDiscount.DiscountLink();
        });

        runTest("AirpotsLink", () -> {
            TestLinkAirpots testLinkAirpots = new TestLinkAirpots();
            testLinkAirpots.AirpotsLink();
        });

        runTest("IphoneLink", () -> {
            TestLinkIphone testLinkIphone = new TestLinkIphone();
            testLinkIphone.IphoneLink();
        });

        runTest("AppleWatchLink", () -> {
            TestLinkAppleWatch testAppleWatch = new TestLinkAppleWatch();
            testAppleWatch.AppleWatchLink();
        });

        runTest("AppleMacLink", () -> {
            TestLinkAppleMac testAppleMac = new TestLinkAppleMac();
            testAppleMac.AppleMacLink();
        });

        runTest("AppleVisionProLink", () -> {
            TestLinkAppleVisionPro testVisionPro = new TestLinkAppleVisionPro();
            testVisionPro.AppleVisionProLink();
        });

        runTest("ServiceCentersLink", () -> {
            TestLinkServiceCenters testServiceCenters = new TestLinkServiceCenters();
            testServiceCenters.ServiceCentersLink();
        });

        runTest("ConfidentialityPolicyLink", () -> {
            TestLinkConfidentialityPolicy testConfidentialityPolicy = new TestLinkConfidentialityPolicy();
            testConfidentialityPolicy.ConfidentialityPolicyLink();
        });

        runTest("TradeInProgramLink", () -> {
            TestLinkTradeInProgram testTradeInProgram = new TestLinkTradeInProgram();
            testTradeInProgram.ConfidentialityPolicyLink();
        });

        runTest("GiftCardLink", () -> {
            TestLinkGiftCard testLinkGiftCard = new TestLinkGiftCard();
            testLinkGiftCard.GiftCardLink();
        });

        runTest("TechnicalSupportLink", () -> {
            TestLinkTechnicalSupport testTechnicalSupport = new TestLinkTechnicalSupport();
            testTechnicalSupport.TechnicalSupportLink();
        });

        runTest("Slider", () -> {
            TestSlider testSlider = new TestSlider();
            testSlider.SlidesLinks();
        });

        runTest("YouTube", () -> {
            TestLinkYoutubeVideo testYoutubeVideo = new TestLinkYoutubeVideo();
            testYoutubeVideo.YoutubeVideo();
        });

        if (writer != null) {
            writer.close();
        }
    }

    private void initializeReportFile() {
        File reportFile = new File("test_report.txt");
        if (reportFile.exists()) {
            reportFile.delete();
        }
        try {
            writer = new PrintWriter(new FileWriter(reportFile, true));
        } catch (IOException e) {
            System.out.println("Error initializing report file: " + e.getMessage());
        }
    }

    private void runTest(String testName, Runnable test) {
        LocalDateTime startTime = LocalDateTime.now();
        writer.println("Test started: " + testName + " at " + startTime);
        try {
            long pageLoadStart = System.currentTimeMillis();
            test.run();
            long pageLoadTime = System.currentTimeMillis() - pageLoadStart;
            LocalDateTime endTime = LocalDateTime.now();
            Duration testDuration = Duration.between(startTime, endTime);
            writer.println("SUCCESS: " + testName);
            writer.println("Page load time: " + pageLoadTime + " ms");
            writer.println("Test duration: " + testDuration.toMillis() + " ms");
        } catch (Exception e) {
            LocalDateTime endTime = LocalDateTime.now();
            Duration testDuration = Duration.between(startTime, endTime);
            writer.println("FAILED: " + testName + " - " + e.getMessage());
            writer.println("Test duration: " + testDuration.toMillis() + " ms");
        } finally {
            writer.println("Test finished: " + testName + " at " + LocalDateTime.now());
            writer.println();
            writer.flush();
        }
    }
}
