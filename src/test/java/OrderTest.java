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
    private final By orderButton;
    private final String name;
    private final String surname;
    private final String address;
    private final By metroLocator;
    private final String phone;
    private final By dateLocator;
    private final By rentalPeriodLocator;
    private final boolean expected;

    public OrderTest(By orderButton, String name, String surname, String address, By metroLocator, String phone, By dateLocator, By rentalPeriodLocator, boolean expected) {
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
                {By.cssSelector(".Button_Button__ra12g"),"Андрей", "Кан", "Улица 42", By.xpath(".//*[text()='Красносельская']"), "+77077777777",
                        By.xpath(".//*[text()='23']"), By.xpath(".//*[text()='сутки']"), true},
                //Второй заказ
                {By.cssSelector(".Button_Button__ra12g"), "Ондрей", "Кин", "Улица 52", By.xpath(".//*[text()='Сокольники']"), "+77088888888",
                        By.xpath(".//*[text()='24']"), By.xpath(".//*[text()='двое суток']"), true},
                //Оформление заказа при нажатии на кнопку Заказать снизу сайта
                //Первый заказ
                {By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM"),"Индрей", "Кун", "Улица 62", By.xpath(".//*[text()='Красносельская']"), "+77011111111",
                        By.xpath(".//*[text()='25']"), By.xpath(".//*[text()='трое суток']"), true},
                //Второй заказ
                {By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM"), "Ундрей", "Кон", "Улица 72", By.xpath(".//*[text()='Сокольники']"), "+77099999999",
                        By.xpath(".//*[text()='26']"), By.xpath(".//*[text()='четверо суток']"), true},
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
    public void testUpperOrderButton() {
        if (orderButton.equals(By.cssSelector(".Button_Button__ra12g"))) {
            OrderTest();
        }
    }

    @Test
    public void testLowerOrderButton() {
        if (orderButton.equals(By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM"))) {
            OrderTest();
        }
    }
    public void OrderTest(){
        scooterMainPage.clickOnCookieButton();
        scooterMainPage.clickOnOrderButton(orderButton);
        orderScooterPage.fillingOrderForm(name, surname, address, metroLocator, phone);
        orderScooterPage.clickOnNextButton();
        orderScooterPage.fillingAboutRentalForm(dateLocator, rentalPeriodLocator);
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
