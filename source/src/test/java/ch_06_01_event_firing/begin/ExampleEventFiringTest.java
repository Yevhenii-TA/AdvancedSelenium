package ch_06_01_event_firing.begin;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExampleEventFiringTest {

    WebDriver driver;

    @BeforeEach
    public void createDriver(){

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.get("https://eviltester.github.io/supportclasses/");

    }

    @Test
    public void loggingFindingElements() {

        final By resend = By.id("resend-select");
        final By noSuchElement = By.id("no-such-element");

        // find the resend element and log as we do so
        System.out.println("Looking For " + resend.toString());
        WebElement resendElem = driver.findElement(resend);
        Assertions.assertNotNull(resendElem);
        if (resendElem != null) {
            System.out.println("Finished looking for " + resend.toString());
        }

        // we should not find the element but log as we look
        System.out.println("Looking For " + noSuchElement.toString());
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            driver.findElement(noSuchElement);
        });

    }


    @AfterEach
    public void closeDriver(){
        driver.quit();
    }
}
