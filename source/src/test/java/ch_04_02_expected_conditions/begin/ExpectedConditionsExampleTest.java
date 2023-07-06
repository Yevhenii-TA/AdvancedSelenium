package ch_04_02_expected_conditions.begin;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExpectedConditionsExampleTest {

    /*
    The ExpectedConditions class provides a lot of pre-built
    methods for synchronisation,

    provide a description of these. showing some in action.

    Look at the code to see how they work as static methods
    that return an Expected Condition

    Create an expected condition based on the patterns shown
    in ExpectedConditions class to sync on something specific.

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
    public void explicitWaitUsingExpectedConditions(){

        final WebElement resendButton = driver.findElement(By.id("resend-select"));
        resendButton.click();

        // the message is not immediately displayed but our explicit wait will handle this
        // presenceOfElementLocated
        // visibilityOfElementLocated
        // explain other conditions

        final WebElement message = new WebDriverWait(driver, 10).
                until(ExpectedConditions.
                        visibilityOfElementLocated(
                                By.cssSelector("#single-list li.message")));
        // view code and see how expected conditions work

        Assertions.assertTrue(message.getText().startsWith("Received message:"));

    }




    @Test
    public void explicitWaitUsingCustomExpectedCondition(){

        final WebElement resendButton = driver.findElement(By.id("resend-select"));
        resendButton.click();

        // TODO: ideally we should wait until the historyMessages increase in number
        new WebDriverWait(driver, 10).
                until(ExpectedConditions.
                        visibilityOfElementLocated(
                                By.cssSelector("#single-list li.message")));

        WebElement message = driver.findElement(By.cssSelector("#single-list li.message"));
        Assertions.assertTrue(message.getText().startsWith("Received message:"));
    }



    @AfterEach
    public void closeDriver(){
        driver.quit();
    }
}
