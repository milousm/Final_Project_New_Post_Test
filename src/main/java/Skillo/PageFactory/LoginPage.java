package Skillo.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/users/login";
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "sign-in-button")
    private WebElement signInButton;
    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordField;
    @FindBy(id = "defaultLoginFormUsername")
    private WebElement userNameField;
    @FindBy(className = "h4")
    private WebElement signInFormTitle;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this); // Initialize PageFactory elements
    }

    public void clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }

    public void populatePassword(String password) {
        passwordField.sendKeys(password);
    }

    public void populateUsername(String username) {
        userNameField.sendKeys(username);
    }

    public String getSignInElementText() {
        wait.until(ExpectedConditions.visibilityOf(signInFormTitle));
        return signInFormTitle.getText();
    }

    public boolean isUrlLoaded() {
        return wait.until(ExpectedConditions.urlToBe(LoginPage.PAGE_URL));
    }

    public void login(String username, String password) {
        populateUsername(username);
        populatePassword(password);
        clickSignIn();
    }
}
