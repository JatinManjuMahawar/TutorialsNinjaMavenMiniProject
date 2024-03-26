package com.tutorialsninja.mini.TestPackage.TestClasses;

import com.tutorialsninja.mini.BasePackage.Base;
import com.tutorialsninja.mini.BasePackage.Utilities;
import com.tutorialsninja.mini.TestPackage.TestPages.RegisterPage;
import com.tutorialsninja.mini.TestPackage.TestPages.RegisterSuccessPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class RegisterTest {
    WebDriver driver = null;
    Properties prop = new Properties();
    FileInputStream fis;
    RegisterPage registerObj;
    RegisterSuccessPage registerSuccessfulObj;
    @BeforeMethod
    public void loginAndClickLogin() throws Exception {
        driver = Base.initializeBrowser("Chrome");
        registerObj = new RegisterPage(driver);
        registerSuccessfulObj = new RegisterSuccessPage(driver);
        try {
            File file = new File("src/main/java/com/tutorialsninja/mini/ConfigurationPackage/Register.properties");
            fis = new FileInputStream(file);

            prop = new Properties();
            prop.load(fis);
        } catch (Exception t) {
            throw t;
        }
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test(dataProvider = "getRegisterTestData")
    public void sendRegisterDetailsAndVerifyRegistration( String Firstname, String Lastname, String Password, String ConfirmPassword, Long Telephone){
        registerObj.clickOnMyAccountDropdown();
        registerObj.clickOnRegisterOption();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateTime = currentDateTime.format(formatter);

        String Email = "jatingupta543"+ formattedDateTime + "@gmail.com";
        registerObj.sendRegistrationDetails(Email,Password
                , ConfirmPassword, Firstname, Lastname
                , Telephone, true);

        Boolean isRegistrationSuccessful = registerSuccessfulObj.VerifySuccessfulRegistration();
        Assert.assertEquals(isRegistrationSuccessful, true);

    }

    @DataProvider
    public Object[][] getRegisterTestData() throws Exception {
        Object[][] testData = Utilities.getTestDataFromTestExcelWithSheetName("RegistrationData");
        return testData;
    }
}
