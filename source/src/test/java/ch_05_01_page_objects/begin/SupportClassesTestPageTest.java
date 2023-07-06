package ch_05_01_page_objects.begin;

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
        Test without Page Objects:

        - hard to read and understand
        - a lot to maintain when application changes
        - too many places to add synchronisation

     */
    @Test
    public void withoutPageObjects(){

        final WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement singleSelectMenu = wait.
                until(ExpectedConditions.
                        visibilityOfElementLocated(By.id("select-menu")));

        final Select select = new Select(singleSelectMenu);
        select.selectByVisibleText("Option 2");

        final By messageLocator = By.id("message");
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageLocator));

        Assertions.assertEquals("Received message: selected 2",
                driver.findElement(messageLocator).getText());
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
