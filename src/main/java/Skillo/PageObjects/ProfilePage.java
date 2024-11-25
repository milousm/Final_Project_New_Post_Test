package Skillo.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProfilePage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/users/";
    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.urlContains(ProfilePage.PAGE_URL));
    }

    public int getPostCount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName("app-post")));
        WebElement allPostStatusFilter = wait.until(ExpectedConditions.elementToBeClickable(By.className("post-filter-buttons")));
        List<WebElement> statusFilters = allPostStatusFilter.findElements(By.tagName("label"));
        try {
            statusFilters.getFirst().click();
        } catch (Exception _) {

        }
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName("app-spinner")));

        int numberOfPosts = 0;
        try {
            numberOfPosts = driver.findElements(By.tagName("app-post")).size();
        } catch (TimeoutException _) {
            return 0;
        }
        return numberOfPosts;
    }

    public void clickPost(int postIndex) {
        List<WebElement> posts = driver.findElements(By.tagName("app-post"));
        posts.get(postIndex).click();
    }

    public void clickPrivatePosts() throws InterruptedException {

        Thread.sleep(1000);
        WebElement privatePostsLabel = driver.findElement(By.className("btn-private"));
        Thread.sleep(1000);
        privatePostsLabel.click();
    }

    public void clickNewPost(){
        WebElement newPostLink = driver.findElement(By.cssSelector(".new-post-btn a"));
        newPostLink.click();
    }

}