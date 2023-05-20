import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class SearchTest {
    private WebDriver webDriver;

    @Before
    public void setUp () {
        WebDriverManager.chromedriver().setup();
        webDriver=new ChromeDriver();
        Dimension dimension = new Dimension(1600, 1000);
        webDriver.manage().window().setSize(dimension);
        webDriver.get("https://www.miit.ru/");
    }


    @After
    public void clean () {
        if (webDriver != null)
            webDriver.quit();
    }

    @Test
    public void search() {
        webDriver.findElement(By.xpath("//span[@class='menu__search-toggle']"))
                .click();
        webDriver.findElement(By.xpath("//input[@placeholder ='Поиск по книгам, преподавателям, " +
                        "студентам и страницам']"))
                .sendKeys("студенты\n");//взаимодействие с посиковой строкой
        webDriver.findElement(By.xpath("//a[@href='/search/news?q=%D1%81%D1%82%D1%83%D0%B4%D0%B5%D0%BD%D1%82%D1%8B']"))
                .click();
        webDriver.findElement(By.xpath("//a[@href='/news/178383?q=студенты']"))
                .click();
        assertTrue(webDriver.findElement(By.className("article__content"))
                .getText().contains("студенты"));//проверка поиска по ключевым словам 1/2
    }

    @Test
    public void search2() {
        webDriver.findElement(By.xpath("//span[@class='menu__search-toggle']"))
                .click();
        webDriver.findElement(By.xpath("//input[@placeholder ='Поиск по книгам, преподавателям, студентам и страницам']"))
                .sendKeys("программирование\n");//взаимодействие с посиковой строкой
        webDriver.findElement(By.xpath("//a[@href='/search/news?q=%D0%BF%D1%80%D0%BE%D0%B3%D1%80%D0%B0%D0%BC%D0%BC%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5']"))
                .click();
        webDriver.findElement(By.xpath("//a[@href='/news/177385?q=программирование']"))
                .click();
        assertTrue(webDriver.findElement(By.className("article__body"))
                .getText().contains("программирование"));//проверка поиска по ключевым словам 2/2
    }

    @Test
    public void search3() {
        webDriver.findElement(By.xpath("//span[@class='menu__search-toggle']"))
                .click();
        webDriver.findElement(By.xpath("//input[@placeholder ='Поиск по книгам, преподавателям, студентам и страницам']"))
                .sendKeys("автоматическое тестирование\n");//взаимодействие с посиковой строкой
        assertTrue(webDriver.findElement(By.className("search_no-text"))
                .getText().contains("ничего не найдено"));//проверка ошибки поиска
    }

    @Test
    public void search4() {
        webDriver.findElement(By.xpath("//span[@class='menu__search-toggle']"))
                .click();
        webDriver.findElement(By.xpath("//input[@placeholder ='Поиск по книгам, преподавателям, студентам и страницам']"))
                .sendKeys("технология\n");//взаимодействие с поисковой строкой
        webDriver.findElement(By.xpath("//a[@href='/search/news?q=%D0%BF%D1%80%D0%BE%D0%B3%D1%80%D0%B0%D0%BC%D0%BC%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5']"))
                .click();
        webDriver.findElement(By.xpath("//a[@href='/news/177385?q=технология']"))
                .click();
        assertTrue(webDriver.findElement(By.className("article__body"))
                .getText().contains("технология"));//проверка поиска по слову
    }
}
