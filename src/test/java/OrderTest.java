import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.time.Duration;

import static org.junit.Assert.assertEquals;

import pages.OrderScooterPage;
import pages.ScooterMainPage;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private ScooterMainPage scooterMainPage;
    private OrderScooterPage orderScooterPage;
    private final String orderButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String metroLocator;
    private final String phone;
    private final String dateLocator;
    private final String rentalPeriodLocator;
    private final boolean expected;

    public OrderTest(String orderButton, String name, String surname, String address, String metroLocator, String phone, String dateLocator, String rentalPeriodLocator, boolean expected) {
        this.orderButton = orderButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroLocator = metroLocator;
        this.phone = phone;
        this.dateLocator = dateLocator;
        this.rentalPeriodLocator = rentalPeriodLocator;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderdetails(){
        return new Object[][]{
                //Оформеление заказа при нажатии на кнопку Заказать в шапке сайта
                //Первый заказ
                {".Button_Button__ra12g","Андрей", "Кан", "Улица 42", ".//*[text()='Красносельская']", "+77077777777",
                        ".//*[text()='23']", ".//*[text()='сутки']", true},
                //Второй заказ
                {".Button_Button__ra12g", "Ондрей", "Кин", "Улица 52", ".//*[text()='Сокольники']", "+77088888888",
                        ".//*[text()='24']", ".//*[text()='двое суток']", true},
        };
    }

    @Before
    public void setUp() {
        // Инициализация ChromeDriver
        driver = new ChromeDriver();
        scooterMainPage = new ScooterMainPage(driver);
        orderScooterPage = new OrderScooterPage(driver);
        // Открывается тестируемый сайт
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

    }


    @Test
    public void runHeaderButtonOrderTest(){
        scooterMainPage.clickOnCookieButton();
        scooterMainPage.clickOnOrderButton(By.cssSelector(orderButton));
        orderScooterPage.fillingOrderForm(name, surname, address, By.xpath(metroLocator), phone);
        orderScooterPage.clickOnNextButton();
        orderScooterPage.fillingAboutRentalForm(By.xpath(dateLocator), By.xpath(rentalPeriodLocator));
        orderScooterPage.clickOnOrderButton();
        orderScooterPage.clickOnYesButton();

        boolean actual = orderScooterPage.isNotificationDisplayed();
        assertEquals("Уведомление об успешном оформлении заказа не появилось", expected, actual);

    }

    @After
    public void tearDown() {
        // Закрываем браузер после теста
        driver.quit();
    }

}
