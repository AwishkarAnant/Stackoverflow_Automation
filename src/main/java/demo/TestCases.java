package demo;

import java.security.Key;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    // TODO: Write Test Cases Here

     public void testCase01(){
     System.out.println("Start Test case: testCase01");
     driver.get("https://stackoverflow.com/");

     String url = driver.getCurrentUrl();
     if(url.contains("stackoverflow.")){
        System.out.println("Testcase01 : Pass");
     }
     else{
        System.out.println("Testcase01 : Fail");
     }

     System.out.println("end Test case: testCase01");
     }

     public void testCase02(){
        System.out.println("Start Test case: testCase02");
        driver.get("https://stackoverflow.com/");

        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys("Python list comprehension");
        search.sendKeys(Keys.ENTER);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        WebElement result = driver.findElement(By.xpath("//div[contains(@class,'fs-caption fc-black')]/div[1]"));
        String resultText = result.getText();

        System.out.println(resultText);
   
        
        if(resultText.contains("python list comprehension")){
           System.out.println("Testcase02 : Pass");
        }
        else{
           System.out.println("Testcase02 : Fail");
        }
   
        System.out.println("end Test case: testCase02");
        }


        public void testCase03(){
            System.out.println("Start Test case: testCase03");
            driver.get("https://stackoverflow.com/");

            try {
               Thread.sleep(6000);
            } catch (InterruptedException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }

            WebElement tagButton = driver.findElement(By.xpath("//li[@class='ps-relative'][2]/a"));
            tagButton.click();
            try {
               Thread.sleep(5000);
            } catch (InterruptedException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }

            WebElement tagSearch = driver.findElement(By.id("tagfilter"));
            tagSearch.sendKeys("javascript");
            tagSearch.sendKeys(Keys.ENTER);

            try {
               Thread.sleep(3000);
            } catch (InterruptedException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }

            List<WebElement> tags = driver.findElements(By.xpath("//div[@class='flex--item']/a"));

            for(WebElement tag : tags){
               if(tag.getText().equals("javascript")){
                  tag.click();
                  break;
               }
            }

            try {
               Thread.sleep(3000);
            } catch (InterruptedException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }


            List<WebElement> questions = driver.findElements(By.xpath("//h3[@class='s-post-summary--content-title']/a"));

            boolean flag = false;
             
            for(WebElement question : questions){
               if(question.getText().toLowerCase().contains("javascript")){
                  flag=true;
                  break;
               }
            }

            if(flag){
               System.out.println("Testcase03 : Pass");
            }
            else{
               System.out.println("Testcase03 : Fail");
            }
        }

        public void testCase04(){
            System.out.println("Start Test case: testCase04");
            driver.get("https://stackoverflow.com/");

            WebElement more = driver.findElement(By.xpath("//span[@data-text='More']"));
            more.click();

            WebElement score = driver.findElement(By.xpath("//li[@role='menuitem'][4]/a"));
            score.click();

            List<WebElement> votes = driver.findElements(By.xpath("//div[@class='s-post-summary--stats-item s-post-summary--stats-item__emphasized']/span[1]"));

            int firstVote = Integer.parseInt(votes.get(0).getText());
            int secondVote = Integer.parseInt(votes.get(1).getText());

            if(firstVote>secondVote){
               System.out.println("Testcase04 : Pass");
            }
            else{
               System.out.println("Testcase04 : Fail");
            }
       
            System.out.println("end Test case: testCase04");
        }


}
