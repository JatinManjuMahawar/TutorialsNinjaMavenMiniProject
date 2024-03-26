package com.tutorialsninja.mini.TestPackage.TestPages;

import org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SelectorDocument;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    WebDriver driver = null;
    WebDriverWait wait = null;

    public RegisterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @FindBy(xpath = "//span[@class='caret']")
    private WebElement registerMyAccountDropdown;
    @FindBy(xpath = "//ul//li//a[text()='Register']")
    private WebElement registerRegisterButton;
    @FindBy(xpath = "//*[@name='firstname']")
    private WebElement registerFirstname;
    @FindBy(xpath = "//*[@name='lastname']")
    private WebElement registerLastname;
    @FindBy(xpath = "//*[@name='email']")
    private WebElement registerEmail;
    @FindBy(xpath = "//*[@name='telephone']")
    private WebElement registerTelephone;
    @FindBy(xpath = "//*[@id='input-password']")
    private WebElement registerPassword;
    @FindBy(xpath = "//*[@id='input-confirm']")
    private WebElement registerConfirmPassword;
    @FindBy(xpath = "(//input[@type='radio'])[2]")
    private WebElement registerSubscribeNewsletter;
    @FindBy(xpath = "//input[@type='checkbox'][@name='agree']")
    private WebElement registerPrivacyPolicyCheckbox;
    @FindBy(xpath = "//input[@type='submit'][@class='btn btn-primary']")
    private WebElement registerSubmitRegistrationDetailsButton;

    public void clickOnMyAccountDropdown(){
        registerMyAccountDropdown.click();
    }
    public void clickOnRegisterOption(){
        registerRegisterButton.click();
    }

    public void sendRegistrationDetails(String Email, String Password, String ConfirmPassword,String Firstname, String Lastname, Long Telephone, Boolean Subscribe){
        registerFirstname.sendKeys(Firstname);
        registerLastname.sendKeys(Lastname);
        registerEmail.sendKeys(Email);
        registerTelephone.sendKeys(Telephone.toString());
        registerPassword.sendKeys(Password);
        registerConfirmPassword.sendKeys(ConfirmPassword);
        if(Subscribe==true){
            registerSubscribeNewsletter.click();
        }
        registerPrivacyPolicyCheckbox.click();
        registerSubmitRegistrationDetailsButton.click();

    }
}
