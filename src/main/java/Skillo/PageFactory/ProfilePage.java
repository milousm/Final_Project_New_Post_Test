package Skillo.PageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProfilePage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/users/";
    private final WebDriver driver;
    @FindBy(tagName = "h2")
    private WebElement username;
    @FindBy(className = "btn-private")
    private WebElement privatePostsLabel;
    @FindBy(css = ".new-post-btn a") // This assumes the link is a child of the button
    private WebElement newPostLink;
    @FindBy(tagName = "app-post")
    private List<WebElement> posts;
    @FindBy(className = "post-filter-buttons")
    private WebElement postFilterButtons;
    @FindBy(tagName = "label")
    private List<WebElement> statusFilters;


    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.urlContains(PAGE_URL));
    }

    public int getPostCount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(posts.get(0)));
        wait.until(ExpectedConditions.elementToBeClickable(postFilterButtons));
        statusFilters.get(0).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName("app-spinner")));
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