import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Variables {
    public static String getUrl() {
        return "http://92.51.36.108:7777/denis.kruzhilin/progect_top/index.html";
    }

    public static SelenideElement home = $("a[href='#home']");
    public static SelenideElement specs = $("a[href='#specs']");
    public static SelenideElement slider = $("a[href='#slider']");
    public static SelenideElement footer = $("a[href='#footer']");

    public static SelenideElement homePrice = $(".home__price");
    public static SelenideElement product = $(byText("Ваша корзина пуста"));

    public static SelenideElement discountContainer = $(".discount__container");
    public static SelenideElement discount = $(By.xpath("//section[contains(@class, 'discount')]//a[contains(@class, 'button')]"));
    public static SelenideElement textDiscount = $(".extra-tab[data-href='/actions/']");

    public static SelenideElement airpots  = $(By.xpath("//li/a[contains(@href, 'apple') and contains(@class, 'footer__link') and text()='Apple Airpods']"));
    public static SelenideElement textAirpots = $(By.xpath("//div[@class='container']/h1[text()='Беспроводные наушники Apple']"));

    public static SelenideElement iphone = $(By.xpath("//li/a[contains(@href, 'apple') and contains(@class, 'footer__link') and text()='iPhone']"));
    public static SelenideElement textIphone = $(By.xpath("//div[@class='container']/h1[text()='iPhone']"));

    public static SelenideElement ipad = $(By.xpath("//li/a[contains(@href, 'apple') and contains(@class, 'footer__link') and text()='iPad']"));
    public static SelenideElement textIpad = $(By.xpath("//div[@class='container']/h1[text()='iPad']"));

    public static SelenideElement appleWatch = $(By.xpath("//li/a[contains(@href, 'apple') and contains(@class, 'footer__link') and text()='Apple Watch']"));
    public static SelenideElement textAppleWatch = $(By.xpath("//div[@class='container']/h1[text()='Apple Watch']"));

    public static SelenideElement appleMac = $(By.xpath("//li/a[contains(@href, 'apple') and contains(@class, 'footer__link') and text()='Apple Mac']"));
    public static SelenideElement textAppleMac = $(By.xpath("//div[@class='container']/h1[text()='Mac']"));

    public static SelenideElement appleVisionPro = $(By.xpath("//li/a[@href='https://re-store.ru/catalog/MQLA3/' and @class='footer__link']"));
    public static SelenideElement textAppleVisionPro = $(By.xpath("//h1[@class='detail-heading__heading h4' and @data-v-1fbe4952='']"));

    public static SelenideElement serviceCenters = $(By.xpath("//li/a[@href='https://re-store.ru/promo/care-nsc/' and @class='footer__link']"));
    public static SelenideElement serviceCentersText = $(By.xpath("//h1[@class='nsc-cover__title' and text()='Независимые ']"));

    public static SelenideElement сonfidentialityPolicy = $(By.xpath("//li/a[@href='https://re-store.ru/oferta/politika/' and @class='footer__link']"));
    public static SelenideElement сonfidentialityPolicyText = $(By.xpath("//h1[text()='Политика конфиденциальности']"));

    public static SelenideElement tradeInPolicy = $(By.xpath("//li/a[@href='https://re-store.ru/promo/trade-in/' and @class='footer__link']"));
    public static SelenideElement tradeInPolicyText = $(By.xpath("//div[@class='ti-title' and text()='Как работает trade-in?']"));

    public static SelenideElement giftCard = $(By.xpath("//li/a[@href='https://re-store.ru/gift/' and @class='footer__link']"));
    public static SelenideElement giftCardText = $(".gift-banner");

    public static SelenideElement sliderInner = $("#inner");
    public static SelenideElement technicalSupport = $x("//input[@id='searchInput']");
    public static SelenideElement technicalSupportText = $x("//h1[text()='Служба поддержки клиентов']");
    public static SelenideElement slideOne = $("label[for='slide-one']");
    public static SelenideElement slideTwo = $("label[for='slide-two']");
    public static SelenideElement slideThree = $("label[for='slide-three']");
    public static SelenideElement slideFour = $("label[for='slide-four']");
    public static SelenideElement slideFive = $("label[for='slide-five']");

    public static SelenideElement bullets = $(".bullets");
    public static SelenideElement videoOne = $(".video__iframe-one");
    public static SelenideElement videoTwo = $(".video__iframe-two");
}
