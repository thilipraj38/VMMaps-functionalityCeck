package vmmapsfunctionalitycheck;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTests {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "Home/Downloads/selenium jar/selenium-java-4.19.1/chrome.exe");
        driver = new ChromeDriver();
        driver.get("https://accounts.vmmaps.com/");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testValidLogin() {
        login("rtbraj38@gmail.com", "Thilip123");
        Assert.assertTrue(isLoggedIn());
    }

    @Test
    public void testInvalidUsername() {
        login("rtbaj", "Thilip123");
        Assert.assertFalse(isLoggedIn());
        Assert.assertTrue(isErrorMessageDisplayed());
    }

    @Test
    public void testInvalidPassword() {
        login("rtbraj38@gmail.com", "thilip");
        Assert.assertFalse(isLoggedIn());
        Assert.assertTrue(isErrorMessageDisplayed());
    }

    @Test
    public void testEmptyUsername() {
        login("", "Thilip123");
        Assert.assertFalse(isLoggedIn());
        Assert.assertTrue(isErrorMessageDisplayed());
    }

    @Test
    public void testEmptyPassword() {
        login("rtbraj38@gmail.com", "");
        Assert.assertFalse(isLoggedIn());
        Assert.assertTrue(isErrorMessageDisplayed());
    }

    private void login(String username, String password) {
        WebElement usernameInput = driver.findElement(By.id("login-email"));
        WebElement passwordInput = driver.findElement(By.id("login-password"));
        WebElement loginButton = driver.findElement(By.id("loginSubmit"));
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    private boolean isLoggedIn() {
    	 // Find the element containing the text indicating that the user is logged in
        WebElement loggedInElement = driver.findElement(By.xpath("//*[contains(text(),'Manage your info, privacy and security to make VMMaps work better for you')]"));
        // Check if the element is displayed
        System.out.println("Logged in Successfully");
        return loggedInElement.isDisplayed();
    }

    private boolean isErrorMessageDisplayed() {
        return driver.findElement(By.id("signinFormError")).isDisplayed();
    }
}
