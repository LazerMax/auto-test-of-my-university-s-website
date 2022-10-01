import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class automation {

    private WebDriver webDriver;

    @Before //класс открытия браузера и получения адреса и тайтла страницы
    public void setUp(){
        System.out.println("Открыть браузер");
        //System.setProperty("webdriver.chrome.driver", "C:\\apache-maven-3.8.3\\chromedriver.exe"); //подключение chromedriver
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();//открытие браузера

        webDriver.get("https://www.miit.ru/");

        System.out.println(webDriver.getCurrentUrl());//получение URL
        System.out.println(webDriver.getTitle());//получение тайтла страницы
    }


        @Test//проверка URL и Тайтла Входа страницы входа в личный кабинет
        public void A_URL_and_Title_Test() {
       System.out.println("Проверка URL и Тайтла ");
        webDriver.get("https://rut-miit.ru/cabinet/hello/login.jsp");
        assertEquals("https://rut-miit.ru/cabinet/hello/login.jsp", webDriver.getCurrentUrl());//проверка URL
    }


    @Test//проверка раздела поддержки
    public void B_Help_Test() {
        webDriver.get("https://rut-miit.ru/cabinet/hello/login.jsp");
        webDriver.findElement(By.xpath("//a[@href='help.jsp']")).click();
        assertTrue(webDriver.findElement(By.xpath("//a[@href='tel:+74956629943']")).getText().contains("+7 (495) 662-99-43"));
        assertTrue(webDriver.findElement(By.xpath("//a[@href='mailto:support@rut-miit.ru?subject=Вход в Личный кабинет']")).getText().contains("support@rut-miit.ru"));
        webDriver.findElement(By.xpath("//a[@href = 'https://www.google.ru/search?q=Google+Chrome']")).click();
        webDriver.findElement(By.xpath("//a[@href = 'https://www.google.ru/search?q=Mozilla+Firefox'] ")).click();
        webDriver.findElement(By.xpath("//a[@href = 'http://whatbrowser.ru']")).click();
        webDriver.findElement(By.xpath("//a[@href = 'login.jsp'] ")).click();
    }



    @Test//проверка кнопки "Проверка входа в личный кабинет при наличии верных данных"
    public void C_Button_open_disk1(){
        webDriver.get("https://rut-miit.ru/cabinet/hello/login.jsp");
        System.out.println("ввод верного логина");
        webDriver.findElement(By.id("login")).sendKeys("");
        System.out.println("ввод верного пароля");
        webDriver.findElement(By.id("password")).sendKeys("");

        webDriver.findElement(By.xpath("//button")).click();
        System.out.println("Проверка кнопки");
        assertTrue(webDriver.findElement(By.xpath("//*[text()='Объявления']")).getText().contains("Объявления"));
    }


    @Test//проверка кнопки "Проверка входа в личный кабинет при наличии неверных данных"
    public void C_Button_open_disk2(){
        webDriver.get("https://rut-miit.ru/cabinet/hello/login.jsp");
        System.out.println("ввод неверного логина");
        webDriver.findElement(By.id("login")).sendKeys("12345");
        System.out.println("ввод неверного пароля");
        webDriver.findElement(By.id("password")).sendKeys("12345");

        webDriver.findElement(By.xpath("//button")).click();
        System.out.println("Проверка кнопки");
      assertTrue(webDriver.findElement(By.xpath("//*[text()='Не удалось выполнить вход']")).getText().contains("Не удалось выполнить вход"));
    }

}
