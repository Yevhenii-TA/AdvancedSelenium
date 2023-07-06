package ch_05_02_page_factory.end;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 extending PageFactory gives us the initFactory
 method, which allows us to use annotations
 */
public class SupportClassesPage extends PageFactory {
    private final WebDriver driver;

/*
     - create the web elements
     - annotate them with @FindBy
     - init the factory then use the elements
*/

    @FindBy(how = How.ID, using = "resend-select")
    public WebElement singleResendButton;

    @FindBy(how = How.CSS, using = "#message")
    public WebElement message;

    public SupportClassesPage(WebDriver driver){

        // intialize the @FindBy
        // annotated WebElements with proxies

        this.driver = driver;
        initElements(driver, this);
    }


    public String waitForMessage() {
        new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOf(
                        message)
                );
        return message.getText();
    }
}
