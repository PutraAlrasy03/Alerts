package co.alerts;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Alerts {

    public static void main(String[] args) {

        WebDriver driver = null;

        try {
            // Set ChromeDriver options (optional)
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized"); // Maximize the browser window

            // Set the path to the ChromeDriver
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dev\\Downloads\\chromedriver.exe");

            // Initialize the WebDriver with ChromeDriver and options
            driver = new ChromeDriver(options);

            // Set up waits
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Longer wait for explicit waits

            // Set up implicit wait
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Implicit wait with a timeout of 10 seconds

            // Step 1: Open the Alert page
            System.out.println("Step 1: Opening Yahoo login page");
            driver.get("https://demo.automationtesting.in/Alerts.html");

            // Step 2: Click the button by css
            System.out.println("Step 2: Clicking the button");
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-danger")));
            button.click();

            // Wait for the alert to appear
            wait.until(ExpectedConditions.alertIsPresent());

            // Step 3: Verify the alert message
            Alert alert = driver.switchTo().alert();
            String alertMessage = alert.getText();
            System.out.println("Step 3: Alert Message is [" + alertMessage + "]");


            // Step 4: Accept the alert after a longer wait
            Thread.sleep(5000); // Wait for 5 seconds before accepting the alert (adjust as needed)
            alert.accept();
            System.out.println("Step 4: Accepted the alert");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                // Step 5: Close the browser
                System.out.println("Step 5: Closing the browser");
                driver.quit();
            }
        }
    }
}

