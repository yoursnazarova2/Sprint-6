package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage {

    private WebDriver driver;

    // Локаторы для формы заказа (описаны только те, что будут использованы в тестах)
    // Поле "Имя"
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    // Поле "Фамилия"
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    // Поле "Адрес"
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // Поле "Станция метро"
    private By metroStationField = By.xpath(".//input[@placeholder='* Станция метро']");
    // Поле "Телефон"
    private By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка "Далее"
    private By nextButton = By.xpath(".//button[text()='Далее']");
    // Поле "Когда привезти самокат"
    private By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // Поле "Срок аренды"
    private By orderLengthList = By.className("Dropdown-placeholder");
    // Значения выпадающего списка с возможностью дальнейшей параметризации тестов
    private String orderLengthXpath = ".//div[contains(@class, 'Dropdown-option') and contains(text(),'%s')]";
    private By orderLengthDays(String days) {
        return By.xpath(String.format(orderLengthXpath, days));
    }
    // Чекбоксы "Цвет самоката" (параметризация)
    private By colorCheckBox (String color) {
        return By.id(color);
    }
    // Поле "Комментарий для курьера"
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    // Кнопка "Заказать"
    private By orderButtonFormFilled = By.xpath(".//button[contains(@class, 'Middle')][text()='Заказать']");
    // Кнопка "Да" (во всплывающем окне)
    private By confirmButton = By.xpath(".//button[text()='Да']");
    // Успешно оформленный заказ (всплывашка)
    private By successfulOrder = By.xpath(".//div[text()='Заказ оформлен']");

    // конструктор класса
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Методы для работы с элементами
    public void fillNameInput (String name){
        driver.findElement(nameField).sendKeys(name);
    }
    public void fillSurnameInput (String surname){
        driver.findElement(surnameField).sendKeys(surname);
    }
    public void fillAddressInput (String address){
        driver.findElement(addressField).sendKeys(address);
    }
    public void fillMetroStationInput (String metro){
        driver.findElement(metroStationField).sendKeys(metro);
    }
    public void fillPhoneInput (String phone){
        driver.findElement(phoneField).sendKeys(phone);
    }
    public void clickNextButton(){
        driver.findElement(nextButton).click();
    }
    public void fillDateInput (String date){
        driver.findElement(dateField).sendKeys(date);
    }
    public void clickOrderLengthList(){
        driver.findElement(orderLengthList).click();
    }
    public void clickOrderLengthDays(String days) {
        driver.findElement(orderLengthDays(days)).click();
    }
    public void selectColor(String color) {
        driver.findElement(colorCheckBox(color)).click();
    }
    public void fillCommentInput (String comment){
        driver.findElement(commentField).sendKeys(comment);
    }
    public void clickOrderButtonFormFilled(){
        driver.findElement(orderButtonFormFilled).click();
    }
    public void clickConfirmButton(){
        driver.findElement(confirmButton).click();
    }

    // кажется, будет лучше объединить часть методов в более крупный блок, чтобы сами тесты были лаконичнее + если будут изменения в форме, то менять можно будет только в описании страницы
    public void fillFirstForm (String name, String surname, String address, String metro, String phone) {
        fillNameInput(name);
        fillSurnameInput(surname);
        fillAddressInput(address);
        fillMetroStationInput(metro);
        fillPhoneInput(phone);
    }
    // здесь не заполняются необязательные поля, чтобы можно было проводить тесты с разным набором данным (полная проверка и проверка только обязательных полей)
    public void fillSecondForm (String date, String days) {
        fillDateInput(date);
        clickOrderLengthList();
        clickOrderLengthDays(days);
    }

    public void checkOrderSuccessfull(){
        driver.findElement(successfulOrder).isDisplayed();
    }
}
