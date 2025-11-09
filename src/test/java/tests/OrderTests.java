package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.MainPage;
import pageObject.OrderPage;

import java.time.Duration;
import java.util.stream.Stream;

public class OrderTests {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @ParameterizedTest
    @MethodSource("orderDetailsMandatoryFields")
    void orderWithUpperButtonOnlyMandatoryFields (String name, String surname, String address, String metro, String phone, String date, String days) {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        mainPage.acceptCookie();
        mainPage.clickOrderButtonUpper();
        orderPage.fillFirstForm(name, surname, address, metro, phone);
        orderPage.clickNextButton();
        orderPage.fillSecondForm(date, days);
        orderPage.clickOrderButtonFormFilled();
        orderPage.clickConfirmButton();
        orderPage.checkOrderSuccessfull();
    }

    @ParameterizedTest
    @MethodSource("orderDetailsAllFields")
    void orderWithLowerButtonAllFields (String name, String surname, String address, String metro, String phone, String date, String days, String color, String comment) {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        mainPage.acceptCookie();
        mainPage.clickOrderButtonLower();
        orderPage.fillFirstForm(name, surname, address, metro, phone);
        orderPage.clickNextButton();
        orderPage.fillSecondForm(date, days);
        orderPage.selectColor(color);
        orderPage.fillCommentInput(comment);
        orderPage.clickOrderButtonFormFilled();
        orderPage.clickConfirmButton();
        orderPage.checkOrderSuccessfull();
    }

    private static Stream<Arguments> orderDetailsMandatoryFields() {
        return Stream.of(
                Arguments.of("Иван", "Иванов", "Красная Площадь", "Чикризовская", "+79818370000", "22.11.2025", "сутки"),
                Arguments.of("Маша", "Соколова", "Ул. Ленина", "Сокольники", "+79818371111", "22.12.2025", "пятеро суток")
        );
    }

    private static Stream<Arguments> orderDetailsAllFields() {
        return Stream.of(
                Arguments.of("Иван", "Иванов", "Красная Площадь", "Чикризовская", "+79818370000", "01.12.2026", "двое суток", "black", "Мне нужен твой мотоцикл"),
                Arguments.of("Маша", "Соколова", "Ул. Ленина", "Сокольники", "+79818371111", "01.01.2026", "шестеро суток", "grey", "Хочу покататься с ветерком")
        );
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

}
