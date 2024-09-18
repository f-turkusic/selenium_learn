package Tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class FindElementByText {
    WebDriver driver;
    WebElement element;

    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Set up ChromeOptions to enable headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu"); // Optional, to avoid some issues
        options.addArguments("--window-size=1920,1080"); // Set a window size if needed

        driver = new ChromeDriver(options);

        // Declare and initialise a fluent wait for WebDriver
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(5));
        wait.pollingEvery(Duration.ofMillis(250));
        // Specify what exceptions to ignore (NoSuchElementException or any other specific exceptions)
        wait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class); // You can add multiple exceptions if needed


    }

    @Test
    public void FindElement() {

        driver.manage().window().maximize();
        driver.get("https://www.browserstack.com/");
        By.xpath("//*[text()='Get started free']");

        // The XPath expression "//*[text()='Get started free']" is used to find an element based on its text content:
        ////* selects all elements in the DOM (Document Object Model).
        //[text()='Get started free'] filters those elements to find the one whose exact text content is "Get started free".
        //This expression will match any element (like a button, a tag, div, etc.) on the page that contains the text "Get started free".
        element = driver.findElement(By.xpath("//*[text()='Get started free']"));
        System.out.println("Element with text(): " + element.getText());


    }

    @AfterMethod
    public void tearDown() throws Exception {
        System.out.println("Tear Down !");
        driver.quit();
    }


}
