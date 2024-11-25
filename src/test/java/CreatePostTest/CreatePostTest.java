package CreatePostTest;

import Skillo.PageObjects.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class CreatePostTest extends TestObject {


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
        loginPage.login(user, password);
        header.clickNewPost();

        CreatePostPage createPostPage = new CreatePostPage(driver);
        Assert.assertTrue(createPostPage.isUrlLoaded(), "The New Post Page URL is incorrect!");
        String newPostText = createPostPage.getPostElementText(driver);
        String expectedPostText = "Post a picture to share with your awesome followers";
        Assert.assertEquals(newPostText, expectedPostText,"New post text is incorrect!");

        createPostPage.uploadPicture(postPicture);
        Assert.assertTrue(createPostPage.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(postPicture.getName(), createPostPage.getImageName(), "The image name is incorrect!");

        String caption = "Testing create new post caption";
        createPostPage.populatePostCaption(caption);
        createPostPage.clickCreatePost();

        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isUrlLoaded(), "The Profile URL is incorrect!");
        profilePage.clickPost(0);

        PostModal postModal = new PostModal(driver);
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(postModal.getPostTitle(), caption, "The post caption is incorrect!");
        Assert.assertEquals(postModal.getPostUser(), user, "The user is incorrect!");
        postModal.deletePost();
        Assert.assertEquals(profilePage.getPostCount(), 0 , "Posts haven;t been deleted!");
    }

    @Test(dataProvider = "getUsers")
    public void testCreatePrivatePost(String user, String password, File postPicture) throws InterruptedException {
    WebDriver driver = super.getDriver();
    HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

    Header header = new Header(driver);
        header.clickLogin();
    LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user, password);
        header.clickNewPost();

    CreatePostPage createPostPage = new CreatePostPage(driver);
        Assert.assertTrue(createPostPage.isUrlLoaded(), "The New Post Page URL is incorrect!");
    String newPostText = createPostPage.getPostElementText(driver);
    String expectedPostText = "Post a picture to share with your awesome followers";
        Assert.assertEquals(newPostText, expectedPostText,"New post text is incorrect!");

        createPostPage.uploadPicture(postPicture);
        Assert.assertTrue(createPostPage.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(postPicture.getName(), createPostPage.getImageName(), "The image name is incorrect!");

    String caption = "Testing create new post caption";
        createPostPage.populatePostCaption(caption);

        createPostPage.clickPrivateSwitch();
        createPostPage.clickCreatePost();

    ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isUrlLoaded(), "The Profile URL is incorrect!");
        profilePage.clickPrivatePosts();
        profilePage.clickPost(0);

    PostModal postModal = new PostModal(driver);
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(postModal.getPostTitle(), caption, "The post caption is incorrect!");
        Assert.assertEquals(postModal.getPostUser(), user, "The user is incorrect!");
        postModal.deletePost();
        Assert.assertEquals(profilePage.getPostCount(), 0 , "Posts haven;t been deleted!");

}

    @Test(dataProvider = "getUsers")
    public void testExitCreatePost(String user, String password, File postPicture) throws InterruptedException {
        WebDriver driver = super.getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        Header header = new Header(driver);
        header.clickLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user, password);
        header.clickNewPost();

        CreatePostPage createPostPage = new CreatePostPage(driver);
        Assert.assertTrue(createPostPage.isUrlLoaded(), "The New Post Page URL is incorrect!");
        String newPostText = createPostPage.getPostElementText(driver);
        String expectedPostText = "Post a picture to share with your awesome followers";
        Assert.assertEquals(newPostText, expectedPostText, "New post text is incorrect!");

        createPostPage.clickExitPost();
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
        loginPage.login(user, password);
        header.clickNewPost();

        CreatePostPage createPostPage = new CreatePostPage(driver);
        Assert.assertTrue(createPostPage.isUrlLoaded(), "The New Post Page URL is incorrect!");
        String newPostText = createPostPage.getPostElementText(driver);
        String expectedPostText = "Post a picture to share with your awesome followers";
        Assert.assertEquals(newPostText, expectedPostText,"New post text is incorrect!");

        createPostPage.clickCreatePost();
        String expectedPhotoToastMessage = "Please upload an image!";
        Assert.assertEquals(createPostPage.getErrorMessage(), expectedPhotoToastMessage, "Photo error message is incorrect!");
    }

    @Test(dataProvider = "getUsers")
    public void testCreatePostWithoutCaption(String user, String password, File postPicture) throws InterruptedException {
        WebDriver driver = super.getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        Header header = new Header(driver);
        header.clickLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user, password);
        header.clickNewPost();

        CreatePostPage createPostPage = new CreatePostPage(driver);
        Assert.assertTrue(createPostPage.isUrlLoaded(), "The New Post Page URL is incorrect!");
        String newPostText = createPostPage.getPostElementText(driver);
        String expectedPostText = "Post a picture to share with your awesome followers";
        Assert.assertEquals(newPostText, expectedPostText,"New post text is incorrect!");

        createPostPage.uploadPicture(postPicture);
        Assert.assertTrue(createPostPage.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(postPicture.getName(), createPostPage.getImageName(), "The image name is incorrect!");

        createPostPage.clickCreatePost();
        String expectedPhotoToastMessage = "Please enter caption!";
        Assert.assertEquals(createPostPage.getErrorMessage(), expectedPhotoToastMessage, "Caption error message is incorrect!");
    }

    @Test(dataProvider = "getUsers")
    public void testCreatePostFromProfile(String user, String password, File postPicture) throws InterruptedException {
        WebDriver driver = super.getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        Header header = new Header(driver);
        header.clickLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user, password);
        header.clickProfile();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickCreatePost();

        CreatePostPage createPostPage = new CreatePostPage(driver);
        Assert.assertTrue(createPostPage.isUrlLoaded(), "The New Post Page URL is incorrect!");
        String newPostText = createPostPage.getPostElementText(driver);
        String expectedPostText = "Post a picture to share with your awesome followers";
        Assert.assertEquals(newPostText, expectedPostText, "New post text is incorrect!");

    }


}
