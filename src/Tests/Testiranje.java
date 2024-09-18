package Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class Testiranje {

    private static WebDriver driver;
    private WebElement element;



    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Set up ChromeOptions to enable headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu"); // Optional, to avoid some issues
        options.addArguments("--window-size=1920,1080"); // Set a window size if needed

        driver=new ChromeDriver(options);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Declare and initialise a fluent wait for WebDriver
        FluentWait<WebDriver> wait = new FluentWait<>(driver);

        // Specify the timeout of the wait
        wait.withTimeout(Duration.ofSeconds(5));

        // Specify polling time
        wait.pollingEvery(Duration.ofMillis(250));

        // Specify what exceptions to ignore (NoSuchElementException or any other specific exceptions)
        wait.ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);


    }

    @Test
    public void testUntitled() throws Exception {

        driver.manage().window().maximize();

        driver.get("https://www.google.com");

        element = driver.findElement(By.id("APjFqb"));
        element.sendKeys("Selenium WebDriver Interview questions");
        element.sendKeys(Keys.RETURN);
        List<WebElement> list = driver.findElements(By.className("_Rm"));
        System.out.println(list.size());

    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }
}