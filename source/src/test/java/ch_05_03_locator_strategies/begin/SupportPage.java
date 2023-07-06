package ch_05_03_locator_strategies.begin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SupportPage extends PageFactory {
    private final WebDriver driver;

    @FindBy(how = How.ID, using = "resend-select")
    public WebElement singleResendButton;

    @FindBy(how = How.CSS, using = "#message")
    public WebElement message;

    public SupportPage(WebDriver driver){

        this.driver = driver;
        initElements(driver, this);

    }


    public String waitForMessage(){

        new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOf(message));

        return message.getText();
    }
}
