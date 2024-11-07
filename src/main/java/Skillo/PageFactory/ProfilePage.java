package Skillo.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProfilePage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/users/";
    private final WebDriver driver;
    @FindBy(tagName = "h2")
    private WebElement username;
    @FindBy(tagName = "app-post")
    private List<WebElement> posts;
    @FindBy(className = "btn-private")
    private WebElement privatePostsLabel;
    @FindBy(xpath = "//div[@class='new-post-btn']//a")
    private WebElement newPostLink;


    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getUsername() {
        return username.getText();
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.urlContains(PAGE_URL));
    }

    public int getPostCount() {
        return posts.size();
    }

    public void clickPost(int postIndex) {
        if (postIndex >= 0 && postIndex < posts.size()) {
            posts.get(postIndex).click();
        } else {
            throw new IndexOutOfBoundsException("Invalid post index: " + postIndex);
        }
    }

    public void clickPrivatePosts(){
        privatePostsLabel.click();
    }

    public void clickNewPost(){
        newPostLink.click();
    }
}