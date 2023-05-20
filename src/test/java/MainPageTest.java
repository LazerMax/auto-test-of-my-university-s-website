import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainPageTest {
    private WebDriver webDriver;
    private WebElement element;
    private WebElement button;
    private String title;
    private String url;
    private boolean bool = false;

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
    public void URL () {
        assertEquals("https://www.miit.ru/",webDriver.getCurrentUrl());
    }

    @Test
    public void Title () {
        assertEquals("РУТ (МИИТ)",webDriver.getTitle());
    }

    @Test
    public void PMIIT () {
        while (!bool) {
            element = webDriver.findElement(By.className("slick-track"));
            bool = element.getText()
                    .contains("Первокурсникам");
            button = webDriver.findElement(By.xpath("//html/body/div/section[1]/div/div/a[2]"));
            button.click();
        }
        //webDriver.findElement(By.partialLinkText("Подробнее")).click();
        webDriver.findElement(By.xpath("//a[@href = 'https://navigator-rut.ru/']")).click();
        assertEquals("https://navigator-rut.ru/",webDriver.getCurrentUrl());
        assertEquals("НАВИГАТОР ПЕРВОКУРСНИКА",
                webDriver.getTitle());
    }

    @Test
    public void pressCenterInfo (){
        element = webDriver.findElement(By.xpath("//*[@id=\"news\"]/div/ul/li[2]/div/div[1]/div"));
        element.click();
        assertEquals("https://rut-miit.ru/depts/8/166013",webDriver.getCurrentUrl());
        assertEquals("Студенческий Пресс-центр | Центр по социальной и молодежной политике | РУТ (МИИТ)",
                webDriver.getTitle());
    }

    @Test
    public void timetable () {
        Actions act = new Actions(webDriver);
        WebElement teaching = webDriver.findElement(By.xpath("/html/body/header/div[2]/div[2]/ul[2]/li[1]"));
        act.moveToElement(teaching).build().perform();
        act.pause(1000).build().perform();
        WebElement timetable = webDriver.findElement(By
                .xpath("/html/body/header/div[2]/div[2]/ul[2]/li[1]/div/div/div/div[1]/ul/li[1]/a"));
        act.click(timetable).build().perform();
        title = webDriver.getTitle();
        url = webDriver.getCurrentUrl();
        webDriver.get("https://www.miit.ru/");
        webDriver.findElement(By.linkText("Расписание")).click();
        assertEquals(title,webDriver.getTitle());
        assertEquals(url,webDriver.getCurrentUrl());
    }

    @Test
    public void news () {
        button = webDriver.findElement(By.linkText("Все новости"));
        button.click();
        Actions act = new Actions(webDriver);
        act.pause(1000).build().perform();
        WebElement rbk = webDriver.findElement(By
                .xpath("//*[@title = 'Названы победители турнира «Лига чемпионов» по мини-футболу']"));
        act.click(rbk).build().perform();
        title = webDriver.getTitle();
        url = webDriver.getCurrentUrl();
        webDriver.get("https://www.miit.ru/");
        webDriver.findElement(By.xpath("//*[@title = 'Названы победители турнира «Лига чемпионов» по мини-футболу | РУТ (МИИТ)']"))
                .click();
        assertEquals(title,webDriver.getTitle());
        assertEquals(url,webDriver.getCurrentUrl());
    }

    @Test
    public void goToLoginPage () {
        webDriver.findElement(By.linkText("Личный кабинет")).click();
        assertEquals("РУТ (МИИТ) | Личный кабинет",webDriver.getTitle());
        assertEquals("https://rut-miit.ru/cabinet/hello/login.jsp",webDriver.getCurrentUrl());
    }

    @Test
    public void univertoday () {
        Actions act = new Actions(webDriver);
        WebElement teaching = webDriver.findElement(By.xpath("/html/body/header/div[2]/div[2]/ul[1]/li[1]"));
        act.moveToElement(teaching).build().perform();
        act.pause(1000).build().perform();
        WebElement univertoday = webDriver.findElement(By
                .xpath("/html/body/header/div[2]/div[2]/ul[1]/li[1]/div/div/div/div[1]/ul/li[1]/a"));
        act.click(univertoday).build().perform();
        title = webDriver.getTitle();
        url = webDriver.getCurrentUrl();
        webDriver.get("https://www.miit.ru/");
        webDriver.findElement(By.linkText("Университет сегодня")).click();
        assertEquals(title,webDriver.getTitle());
        assertEquals(url,webDriver.getCurrentUrl());
    }
}