import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.ArrayList;

/**
 * Created by Кондратов on 06.06.2018.
 */
public class FirstHomeWorkTest extends Assert {
    WebDriver driver;
    String baseURL;

    @Before
    public void beforeTestSberbank() {
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
        baseURL = "http://www.sberbank.ru/ru/person";
        driver = new ChromeDriver();
        driver.get(baseURL);
    }

    @Test
    public void testSberbank() throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//li[position()=6][@class='lg-menu__item']/span[@class='lg-menu__link']/span[@class='lg-menu__text']"));
        actions.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//a[@href='//www.sberbank.ru/ru/person/bank_inshure/insuranceprogram/travel_and_chopping']")).click();
        element = driver.findElement(By.xpath("//li[position()=6][@class='lg-menu__item']/span[@class='lg-menu__link']/span[@class='lg-menu__text']"));
        actions.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//a[@href='/ru/person/bank_inshure/insuranceprogram/travel_and_shopping'][@class='lg-menu__sub-link']")).click();
        assertEquals(driver.findElement(By.xpath("//h3[text()='Страхование путешественников']")).getText(), "Страхование путешественников");
        driver.findElement(By.xpath("//a[@href='https://online.sberbankins.ru/store/vzr/index.html#/viewCalc']")).click();
        ArrayList tabs2 = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window((String) tabs2.get(1));
        driver.findElement(By.xpath("//div[contains(text(),'35')]")).click();
        driver.findElement(By.xpath("//span[@class ='b-continue-btn']")).click();
        fillField(By.xpath("//input[@name='insured0_surname']"), "Menethil");
        fillField(By.xpath("//input[@name='insured0_name']"), "Arthas");
        fillField(By.xpath("//input[@name='insured0_birthDate']"), "22.06.1941");
        fillField(By.xpath("//input[@name='surname']"), "Иванова");
        fillField(By.xpath("//input[@name='name']"), "Ивана");
        fillField(By.xpath("//input[@name='middlename']"), "Иванович");
        fillField(By.xpath("//input[@name='birthDate']"), "09.05.1945");
        driver.findElement(By.xpath("//input[@name='female']")).click();
        fillField(By.xpath("//input[@name='passport_series']"), "9999");
        fillField(By.xpath("//input[@name='passport_number']"), "999999");
        fillField(By.xpath("//textarea[@name='issuePlace']"), "Azeroth WoW");
        fillField(By.xpath("//input[@name='issueDate']"), "12.12.2012");
        driver.findElement(By.xpath("//span[@ng-click='save()']")).click();
        assertEquals("22.06.1941",
                driver.findElement(By.xpath("//input[@name='insured0_birthDate']")).getAttribute("value"));
        assertEquals("Arthas",
                driver.findElement(By.xpath("//input[@name='insured0_name']")).getAttribute("value"));
        assertEquals("Menethil",
                driver.findElement(By.xpath("//input[@name='insured0_surname']")).getAttribute("value"));
        assertEquals("Иванова",
                driver.findElement(By.xpath("//input[@name='surname']")).getAttribute("value"));
        assertEquals("Ивана",
                driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"));
        assertEquals("Иванович",
                driver.findElement(By.xpath("//input[@name='middlename']")).getAttribute("value"));
        assertEquals("09.05.1945",
                driver.findElement(By.xpath("//input[@name='birthDate']")).getAttribute("value"));
        assertEquals("9999",
                driver.findElement(By.xpath("//input[@name='passport_series']")).getAttribute("value"));
        assertEquals("999999",
                driver.findElement(By.xpath("//input[@name='passport_number']")).getAttribute("value"));
        assertEquals("Azeroth WoW",
                driver.findElement(By.xpath("//textarea[@name='issuePlace']")).getAttribute("value"));
        String text = driver.findElement(By.xpath("//div[@ng-show='tryNext && myForm.$invalid']")).getText();
        assertEquals(text, "Заполнены не все обязательные поля");
    }

    @After
    public void afterTestSberbank() {
        driver.quit();
    }

    private void fillField(By locator, String value) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }
}
