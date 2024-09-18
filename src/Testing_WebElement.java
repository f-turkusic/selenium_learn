


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class Testing_WebElement {
    static WebDriver driver;
    static String url = "https://www.makemytrip.com/";
    static Object WebDriverManager;
    static FluentWait<WebDriver> wait;

    By closeButtonXpath = By.cssSelector("span.commonModal__close");
    By searchButtonXpath = By.cssSelector("button#hsw_search_button");
    //By closeButtonXpath = By.xpath("//span[@class='commonModal__close']");

    @BeforeClass
    public static void setUp() {

        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        //options.addArguments("--disable-gpu"); // Optional, to avoid some issues
        //options.addArguments("--window-size=1920,1080"); // Set a window size if needed

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

        //Launch MakeMyTrip website
        driver.get(url);
    }

    @Test
    public void search() throws InterruptedException {
        try {
            WebElement closeButton = wait.until(elementToBeClickable(closeButtonXpath));
            closeButton.click();

            System.out.println("Modal closed successfully.");
        } catch (NoSuchElementException e) {
            // Handle the case where the element is not found
            System.out.println("Close button not found. No action required.");
        } catch (Exception e) {
            // Handle any other exceptions
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // Close the browser session when done
            System.out.println("An error occurred!!!");
        }


        driver.findElement(By.cssSelector("li.menu_Hotels")).click();

        WebElement searchButton = wait.until(elementToBeClickable(searchButtonXpath));
        searchButton.click();

        List<WebElement> hotels = driver.findElements(By.xpath("//span[contains(@id, 'htl_id_seo_')]"));
        hotels.get(0).click();

        //boolean eleSelected = driver.findElement(By.xpath("xpath")).isDisplayed();
        //driver.findElement(By.cssSelector("input.react-autosuggest__input")).sendKeys("habitat");

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
