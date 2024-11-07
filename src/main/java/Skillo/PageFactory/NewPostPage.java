package Skillo.PageFactory;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class NewPostPage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/posts/create";
    private final WebDriver driver;
    @FindBy(tagName = "h3")
    private WebElement newPostFormTitle;
    @FindBy(css = "img.image-preview")
    private WebElement image;
    @FindBy(css = "input.input-lg")
    private WebElement imageTextElement;
    @FindBy(css = ".file[type='file']")
    private WebElement uploadField;
    @FindBy(name = "caption")
    private WebElement captionElement;
    @FindBy(id = "create-post")
    private WebElement createPostButton;
    @FindBy(className = "fa-times")
    private WebElement closePostButton;
    @FindBy(css = "[for=customSwitch2]")
    private WebElement privacySettingLabel;
    @FindBy(css = ".toast-message")
    private WebElement errorMessageElement;

    public NewPostPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getNewPostElementText(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(newPostFormTitle));
        return newPostFormTitle.getText();
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlToBe(NewPostPage.PAGE_URL));
    }

    public boolean isImageVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(image)).isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getImageName() {
        String imageName = imageTextElement.getAttribute("placeholder");
        return imageName;
    }

    public void uploadPicture(File file) {
        uploadField.sendKeys(file.getAbsolutePath());
    }

    public void populatePostCaption(String caption) {
        captionElement.sendKeys(caption);
    }

    public void clickPrivateSwitch(){
        privacySettingLabel.click();
    }

    public void clickCreatePost() {
        createPostButton.click();
    }

    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessageElement));
            return errorMessageElement.getText();
        } catch (Exception e) {
            return "Error message not found";
        }
    }

    public void clickExitPost(){
        closePostButton.click();
    }
}
