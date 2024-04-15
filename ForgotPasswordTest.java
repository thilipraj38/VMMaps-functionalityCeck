package vmmapsfunctionalitycheck;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ForgotPasswordTest {
    public static void main(String[] args) {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "Home/Downloads/selenium jar/selenium-java-4.19.1/chrome.exe");

        // Create a new instance of the Chrome driver
        WebDriver driver = new ChromeDriver();

        // Open the browser and navigate to the login page
        driver.get("https://accounts.vmmaps.com/");

        // Click on the "Forgot Password" link
        WebElement forgotPasswordLink = driver.findElement(By.linkText("Forgot Password"));
        forgotPasswordLink.click();

        // Enter the email address associated with the account
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("your_email@example.com");

        // Click on the "Submit" or "Reset Password" button
        WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
        submitButton.click();

        // Verify that the correct message is displayed confirming that the password reset link has been sent
        WebElement confirmationMessage = driver.findElement(By.className("confirmation-message"));
        if (confirmationMessage.isDisplayed()) {
            System.out.println("Password reset link sent successfully");
        } else {
            System.out.println("Failed to send password reset link");
        }

        // Close the browser
        driver.quit();
    }
}
