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

import pages.ScooterMainPage;

@RunWith(Parameterized.class)
public class FAQTest {

    private WebDriver driver;
    private ScooterMainPage scooterMainPage;
    private final By arrowLocator;
    private final By answerLocator;
    private final String expectedAnswer;

    public FAQTest(By arrowLocator, By answerLocator, String expectedAnswer){
        this.arrowLocator = arrowLocator;
        this.answerLocator = answerLocator;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Object[][] getArrowAndAnswer(){
        return new Object[][]{
                //Первый вопрос
                {By.id("accordion__heading-0"),By.xpath(".//div[@id='accordion__panel-0']/p"),
                        "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                //Второй вопрос
                {By.id("accordion__heading-1"),By.xpath(".//div[@id='accordion__panel-1']/p"),
                        "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                //Третий вопрос
                {By.id("accordion__heading-2"),By.xpath(".//div[@id='accordion__panel-2']/p"),
                        "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                //Четвертый вопрос
                {By.id("accordion__heading-3"),By.xpath(".//div[@id='accordion__panel-3']/p"),
                        "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                //Пятый Вопрос
                {By.id("accordion__heading-4"),By.xpath(".//div[@id='accordion__panel-4']/p"),
                        "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                //Шестой вопрос
                {By.id("accordion__heading-5"),By.xpath(".//div[@id='accordion__panel-5']/p"),
                        "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                //Седьмой вопрос
                {By.id("accordion__heading-6"),By.xpath(".//div[@id='accordion__panel-6']/p"),
                        "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                //Восьмой вопрос
                {By.id("accordion__heading-7"),By.xpath(".//div[@id='accordion__panel-7']/p"),
                        "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Before
    public void setUp() {
        // Инициализация ChromeDriver
        driver = new ChromeDriver();
        scooterMainPage = new ScooterMainPage(driver);
        // Открывается тестируемый сайт
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

    }

    @Test

    public void test(){
        scooterMainPage.clickOnCookieButton();
        scooterMainPage.scrollToArrow(arrowLocator);
        String actualAnswer = scooterMainPage.clickOnArrowCheckAnswer(arrowLocator, answerLocator);
        assertEquals("Текст отличается", expectedAnswer.trim(), actualAnswer.trim());

    }


    @After
    public void tearDown() {
        // Закрываем браузер после теста
        driver.quit();
    }
}
