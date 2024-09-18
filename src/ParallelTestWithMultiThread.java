import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class ParallelTestWithMultiThread {

    WebDriver driver;
    ChromeOptions options;
    static FluentWait<WebDriver> wait;

    @BeforeClass
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Set up ChromeOptions to enable headless mode
        options = new ChromeOptions();
        //options.addArguments("--headless");
        options.addArguments("--disable-gpu"); // Optional, to avoid some issues
        options.addArguments("--window-size=1920,1080"); // Set a window size if needed


        driver = new ChromeDriver(options);
        driver.manage().window().maximize();


        // Declare and initialise a fluent wait for WebDriver
        wait = new FluentWait<>(driver);
        // Specify the timeout of the wait
        wait.withTimeout(Duration.ofSeconds(5));
        // Specify polling time
        wait.pollingEvery(Duration.ofMillis(250));
        // Specify what exceptions to ignore (NoSuchElementException or any other specific exceptions)
        wait.ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class); // You can add multiple exceptions if needed


    }

    @Test()
    public void testOnChromeWithBrowserStackUrl()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        driver.get("https://www.browserstack.com/");
        driver.manage().window().maximize();
        System.out.println("this is the test related to chrome browserstack homepage"+ " " +Thread.currentThread().getId());

    }

    @Test()
    public void testOnChromeWithBrowserStackSignUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver.get("https://www.browserstack.com/users/sign_up");
        driver.manage().window().maximize();

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(screenshot, new File("screenshot.png"));

        WebElement userFullName = wait.until(elementToBeClickable(By.id("user_full_name")));
        userFullName.sendKeys("<name>");

        WebElement userEmailLogin = wait.until(elementToBeClickable(By.id("user_email_login")));
        userEmailLogin.sendKeys("<login email id>");

        WebElement userPassword = wait.until(elementToBeClickable(By.id("user_password")));
        userPassword.sendKeys("<password>");

        System.out.println("this is the test related to chrome browserstack login"+ " " +Thread.currentThread().getId());

    }

    @AfterClass
    public void close()
    {
        driver.quit();
    }
}