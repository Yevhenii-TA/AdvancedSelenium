package ch_05_05_page_synchronisation.begin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoadableSupportPage {
    private final WebDriverWait wait;
    private final WebDriver driver;

    @FindBy(how = How.ID, using="select-menu")
    private WebElement selectMenu;

    @FindBy(how = How.ID, using="resend-select")
    private WebElement resendSelect;

    @FindBy(how = How.ID, using="message")
    private WebElement message;

    public LoadableSupportPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public void select(final String singleOptionText) {
        final Select select = new Select(selectMenu);
        select.selectByVisibleText(singleOptionText);
    }

    public String getMessage() {
        wait.until(ExpectedConditions.visibilityOf(message));
        wait.until(ExpectedConditions.
                        textToBePresentInElement(message, "Received"));
        return message.getText();
    }

    public MessageHistoryComponent messageHistory() {
        return new MessageHistoryComponent(driver);
    }

    public void waitTillReady() {
        wait.until(ExpectedConditions.titleIs("Support Classes Example"));
        wait.until(ExpectedConditions.elementToBeClickable(resendSelect));
    }
}
