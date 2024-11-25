package Skillo.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PostModal {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(className = "post-modal")
    private WebElement modalElement;
    @FindBy(css = ".post-modal-img img")
    private WebElement image;
    @FindBy(className = "post-title")
    private WebElement postTitle;
    @FindBy(className = "post-user")
    private WebElement postUser;
    @FindBy(className = "delete-ask")
    private WebElement deleteUserLabel;
    @FindBy(xpath = "//button[text()='Yes']")
    private WebElement yesButton;

    public PostModal(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this); // Initialize PageFactory elements
    }

    public boolean isImageVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(image)).isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getPostTitle() {
        wait.until(ExpectedConditions.visibilityOf(postTitle));
        return postTitle.getText();
    }

    public String getPostUser() {
        wait.until(ExpectedConditions.visibilityOf(postUser));
        return postUser.getText();
    }

    public void deletePost() {
        wait.until(ExpectedConditions.visibilityOf(deleteUserLabel)).click();
        wait.until(ExpectedConditions.elementToBeClickable(yesButton)).click();
    }
}