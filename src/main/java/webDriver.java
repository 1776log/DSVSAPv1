import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class webDriver {
    private static WebDriver driver;
    private static WebDriverWait w;
    private static File source;
    private static ChromeOptions options;
    private static MapCoverageDisplay displayMap;


    public webDriver() {
        System.setProperty("webdriver.chrome.driver",
                "/Users/nicholasburt/Desktop/DSVscraping/chromedriver");
        SetupChromeDriver();
    }
    /******************************************************************************/
    public webDriver(int i) {
        SetupChromeDriver();
        System.out.println(driver.findElement(By.id("loadform:table:0:j_idt483")));

    }
    /******************************************************************************/
    public static void main(String[] args) {
        System.out.println("webDriver main");
    }
    /******************************************************************************/
    public static void killDriver(){
        driver.close();
    }
     /******************************************************************************/
    public static ArrayList<File> getSources(String[] cityStates) {
        ArrayList<File> queries = new ArrayList<>();
        for (String cityState:
             cityStates) {
            queries.add(queryResultsHTML(cityState));
        }
        return(queries);
    }
    /******************************************************************************/
    public static File getSource(String cityState) {

        return(queryResultsHTML(cityState));
    }
    /******************************************************************************/
    private static void SetupChromeDriver() {

        options = new ChromeOptions();
        options.addArguments("--headless");
        //options.addArguments(f'user-agent={user_agent}');
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--disable-extensions");
        options.addArguments("--proxy-server='direct://'");
        options.addArguments("--proxy-bypass-list=*");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox'");
        options.addArguments("--incognito");
        options.addArguments("--w3c");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        w = new WebDriverWait(driver,  Duration.ofSeconds(5));

    }
    /******************************************************************************/
    public static File queryResultsHTML(String cityAndState){
        driver.get("https://mytms.us.dsv.com/web/pages/carrier/findload");
//      identify element
        w.until(ExpectedConditions.presenceOfElementLocated(By.
                xpath("//*[@id=\"loadform:button\"]")));
        driver.findElement(By.id("loadform:originInput")).
                sendKeys(cityAndState);
        Select radius = new Select(driver.findElement(By.id("loadform:radius")));
        radius.selectByIndex(2);
        driver.findElement(By.id("loadform:button")).click();
        WebDriverWait w2 = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            w.until(ExpectedConditions.presenceOfElementLocated(By.
                    xpath("/html/body/table/tbody/tr/td[2]/table/tbody/tr[4]/td/" +
                            "table/tbody/tr/td[4]/form/table[2]/thead/tr[1]/th")));
        }catch (Exception e){

        }

        source = new File(driver.findElement(By.id("loadform:originInput")).
                getAttribute("value"));

        try {
            FileWriter myWriter = new FileWriter(source);
            myWriter.write(driver.getPageSource());
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //displayMap.addNewCircle(cityAndState);
        return (source);
    }
}
