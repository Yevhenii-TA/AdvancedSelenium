package ch_05_01_page_objects.end;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SupportClassesTestPageTest {

    WebDriver driver;

    @BeforeEach
    public void createDriver(){

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.get("https://eviltester.github.io/supportclasses/#2000");

    }

    /*
        Test using abstractions is:

        - easy to read
        - only has to change if intent of test changes
        - does not have to change if application changes - the page objects change
        - page objects used in multiple tests

     */
    @Test
    public void canSendMessage(){

        SupportClassesTestPage page = new SupportClassesTestPage(driver);

        page.selectSingleOptionMessage("Option 2");

        page.waitForMessageReceived();

        Assertions.assertEquals("Received message: selected 2",
                                page.getLastSingleMessage());

    }

    @AfterEach
    public void closeDriver(){
        driver.quit();
    }
}
