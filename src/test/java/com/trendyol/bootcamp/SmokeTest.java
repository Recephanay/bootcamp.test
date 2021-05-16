package com.trendyol.bootcamp;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class SmokeTest {
    WebDriver webDriver;
    @BeforeMethod
    public void startUp(){
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        webDriver.get("https://www.trendyol.com/");
        WebElement closeButton = webDriver.findElement(new By.ById("Group-38"));
        closeButton.click();
    }


    @Test
     public void shouldSearch(){

         webDriver.findElement(By.className("search-box")).sendKeys("telefon");
         webDriver.findElement(By.className("search-box")).sendKeys(Keys.ENTER);
         String resultText = webDriver.findElement(By.cssSelector(".dscrptn >h1")).getText();
         assertEquals(resultText,"telefon");

     }
     @Test
    public void shouldSearchBox2(){

        webDriver.findElement(By.className("search-box")).sendKeys("telefon");
        boolean searchRecommendations = webDriver.findElement(By.className("suggestion-title")).isDisplayed();
        assertEquals(searchRecommendations,true);


     }
     @Test
     public void shouldLogin(){
        webDriver.findElement(By.className("account-user")).click();
        webDriver.findElement(By.id("login-email")).sendKeys("hanayrecep@gmail.com");
         webDriver.findElement(By.id("login-password-input")).sendKeys("15711571203h");
         webDriver.findElement(By.className("submit")).click();
         WebDriverWait wait = new WebDriverWait(webDriver,10);
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("component-list")));
         String accounButtonText = webDriver.findElement(By.className("account-user")).getText();
         assertEquals(accounButtonText,"Hesabım");
    }


     @AfterMethod
    public void tearDown(){
         webDriver.quit();


     }
}
