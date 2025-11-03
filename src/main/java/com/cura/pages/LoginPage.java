package com.cura.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    
    private WebDriver driver;
    
    @FindBy(id = "txt-username")
    private WebElement usernameField;
    
    @FindBy(id = "txt-password")
    private WebElement passwordField;
    
    @FindBy(id = "btn-login")
    private WebElement loginButton;
    
    @FindBy(className = "text-danger")
    private WebElement errorMessage;
    
    @FindBy(xpath = "//h2[contains(text(),'Login')]")
    private WebElement loginHeader;
    
    @FindBy(xpath = "//p[@class='lead text-danger']")
    private WebElement loginFailedMessage;
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public void enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }
    
    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    
    public void clickLoginButton() {
        loginButton.click();
    }
    
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
    
    public boolean isLoginHeaderDisplayed() {
        return loginHeader.isDisplayed();
    }
    
    public String getLoginHeader() {
        return loginHeader.getText();
    }
    
    public boolean isErrorMessageDisplayed() {
        try {
            return loginFailedMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getErrorMessage() {
        return loginFailedMessage.getText();
    }
    
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}