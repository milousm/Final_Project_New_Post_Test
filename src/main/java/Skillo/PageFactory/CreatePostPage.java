package Skillo.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class CreatePostPage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/posts/create";
    private final WebDriver driver;
    private final WebDriverWait wait;


    @FindBy(tagName = "h3")
    private WebElement createPostFormTitle;
    @FindBy(css = "img.image-preview")
    private WebElement image;
    @FindBy(css = "input.input-lg")
    private WebElement imageTextElement;
    @FindBy(css = ".file[type='file']")
    private WebElement uploadField;
    @FindBy(name = "caption")
    private WebElement captionElement;
    @FindBy(css = "[for=customSwitch2]")
    private WebElement privacySettingLabel;
    @FindBy(id = "create-post")
    private WebElement createPostButton;
    @FindBy(className = "toast-message")
    private WebElement errorMessageElement;
    @FindBy(css = ".fas.fa-times")
    private WebElement closePostButton;

    public CreatePostPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this); // Initialize PageFactory elements
    }

    public boolean isUrlLoaded() {
        return wait.until(ExpectedConditions.urlToBe(CreatePostPage.PAGE_URL));
    }

    public String getPostElementText(WebDriver driver) {
        wait.until(ExpectedConditions.visibilityOf(createPostFormTitle));
        return createPostFormTitle.getText();
    }

    public boolean isImageVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(image)).isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getImageName() {
        return imageTextElement.getAttribute("placeholder");
    }

    public void uploadPicture(File file) {
        uploadField.sendKeys(file.getAbsolutePath());
    }

    public void populatePostCaption(String caption) {
        captionElement.sendKeys(caption);
    }

    public void clickPrivateSwitch() {
        privacySettingLabel.click();
    }

    public void clickCreatePost() {
        wait.until(ExpectedConditions.elementToBeClickable(createPostButton)).click();
    }

    public String getErrorMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(errorMessageElement)).getText();
        } catch (Exception e) {
            return "Error message not found!";
        }
    }

    public void clickExitPost() {
        wait.until(ExpectedConditions.elementToBeClickable(closePostButton)).click();
    }
}
