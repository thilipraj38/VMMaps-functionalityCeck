package vmmapsfunctionalitycheck;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RegistrationFormTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set up the WebDriver
        System.setProperty("webdriver.chrome.driver", "Home/Downloads/selenium jar/selenium-java-4.19.1/chrome.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://accounts.vmmaps.com/");
        driver.findElement(By.id("signupToggle")).click();
    }

    @Test
    public void testRegistrationForm() {
        // Fill out the registration form
        WebElement fullNameField = driver.findElement(By.id("signup-username"));
        fullNameField.sendKeys("John Doe");

        WebElement mobileField = driver.findElement(By.id("signup-mobile"));
        mobileField.sendKeys("1234567890");

        WebElement emailField = driver.findElement(By.id("signup-email"));
        emailField.sendKeys("john.doe@example.com");

        WebElement passwordField = driver.findElement(By.id("signup-password"));
        passwordField.sendKeys("password");

        WebElement confirmPasswordField = driver.findElement(By.id("signup-confirmPassword"));
        confirmPasswordField.sendKeys("password");

        // Check terms and conditions checkbox
        WebElement termsCheckbox = driver.findElement(By.id("terms-vms-policy"));
        termsCheckbox.click();
        
        driver.findElement(By.id("newsLetter")).click();

        // Click on the submit button
        WebElement submitButton = driver.findElement(By.id("signup-submit-text"));
        submitButton.click();

        // Check if registration was successful
        WebElement errorMessage = driver.findElement(By.id("signupFormError"));
        if(!errorMessage.isDisplayed()){
        	 // Simulate email verification process
            String verificationCode = "123456"; // Mock verification code
            WebElement verificationCodeField = driver.findElement(By.id("otp"));
            verificationCodeField.sendKeys(verificationCode);
            driver.findElement(By.id("resendOTP")).click();
            WebElement verifyButton = driver.findElement(By.id("verify-otp-text"));
            verifyButton.click();
            if(driver.findElement(By.id("snackbars")).isDisplayed()) {
            	System.out.println("You are registered successfully");
            }
            else {
            	 System.out.println("verification failed");
            }
        }
        else {
        System.out.println("Registration failed");
       
        }
       
    }


    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }

}
