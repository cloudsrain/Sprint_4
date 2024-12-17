package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//Класс главной страницы Яндекс Самокат
public class ScooterMainPage {

    private final WebDriver driver;

    //Локатор для кнопки Cookie
    private final By cookieButton = By.className("App_CookieButton__3cvqF");

    public ScooterMainPage(WebDriver driver){
        this.driver = driver;
    }

    //Метод для нажатия кнопки Заказать в шапке сайте либо снизу сайта
    public void clickOnOrderButton(By orderButton){
        driver.findElement(orderButton).click();

    }

    //Метод для закрытия пуш уведомления о Cookie
    public void clickOnCookieButton(){
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(cookieButton));
        driver.findElement(cookieButton).click();
    }

    //Метод для скролла страницы до стрелки в выпадающем списке
    public void scrollToArrow(By arrowLocator){
        WebElement element = driver.findElement(arrowLocator);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //Метод для нажатия по стрелке в выпадающем списке и проверки ответа на соответствие
    public String clickOnArrowCheckAnswer(By arrowLocator, By answerLocator){

        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(arrowLocator));
        driver.findElement(arrowLocator).click();
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return driver.findElement(answerLocator).getText();

    }

}
