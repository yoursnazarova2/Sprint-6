package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {

    private WebDriver driver;

    // Локаторы для Выпадающего списка - FAQ
    // cookie acceptance
    private By cookie = By.xpath(".//button[text()='да все привыкли']");
    // Раздел "Вопросы о важном" - если до него потребуется промотка
    private By faqList = By.xpath(".//div[text()='Вопросы о важном']");
    // Выпадающий список с параметром (для проверок разных вопросов из списка faq)
    private By faqQuestionByNumber(int number) {
        return By.id("accordion__heading-" + number);
    }
    // Текст в выпадающем списке (параметризированный)
    private String faqAnswerXpath = ".//p[text()='%s']";
    private By faqAnswer(String answer) {
        return By.xpath(String.format(faqAnswerXpath, answer));
    }

    // Локаторы для заказа самоката
    // Кнопка "Заказать" вверху страницы
    private By orderButtonUpper = By.xpath(".//div[contains(@class, 'Header')]//button[text()='Заказать']");
    // Кнопка "Заказать" внизу страницы
    private By orderButtonLower = By.xpath(".//div[contains(@class, 'FinishButton')]//button[text()='Заказать']");

    // конструктор класса
    public MainPage(WebDriver driver){
        this.driver = driver;
    }
    // Методы для работы с элементами
    public void acceptCookie(){
        driver.findElement(cookie).click();
    }
    public void scrollToFaqList(){
        WebElement element = driver.findElement(faqList);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    public void clickFaqQuestionByNumber(int number) {
        driver.findElement(faqQuestionByNumber(number)).click();
    }
    public void checkFaqAnswerByTextIsVisible(String answer) {
        driver.findElement(faqAnswer(answer)).isDisplayed();
    }
    public void clickOrderButtonUpper() {
        driver.findElement(orderButtonUpper).click();
    }
    public void clickOrderButtonLower() {
        driver.findElement(orderButtonLower).click();
    }
}
