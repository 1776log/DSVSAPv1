import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MapCoverageDisplay {
    private static WebElement searchFeild,newCircleButton,circleFillSelector,circleBoarderSelector;
    private static WebDriver driver;
    public MapCoverageDisplay() {
        //================================================================================//
        String[] cityStates = {"Kennewick, WA","Redding, CA","Bakersfield, CA",
                "Show Low, AZ","Salt Lake City, UT","Lewistown, MT","Jamestown, ND","Crivitz, WI"
                ,"Somerset, PA","Stoneham, CO","Maysville, MO","Georgetown, SC","Floydada, TX",
                "Pandale, TX","Houston, TX","Shreveport, LA","Holt, FL","Miami, FL","Dale, IN","Scarborough, ME"};

        System.setProperty("webdriver.chrome.driver",
                "/Users/nicholasburt/Desktop/DSVscraping/chromedriver");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.mapdevelopers.com/draw-circle-tool.php");
        searchFeild = driver.findElement(By.id("address-map-control"));
        circleBoarderSelector = driver.findElement(By.id("circle-map-control"));
        circleFillSelector = driver.findElement(By.id("border-map-control"));
        newCircleButton = driver.findElement(By.id("new-circle-map-control"));

        driver.findElement(By.id("radius-map-control")).clear();
        driver.findElement(By.id("radius-map-control")).sendKeys("300");

        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));

        //driver.close();
    }

    public MapCoverageDisplay(int i) {
    }

    public static void addNewCircle(String cityState){
        searchFeild.sendKeys(cityState);
        circleBoarderSelector.clear();
        circleBoarderSelector.sendKeys("AAAAAA");
        circleFillSelector.clear();
        circleFillSelector.sendKeys("000000");
        newCircleButton.click();
        searchFeild.clear();
    }

    public static void addNewRedCircle(String cityState){
        searchFeild.sendKeys(cityState);
        circleBoarderSelector.clear();
        circleBoarderSelector.sendKeys("AA0000");
        circleFillSelector.clear();
        circleFillSelector.sendKeys("FFB3C7");
        newCircleButton.click();
        searchFeild.clear();
    }


}
