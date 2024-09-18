import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestNGParallelTestingDemo {
    // Use ThreadLocal for parallel WebDriver sessions
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static String url = "https://www.google.com/";
    private static FluentWait<WebDriver> wait;
    private static ChromeOptions options;

    @BeforeMethod
    public void setUp() {
        // Set up ChromeOptions if needed
        options = new ChromeOptions();
        // Uncomment options below if needed
         options.addArguments("--headless");
         options.addArguments("--disable-gpu");
        // options.addArguments("--window-size=1920,1080");

        // Set the path for ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Initialize WebDriver for the current thread
        driver.set(new ChromeDriver(options));
        driver.get().manage().window().maximize();

        // Initialize FluentWait for the current thread's WebDriver
        wait = new FluentWait<>(driver.get());
        wait.withTimeout(Duration.ofSeconds(5));
        wait.pollingEvery(Duration.ofMillis(250));
        wait.ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    @Test
    public void Test1() throws InterruptedException {
        System.out.println("I am inside Test1 " + Thread.currentThread().getName());
        // Get the WebDriver for the current thread
        driver.get().get(url);
        Thread.sleep(3000);
    }

    @Test
    public void Test2() throws InterruptedException {
        System.out.println("I am inside Test2 " + Thread.currentThread().getName());
        // Get the WebDriver for the current thread
        driver.get().get(url);
        Thread.sleep(3000);
    }

    @Test
    public void Test3() throws InterruptedException {
        System.out.println("I am inside Test3 " + Thread.currentThread().getName());
        // Get the WebDriver for the current thread
        driver.get().get(url);
        Thread.sleep(3000);
    }

    @AfterMethod
    public void tearDown() {
        // Quit the WebDriver for the current thread and remove it from ThreadLocal
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
