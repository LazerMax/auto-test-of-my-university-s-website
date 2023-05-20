import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AuthorizationTest {
    private WebDriver webDriver;

    @Before //класс открытия браузера и получения адреса и тайтла страницы
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();//открытие браузера
        Dimension dimension = new Dimension(1600, 1000);
        webDriver.manage().window().setSize(dimension);
        webDriver.get("https://rut-miit.ru/cabinet/hello/login.jsp");
    }

    @After
    public void clean () {
        if (webDriver != null)
            webDriver.quit();
    }

    @Test//проверка URL и Тайтла Входа страницы входа в личный кабинет
    public void A_URL_and_Title_Test() {
        assertEquals("https://rut-miit.ru/cabinet/hello/login.jsp", webDriver.getCurrentUrl());//проверка URL
        assertEquals("РУТ (МИИТ) | Личный кабинет", webDriver.getTitle());//проверка Тайтла
    }

    @Test//проверка раздела поддержки
    public void B_Help_Test() {
        webDriver.findElement(By.xpath("//a[@href='help.jsp']")).click();
        assertTrue(webDriver.findElement(By.xpath("//a[@href='tel:+74956629943']"))
                .getText().contains("+7 (495) 662-99-43"));
        assertTrue(webDriver.findElement(By.xpath("//a[@href='mailto:support@rut-miit.ru?subject=Вход в Личный кабинет']"))
                .getText().contains("support@rut-miit.ru"));
        webDriver.findElement(By.xpath("//a[@href = 'login.jsp'] ")).click();
    }

    @Test//проверка кнопки "Проверка входа в личный кабинет при наличии верных данных"
    public void rightLogin(){
        webDriver.findElement(By.id("login")).sendKeys("felshines@gmail.com");
        webDriver.findElement(By.id("password")).sendKeys("QER9D");
        webDriver.findElement(By.xpath("//button")).click();
        assertTrue(webDriver.findElement(By.xpath("//*[text()='Объявления']")).getText().contains("Объявления"));
    }

    @Test//проверка кнопки "Проверка входа в личный кабинет при наличии неверных данных"
    public void wrongLogin(){
        webDriver.get("https://rut-miit.ru/cabinet/hello/login.jsp");
        webDriver.findElement(By.id("login")).sendKeys("12345");
        webDriver.findElement(By.id("password")).sendKeys("12345");
        webDriver.findElement(By.xpath("//button")).click();
        assertTrue(webDriver.findElement(By.xpath("//*[text()='Не удалось выполнить вход']"))
                .getText().contains("Не удалось выполнить вход"));
    }
}
