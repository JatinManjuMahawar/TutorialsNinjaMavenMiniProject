package com.tutorialsninja.mini.TestPackage.TestPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver = null;
    WebDriverWait wait = null;
    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @FindBy(xpath = "//span[text()='My Account'][@class='hidden-xs hidden-sm hidden-md']")
    private WebElement loginMyAccountDropdown;
    @FindBy(xpath = "(//ul//a)[@href='https://tutorialsninja.com/demo/index.php?route=account/login'][1]")
    private WebElement loginLoginButton;
    @FindBy(xpath = "//input[@id='input-email']")
    private WebElement loginEmailAddressTextBox;
    @FindBy(xpath = "//input[@id='input-password']")
    private WebElement loginPasswordTextBox;
    @FindBy(xpath = "//input[@class='btn btn-primary']")
    private WebElement loginLoginButtonSubmit;

    public void clickOnMyAccountDropdown(){
        wait.until(ExpectedConditions.visibilityOf(loginMyAccountDropdown));
        loginMyAccountDropdown.click();
    }
    public void cliclOnLoginOption(){
        loginLoginButton.click();
    }
    public void sendLoginDetailsAndSubmit(String Email, String Password){
        wait.until(ExpectedConditions.elementToBeClickable(loginEmailAddressTextBox));
        loginEmailAddressTextBox.sendKeys(Email);
        wait.until(ExpectedConditions.elementToBeClickable(loginPasswordTextBox));
        loginPasswordTextBox.sendKeys(Password);
        wait.until(ExpectedConditions.elementToBeClickable(loginLoginButtonSubmit));
        loginLoginButtonSubmit.click();
    }
}
