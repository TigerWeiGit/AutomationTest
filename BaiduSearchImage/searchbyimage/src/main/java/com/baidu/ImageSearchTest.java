package com.baidu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;

public class ImageSearchTest {
    private WebDriver driver;
    private static final String RESOURCE_PATH = "D:\\VSCodeProject\\BaiduSearchImage\\searchbyimage\\src\\main\\resources\\";
    private static final String SEARCHED_IMG = RESOURCE_PATH + "tiantan.jpg";
    private static final String CHROME_DRIVER = RESOURCE_PATH + "chromedriver-win64\\chromedriver.exe";

    @BeforeMethod
    public void setUp() {        
            System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testImageSearchAndValidation() {
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        String visitResult = bundle.getString("VISIT_RESULT");

        navigateToBaiduImageSearch();
        uploadImageForSearch(SEARCHED_IMG);
        waitForResultsToLoad(5000);
        visitSpecifiedResult(Integer.parseInt(visitResult));
        validateSearchResults();
        takeScreenshot("SearchResult_ScreenShot");
        switchToNewWindow();
        takeScreenshot("LastVisited_ScreenShot");
    }

    private void navigateToBaiduImageSearch() {
        driver.get("https://image.baidu.com");
        WebElement imageLink = driver.findElement(By.className("st_camera_off"));
        imageLink.click();
    }

    private void uploadImageForSearch(String imgPath) {
        WebElement uploadInput = driver.findElement(By.id("stfile"));
        uploadInput.sendKeys(imgPath);
    }

    private void waitForResultsToLoad(int waitTime) {
        // Wait for search results to load
        // only specify wait time for excercise
        // better waiting logic can be implemented for real project.
        try {
            Thread.sleep(waitTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void visitSpecifiedResult(int resultNumber) {
        if (resultNumber > 0) {
            String xpath = "//div[@id=\"app\"]/div/div[2]/div/div[2]/div[" + resultNumber + "]/div/a";
            WebElement resultLink = driver.findElement(By.xpath(xpath));
            resultLink.click();
            // Add waiting logic if needed
        }
    }

    private void validateSearchResults() {
        // For this excercise, simply verifying the result by keyword in the result page source
        // better validation can be implement to use OpenCV to check for image similarities for real project testing.
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains("天坛"), "Validation Failed: Image search results do not contain the related image.");
    }

    private void takeScreenshot(String screenshotPrefix) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String date = df.format(new Date());
        String screenshotFilename = screenshotPrefix + "_" + date + ".png";
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, new File(screenshotFilename));
            System.out.println(screenshotPrefix + " Screenshot saved: " + screenshotFilename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void switchToNewWindow() {
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(driver.getWindowHandle())) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }
}
