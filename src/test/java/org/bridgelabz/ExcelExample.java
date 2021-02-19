package org.bridgelabz;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.bridgelabz.bridgelabz.ReadExcelFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class ExcelExample {
    public  static WebDriver driver = null;
    @Test(dataProvider="testdata")
    public void demoClass(String username, String password) throws InterruptedException, AWTException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        driver.get("https://login.yahoo.com/?.src=&.intl=us&.lang=en-US&.do");
        driver.findElement(By.xpath("//INPUT[@id='login-username']")).sendKeys(username);
        Robot robot = new Robot();
        robot.mouseMove(1015,407);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }
    @AfterMethod
    void ProgramTermination() {
        driver.quit();
    }
    @DataProvider(name="testdata")
    public Object[][] testDataExample(){
         final String EXCEL_PATH = "C:\\Users\\ankit\\Desktop\\datadriventestingselenium\\src\\main\\resources\\testdata\\ms.xlsx";
        ReadExcelFile configuration = new ReadExcelFile(EXCEL_PATH);
        int rows = configuration.getRowCount(0);
        Object[][]signin_credentials = new Object[rows][2];

        for(int i=0;i<rows;i++)
        {
            signin_credentials[i][0] = configuration.getData(0, i, 0);
            signin_credentials[i][1] = configuration.getData(0, i, 1);

        }
        return signin_credentials;
    }
}

