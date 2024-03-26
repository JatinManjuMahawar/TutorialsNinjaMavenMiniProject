package com.tutorialsninja.mini.TestPackage.TestPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterSuccessPage {
    WebDriver driver = null;
    public RegisterSuccessPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//div//h1[text()='Your Account Has Been Created!']")
    private WebElement registerVerifyRegistration;
    public Boolean VerifySuccessfulRegistration(){
        Boolean isRegistrationSuccessful = false;
        try{
            isRegistrationSuccessful = registerVerifyRegistration.isDisplayed();}
        catch(Exception ex){}

        return isRegistrationSuccessful;
    }
}
