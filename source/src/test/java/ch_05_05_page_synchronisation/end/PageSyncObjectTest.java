package ch_05_05_page_synchronisation.end;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageSyncObjectTest {

    WebDriver driver;

    @BeforeEach
    public void createDriver(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }

    @Test
    public void canSeeMessageInHistory(){

        // to show a component on the page
        LoadableSupportPage page = new LoadableSupportPage(driver);

        // we should really wait for page to load prior to doing anything,
        // to make sure it is valid to work with
        page.get();

        page.select("Option 2");
        Assertions.assertEquals("Received message: selected 2",
                                page.getMessage());
        MessageHistoryComponent history = page.messageHistory();

        // wait for the history component to be ready
        history.waitTillReady();

        Assertions.assertEquals(1, history.countSingleHistoryMessages());
        Assertions.assertEquals("Received message: selected 2",
                                    history.getSingleHistoryMessage(0));
    }

    @AfterEach
    public void closeDriver(){
        driver.quit();
    }
}
