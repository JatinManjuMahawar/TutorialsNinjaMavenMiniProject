package com.tutorialsninja.mini.TestPackage.TestClasses;

import com.tutorialsninja.mini.BasePackage.*;
import com.tutorialsninja.mini.TestPackage.TestPages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {
    static WebDriver driver = null;

    static LoginPage loginPage= null;
    static LoginSuccessPage loginSuccessPage = null;
    @BeforeMethod
    public static void beforeTestsStart() throws Exception {
        driver = Base.initializeBrowser("Chrome");
        loginPage = new LoginPage(driver);
        loginSuccessPage = new LoginSuccessPage(driver);
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test(dataProvider = "validLoginData")
    public void loginWithValidCredentials(String Email, String Password){
        loginPage.clickOnMyAccountDropdown();
        loginPage.cliclOnLoginOption();
        loginPage.sendLoginDetailsAndSubmit(Email, Password);
        boolean isLoginSuccess = loginSuccessPage.VerifySuccessfulLogin();
        Assert.assertTrue(isLoginSuccess, "true");

    }
    @Test(dataProvider = "invalidLoginData")
    public void loginWithInValidCredentials(String Email, String Password){
        loginPage.clickOnMyAccountDropdown();
        loginPage.cliclOnLoginOption();
        loginPage.sendLoginDetailsAndSubmit(Email, Password);
        boolean isLoginSuccess = loginSuccessPage.VerifySuccessfulLogin();
        Assert.assertEquals(isLoginSuccess, false);

    }
    @Test
    public void loginWithNoCredentials(){
        loginPage.clickOnMyAccountDropdown();
        loginPage.cliclOnLoginOption();
        loginPage.sendLoginDetailsAndSubmit("", "");
        boolean isLoginSuccess = loginSuccessPage.VerifySuccessfulLogin();
        Assert.assertEquals(isLoginSuccess, false);

    }
    @Test
    public void loginWithCharCredentials(){
        loginPage.clickOnMyAccountDropdown();
        loginPage.cliclOnLoginOption();
        loginPage.sendLoginDetailsAndSubmit("123", "456");
        boolean isLoginSuccess = loginSuccessPage.VerifySuccessfulLogin();
        Assert.assertEquals(isLoginSuccess, false);
    }
    @DataProvider
    public Object[][] validLoginData() throws Exception {
        Object testData[][] = Utilities.getTestDataFromTestExcelWithSheetName("ValidData");
        return testData;
    }
    @DataProvider
    public Object[][] invalidLoginData() throws Exception {
        Object testData[][] = Utilities.getTestDataFromTestExcelWithSheetName("InValidData");
        return testData;
    }

}
