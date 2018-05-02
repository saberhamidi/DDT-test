import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class ReadFileTest {

    private ExcelUtil excelUtil;

    private WebDriver driver;
    private ExtentTest testReport;


    @Before
    public void setTest() throws Exception{
        excelUtil = new ExcelUtil();
        excelUtil.setFile("/Users/Saber/Desktop/QA Training/Week9/DDTtest/src/main/resources/dataFile.xlsx",0);

        System.setProperty("webdriver.chrome.driver", "../chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void test() throws InterruptedException{

        ExtentReports report = new ExtentReports("/Users/Saber/Desktop/QA Training/Week9/DDTtest/src/main/resources/report.html", true);

        for (int i = 1; i < excelUtil.getSheet().getPhysicalNumberOfRows(); i++) {

            testReport =report.startTest("Excel Data Test "+i);
            testReport.log(LogStatus.INFO, "Opining Browser");

            Thread.sleep(3000);
            driver.get("https://callcashback.uk/");

            testReport.log(LogStatus.INFO, "Navigated to CallCashback!");

            WebElement username = driver.findElements(By.xpath("//*[@id=\"email\"]")).get(1);
            WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
            WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"login\"]/button"));

            testReport.log(LogStatus.INFO, "Login elements fetched!");

            username.sendKeys(excelUtil.getCellData(i, 0));
            password.sendKeys(excelUtil.getCellData(i,1));
            testReport.log(LogStatus.INFO, "Credentials input!");
            loginButton.click();
            testReport.log(LogStatus.INFO, "Tried to login!");
        }
        Thread.sleep(3000);
        String dashboardHeading =  driver.findElement(By.xpath("/html/body/div[4]/div/div/h2/strong")).getText();
        if (dashboardHeading.equals("CallCashBack dashboard")){
            testReport.log(LogStatus.PASS, "Logged in successfull!");
            report.endTest(testReport);
            report.flush();
        }

        else {
            testReport.log(LogStatus.FAIL, "Login Failed!");
            report.endTest(testReport);
            report.flush();
        }

        assertEquals("CallCashBack dashboard", dashboardHeading);

        Thread.sleep(3000);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
