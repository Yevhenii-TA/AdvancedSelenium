package ch_04_03_fluent_wait.begin;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class FluentWaitExampleTest {

    /*
        WebDriverWait is built on top of FluentWait,
        we can use this to wait on anything, not just WebDriver
    */

    WebDriver driver;

    @BeforeEach
    public void createDriver(){

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        // trigger time delays with the hash
        driver.get("https://eviltester.github.io/supportclasses/#2000");

    }


    @Test
    public void explicitWaitIsFluent(){

        final WebElement resendButton = driver.findElement(By.id("resend-select"));
        resendButton.click();

        // WebDriverWait is built on FluentWait so we have a lot of control over the wait
        // todo: customise timeout message, poll every 50 milliseconds,
        //       and ignore StaleElementReferenceException.class
        final WebElement message = new WebDriverWait(driver, 5).
                until(ExpectedConditions.
                        visibilityOfElementLocated(
                                By.cssSelector("#single-list li.message")));

        Assertions.assertTrue(message.getText().startsWith("Received message:"));

    }

    // using fluent wait to wait using WebElement rather than driver
    @Test
    public void usingFluentWait(){

        final WebElement resendButton = driver.findElement(By.id("resend-select"));
        resendButton.click();

        // I could build a custom wait around the element I want to work with
        new WebDriverWait(driver, 10).
                until(ExpectedConditions.
                        visibilityOfElementLocated(
                                By.cssSelector("#single-list li.message")));

        final WebElement message = driver.findElement(By.cssSelector("#single-list li.message"));
        Assertions.assertTrue(message.getText().startsWith("Received message:"));

    }



    @AfterEach
    public void closeDriver(){
        driver.quit();
    }


}
