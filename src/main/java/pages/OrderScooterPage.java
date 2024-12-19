package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderScooterPage {

    private final WebDriver driver;
    //Локатор кнопки Далее
    private final By nextButton = By.xpath(".//*[text()='Далее']");
    //Локатор кнопки Заказать
    private final By downOrderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/*[text()='Заказать']");
    //Локатор дла поля Имя
    private final By nameField = By.xpath(".//*[@placeholder='* Имя']");
    //Локатор для поля Фамилия
    private final By surnameField = By.xpath(".//*[@placeholder='* Фамилия']");
    //Локатор для поля Адрес
    private final By addressField = By.xpath(".//*[@placeholder='* Адрес: куда привезти заказ']");
    //Локатор для поля Метро
    private final By metroField = By.xpath(".//*[@placeholder='* Станция метро']");
    //Локатор для поля Телефон
    private final By phoneField = By.xpath(".//*[@placeholder='* Телефон: на него позвонит курьер']");
    //Локатор для поля Когда привезти самокат
    private final By dateField = By.xpath(".//*[@placeholder='* Когда привезти самокат']");
    //локатор для поля Срок Аренды
    private final By rentalPeriodField = By.xpath(".//*[text()='* Срок аренды'] ");
    //Локатор для кнопки Да в окне Хотите оформить заказ
    private final By yesButton = By.xpath(".//*[text()='Да'] ");
    //Локатор для пуш уведомления об успешном оформлении заказа
    private final By successPushNotification = By.xpath(".//*[text()='Заказ оформлен']");

    public OrderScooterPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод для нажатия по кнопке Далее
    public void clickOnNextButton(){
        driver.findElement(nextButton).click();
    }
    //метод для нажатия по кнопке Заказать снизу формы заказа
    public void clickOnOrderButton(){
        driver.findElement(downOrderButton).click();
    }

    //Метод для заполнения формы Для кого самокат
    public void fillingOrderForm(String name, String surname, String address, By metroLocator, String phone){
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(metroField).click();
        driver.findElement(metroLocator).click();
        driver.findElement(phoneField).sendKeys(phone);

    }

    //Метод для заполнения формы Про аренду без цвета и комментария
    public void fillingAboutRentalForm(By dateLocator, By rentalPeriodLocator){
        driver.findElement(dateField).click();
        driver.findElement(dateLocator).click();
        driver.findElement(rentalPeriodField).click();
        driver.findElement(rentalPeriodLocator).click();

    }

    //Метод для нажатия кнопки Да
    public void clickOnYesButton(){
        driver.findElement(yesButton).click();
    }

    //Метод для обнаружения уведомления об успешном оформлении заказа
    public boolean isNotificationDisplayed(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(successPushNotification));
        return driver.findElement(successPushNotification).isDisplayed();
    }

}
