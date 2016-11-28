package com.excilys.formation.computerdatabase.integration;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SeleniumTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8081/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testSelenium() throws Exception {
        driver.get(baseUrl + "computer-database/dashboard");
        driver.findElement(By.linkText("CM-5")).click();
        driver.findElement(By.xpath("//section[@id='main']/div/div/div/form/fieldset/div[3]/label")).click();
        driver.findElement(By.id("discontinued")).click();
        driver.findElement(By.id("introduced")).click();
        driver.findElement(By.linkText("8")).click();
        driver.findElement(By.id("computerName")).clear();
        driver.findElement(By.id("computerName")).sendKeys("c");
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
        driver.findElement(By.id("computerName")).clear();
        driver.findElement(By.id("computerName")).sendKeys("");
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
        driver.findElement(By.id("discontinued")).click();
        driver.findElement(By.linkText("18")).click();
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
        driver.findElement(By.id("computerName")).clear();
        driver.findElement(By.id("computerName")).sendKeys("CM-2a");
        new Select(driver.findElement(By.id("companyId"))).selectByValue("Sanyo");
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
        driver.findElement(By.id("addComputer")).click();
        driver.findElement(By.id("computerName")).clear();
        driver.findElement(By.id("computerName")).sendKeys("t");
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
        driver.findElement(By.id("computerName")).clear();
        driver.findElement(By.id("computerName")).sendKeys("ordi");
        driver.findElement(By.id("introduced")).click();
        driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/table/tbody/tr[2]/td[4]")).click();
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
        driver.findElement(By.xpath("(//button[@name='nbElements'])[3]")).click();
        driver.findElement(By.linkText("»")).click();
        driver.findElement(By.linkText("4")).click();
        driver.findElement(By.linkText("6")).click();
        driver.findElement(By.id("editComputer")).click();
        driver.findElement(By.xpath("(//input[@name='cb'])[59]")).click();
        assertTrue(
                closeAlertAndGetItsText().matches("^Are you sure you want to delete the selected computers[\\s\\S]$"));
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
