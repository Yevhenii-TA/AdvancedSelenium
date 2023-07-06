package ch_05_04_pros_cons.end;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class PageFactoryTest {

    WebDriver driver;

    @BeforeEach
    public void createDriver(){

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.get("https://eviltester.github.io/supportclasses");

    }

    @Test
    public void sendMessageWithWaitInPageObject(){

        SupportClassesPage page = new SupportClassesPage(driver);
        Assertions.assertEquals(0, page.countSingleMessageHistory());
        page.clickResendSingleButton();

        Assertions.assertEquals("Received message: selected 1",
                                page.waitForMessage());
        Assertions.assertEquals(1, page.countSingleMessageHistory());
    }

    @AfterEach
    public void closeDriver(){
        driver.quit();
    }
}
