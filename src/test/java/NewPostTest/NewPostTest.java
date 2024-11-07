package NewPostTest;

import Skillo.PageObjects.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class NewPostTest extends TestObject {


    @DataProvider(name ="getUsers")
    public Object[][] getUsers() {
        File postPicture = new File("src/test/resources/upload/uploadPhoto.jpg");
        return new Object[][]{
                {"NewPostUser01", "Qwerty1234.", postPicture}
        };
    }


    @Test(dataProvider = "getUsers")
    public void testCreatePublicPost(String user, String password, File postPicture) throws InterruptedException {
        WebDriver driver = super.getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        Header header = new Header(driver);
        header.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.populateUsername(user);
        loginPage.populatePassword(password);
        loginPage.clickSignIn();

        header.clickNewPost();

        NewPostPage newPostPage = new NewPostPage(driver);
        Assert.assertTrue(newPostPage.isUrlLoaded(), "The New Post Page URL is incorrect!");
        String newPostText = newPostPage.getNewPostElementText(driver);
        String expectedPostText = "Post a picture to share with your awesome followers";
        Assert.assertEquals(newPostText, expectedPostText,"New post text is incorrect!");

        newPostPage.uploadPicture(postPicture);
        Assert.assertTrue(newPostPage.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(postPicture.getName(), newPostPage.getImageName(), "The image name is incorrect!");

        String caption = "Testing create new post caption";
        newPostPage.populatePostCaption(caption);
        newPostPage.clickCreatePost();

        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isUrlLoaded(), "The Profile URL is incorrect!");
        Assert.assertEquals(profilePage.getPostCount(), 1, "The number of Posts is incorrect!");
        profilePage.clickPost(0);

        PostModal postModal = new PostModal(driver);
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(postModal.getPostTitle(), caption, "The post caption is incorrect!");
        Assert.assertEquals(postModal.getPostUser(), user, "The user is incorrect!");
        postModal.deletePost();
        profilePage.waitForPostCountToBeZero();
        Assert.assertEquals(profilePage.getPostCount(), 0, "Post hasn't been deleted!");

    }

    @Test(dataProvider = "getUsers")
    public void testCreatePrivatePost(String user, String password, File postPicture) throws InterruptedException {
    WebDriver driver = super.getDriver();
    HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

    Header header = new Header(driver);
        header.clickLogin();

    LoginPage loginPage = new LoginPage(driver);
        loginPage.populateUsername(user);
        loginPage.populatePassword(password);
        loginPage.clickSignIn();

        header.clickNewPost();

    NewPostPage newPostPage = new NewPostPage(driver);
        Assert.assertTrue(newPostPage.isUrlLoaded(), "The New Post Page URL is incorrect!");
    String newPostText = newPostPage.getNewPostElementText(driver);
    String expectedPostText = "Post a picture to share with your awesome followers";
        Assert.assertEquals(newPostText, expectedPostText,"New post text is incorrect!");

        newPostPage.uploadPicture(postPicture);
        Assert.assertTrue(newPostPage.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(postPicture.getName(), newPostPage.getImageName(), "The image name is incorrect!");

    String caption = "Testing create new post caption";
        newPostPage.populatePostCaption(caption);

        newPostPage.clickPrivateSwitch();
        newPostPage.clickCreatePost();

    ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isUrlLoaded(), "The Profile URL is incorrect!");
        profilePage.clickPrivatePosts();

        Assert.assertEquals(profilePage.getPostCount(), 1, "The number of Posts is incorrect!");
        profilePage.clickPost(0);

    PostModal postModal = new PostModal(driver);
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(postModal.getPostTitle(), caption, "The post caption is incorrect!");
        Assert.assertEquals(postModal.getPostUser(), user, "The user is incorrect!");
        postModal.deletePost();
        profilePage.waitForPostCountToBeZero();
        Assert.assertEquals(profilePage.getPostCount(), 0, "Post hasn't been deleted!");

}

    @Test(dataProvider = "getUsers")
    public void testExitCreatePost(String user, String password, File postPicture) throws InterruptedException {
        WebDriver driver = super.getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        Header header = new Header(driver);
        header.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.populateUsername(user);
        loginPage.populatePassword(password);
        loginPage.clickSignIn();

        header.clickNewPost();

        NewPostPage newPostPage = new NewPostPage(driver);
        Assert.assertTrue(newPostPage.isUrlLoaded(), "The New Post Page URL is incorrect!");
        String newPostText = newPostPage.getNewPostElementText(driver);
        String expectedPostText = "Post a picture to share with your awesome followers";
        Assert.assertEquals(newPostText, expectedPostText, "New post text is incorrect!");

        newPostPage.clickExitPost();
        Assert.assertTrue(loginPage.isUrlLoaded(), "The Login URL is not correct!");

    }

    @Test(dataProvider = "getUsers")
    public void testCreatePostWithoutPhoto(String user, String password, File postPicture) throws InterruptedException {
        WebDriver driver = super.getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        Header header = new Header(driver);
        header.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.populateUsername(user);
        loginPage.populatePassword(password);
        loginPage.clickSignIn();

        header.clickNewPost();

        NewPostPage newPostPage = new NewPostPage(driver);
        Assert.assertTrue(newPostPage.isUrlLoaded(), "The New Post Page URL is incorrect!");
        String newPostText = newPostPage.getNewPostElementText(driver);
        String expectedPostText = "Post a picture to share with your awesome followers";
        Assert.assertEquals(newPostText, expectedPostText,"New post text is incorrect!");

        newPostPage.clickCreatePost();
        String expectedPhotoToastMessage = "Please upload an image!";
        Assert.assertEquals(newPostPage.getErrorMessage(), expectedPhotoToastMessage, "Photo error message is incorrect!");

        newPostPage.uploadPicture(postPicture);
        Assert.assertTrue(newPostPage.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(postPicture.getName(), newPostPage.getImageName(), "The image name is incorrect!");

        String caption = "Testing create new post caption";
        newPostPage.populatePostCaption(caption);
        newPostPage.clickCreatePost();

        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isUrlLoaded(), "The Profile URL is incorrect!");
        Assert.assertEquals(profilePage.getPostCount(), 1, "The number of Posts is incorrect!");
        profilePage.clickPost(0);

        PostModal postModal = new PostModal(driver);
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(postModal.getPostTitle(), caption, "The post caption is incorrect!");
        Assert.assertEquals(postModal.getPostUser(), user, "The user is incorrect!");
        postModal.deletePost();

    }

    @Test(dataProvider = "getUsers")
    public void testCreatePostWithoutCaption(String user, String password, File postPicture) throws InterruptedException {
        WebDriver driver = super.getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        Header header = new Header(driver);
        header.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.populateUsername(user);
        loginPage.populatePassword(password);
        loginPage.clickSignIn();

        header.clickNewPost();

        NewPostPage newPostPage = new NewPostPage(driver);
        Assert.assertTrue(newPostPage.isUrlLoaded(), "The New Post Page URL is incorrect!");
        String newPostText = newPostPage.getNewPostElementText(driver);
        String expectedPostText = "Post a picture to share with your awesome followers";
        Assert.assertEquals(newPostText, expectedPostText,"New post text is incorrect!");

        newPostPage.uploadPicture(postPicture);
        Assert.assertTrue(newPostPage.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(postPicture.getName(), newPostPage.getImageName(), "The image name is incorrect!");

        newPostPage.clickCreatePost();
        String expectedPhotoToastMessage = "Please enter caption!";
        Assert.assertEquals(newPostPage.getErrorMessage(), expectedPhotoToastMessage, "Caption error message is incorrect!");

        String caption = "Testing create new post caption";
        newPostPage.populatePostCaption(caption);
        newPostPage.clickCreatePost();

        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isUrlLoaded(), "The Profile URL is incorrect!");
        Assert.assertEquals(profilePage.getPostCount(), 1, "The number of Posts is incorrect!");
        profilePage.clickPost(0);

        PostModal postModal = new PostModal(driver);
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(postModal.getPostTitle(), caption, "The post caption is incorrect!");
        Assert.assertEquals(postModal.getPostUser(), user, "The user is incorrect!");
        postModal.deletePost();

    }

    @Test(dataProvider = "getUsers")
    public void testCreatePostFromProfile(String user, String password, File postPicture) throws InterruptedException {
        WebDriver driver = super.getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        Header header = new Header(driver);
        header.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.populateUsername(user);
        loginPage.populatePassword(password);
        loginPage.clickSignIn();
        header.clickProfile();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickNewPost();

        NewPostPage newPostPage = new NewPostPage(driver);
        Assert.assertTrue(newPostPage.isUrlLoaded(), "The New Post Page URL is incorrect!");
        String newPostText = newPostPage.getNewPostElementText(driver);
        String expectedPostText = "Post a picture to share with your awesome followers";
        Assert.assertEquals(newPostText, expectedPostText, "New post text is incorrect!");

    }


}
