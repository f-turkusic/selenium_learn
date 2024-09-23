# Selenium 

## Parallel Testing with Selenium
Parallel testing helps to reduce execution time and efforts and results in a faster time to delivery.
TestNG is a testing framework for Java that helps to organize tests in a structured way and enhances maintainability and readability to the scripts. TestNG has made it easier for automation testers owing to its large feature set. One of which is parallel testing or parallel execution. TestNG provides an auto-defined XML file, where one can set the parallel attribute to method/tests/classes and by using the concept of multi-threading of Java, one can set the number of threads, one wants to create for parallel execution.

## Thread.sleep() in Selenium
Types of Selenium Wait Commands

- ### Implicit wait: ### 
```java
driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
```

- ### Explicit wait: ###
```java
WebDriverWait wait=new WebDriverWait(driver, 20);
wait.until(ExpectedConditions.visibilityOf(element));
```

- ### Thread.sleep(): ###
Thread.sleep() is not a Selenium wait, it is provided by Java.
Thread.sleep methods throws InterruptedException when any other thread interrupts the current thread and should be handled by the throws method or try catch block.
```java
try {
Thread.sleep(2000);
} catch (InterruptedException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
```
**Limitations of Thread.sleep**
Using Thread.sleep() frequently in an automation framework is not a good practice. If the applied sleep is of 5 secs and the web element is displayed in 2 secs only, the extra 3 secs will increase the execution time. And if you use it more often in the framework, the execution time would increase drastically.


|Aspect	| Thread.sleep()	| wait()                                            |
|---------------|---------------|---------------------------------------------------|
| Purpose |	Pauses execution for a fixed time	| Makes a thread wait until notified                |
| Locks/Monitors |	Does not release locks or monitors	| Releases locks/monitors held by the thread        |
| Must be in sync block? |	No	| Yes, must be called inside a synchronized block   |
| How it resumes	|Automatically after the specified time	| When another thread calls notify() or notifyAll() |
| Common Use Case	|Fixed time delays, pauses	| Thread coordination and synchronization           |

[Link Text](https://example.com)



