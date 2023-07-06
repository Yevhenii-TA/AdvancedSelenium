package ch_03_02_custom.begin;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class CustomLocatorsTest {


    static WebDriver driver;

    @BeforeAll
    public static void createDriver(){

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.get("https://eviltester.github.io/supportclasses/");

    }

    @Test
    public void findByClassAttribute(){

        final WebElement resendSingle = driver.findElement(By.id("resend-select"));
        resendSingle.click();
        resendSingle.click();
        resendSingle.click();
        resendSingle.click();

        final WebElement resend = driver.findElement(By.id("resend-multi"));
        resend.click();
        resend.click();

        // TODO: create a custom ByAttibuteValue locator for any attribute i.e. "class", "message"
        final List<WebElement> messages = driver.findElements(By.className("message"));
        Assertions.assertEquals(6, messages.size());
    }

    @Test
    public void attributeFindBy(){

        // find the instructions via the data-locator attribute
        // https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/data-*

        WebElement instructions = driver.findElement(By.cssSelector("[data-locator='instructions']"));
        Assertions.assertEquals("Select an item from the list to show the response message.",
                instructions.getText());
    }

    @Test
    public void dataLocatorFindBy(){

        // TODO create a ByGlobalDataAttribute
        WebElement title = driver.findElement(By.cssSelector("[data-title='historytitle']"));
        Assertions.assertEquals("Message History", title.getText());

    }

    @AfterAll
    public static void closeDriver(){
        driver.quit();
    }

}
