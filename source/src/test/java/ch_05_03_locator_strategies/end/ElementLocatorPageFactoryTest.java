package ch_05_03_locator_strategies.end;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ElementLocatorPageFactoryTest {

    WebDriver driver;

    @BeforeEach
    public void createDriver(){

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        // trigger time delays with a hash e.g. #2000
        // trigger extra delay to display with an underscore #_2000
        driver.get("https://eviltester.github.io/supportclasses/#_2000");

    }

    @Test
    public void sendMessage(){

        SupportPage page = new SupportPage(driver);

        page.singleResendButton.click();

        Assertions.assertEquals("Received message: selected 1",
                page.waitForMessage());
    }


    @AfterEach
    public void closeDriver(){
        driver.quit();
    }
}
