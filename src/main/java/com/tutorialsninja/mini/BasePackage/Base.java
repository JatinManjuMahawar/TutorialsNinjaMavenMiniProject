package com.tutorialsninja.mini.BasePackage;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.*;
import java.io.*;
import java.time.Duration;
import java.util.Properties;

public class Base {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Properties prop = new Properties();

    public static WebDriverWait getShortWait(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait;
    }
    public static WebDriverWait getMediumWait(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait;
    }
    public static WebDriverWait getLongWait(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        return wait;
    }
    public static WebDriverWait getVeryLongWait(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        return wait;
    }

    public static void waitUntilElementIsClickable(String xPATH){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPATH)));
    }
    public static void waitUntilElementIsSelectable(String xPATH){
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath(xPATH)));
    }
    public static void waitUntilAlertIsPresent(){
        wait.until(ExpectedConditions.alertIsPresent());
    }
    public static void waitUntilElementIsClickable(WebElement element){
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static Properties getPropertiesConfiguration() throws Exception {
        File propertyFile = new File("src/main/java/com/tutorialsninja/mini/ConfigurationPackage/Properties.properties");
        FileInputStream fis = new FileInputStream(propertyFile);
        prop.load(fis);
        return prop;
    }

    public static WebDriver initializeBrowser(String browserName) throws Exception {
        if(browserName.equalsIgnoreCase("Chrome")){
            driver = new ChromeDriver();
        }
        else if (browserName.equalsIgnoreCase("FireFox")){
            driver = new FirefoxDriver();
        }
        else if (browserName.equalsIgnoreCase("Safari")){
            driver = new SafariDriver();
        }
        else{
            driver = new ChromeDriver();
        }
        Properties prop = getPropertiesConfiguration();
        driver.get(prop.getProperty("BaseURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        return driver;
    }
}
