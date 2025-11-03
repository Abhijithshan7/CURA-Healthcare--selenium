package com.cura.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(id = "btn-make-appointment")
    private WebElement makeAppointmentButton;
    
    @FindBy(id = "menu-toggle")
    private WebElement menuToggle;
    
    @FindBy(xpath = "//a[contains(text(),'Login')]")
    private WebElement loginMenuLink;
    
    @FindBy(xpath = "//a[contains(text(),'Home')]")
    private WebElement homeMenuLink;
    
    @FindBy(xpath = "//a[contains(text(),'History')]")
    private WebElement historyMenuLink;
    
    @FindBy(xpath = "//a[contains(text(),'Profile')]")
    private WebElement profileMenuLink;
    
    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    private WebElement logoutMenuLink;
    
    @FindBy(className = "text-vertical-center")
    private WebElement pageHeader;
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    public void clickMakeAppointment() {
        makeAppointmentButton.click();
    }
    
    public void openMenu() {
        menuToggle.click();
        // Wait for menu animation to complete
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void clickLoginFromMenu() {
        openMenu();
        wait.until(ExpectedConditions.elementToBeClickable(loginMenuLink));
        loginMenuLink.click();
    }
    
    public void clickHomeFromMenu() {
        openMenu();
        wait.until(ExpectedConditions.elementToBeClickable(homeMenuLink));
        homeMenuLink.click();
    }
    
    public void clickHistoryFromMenu() {
        openMenu();
        wait.until(ExpectedConditions.elementToBeClickable(historyMenuLink));
        historyMenuLink.click();
    }
    
    public void clickProfileFromMenu() {
        openMenu();
        wait.until(ExpectedConditions.elementToBeClickable(profileMenuLink));
        profileMenuLink.click();
    }
    
    public void clickLogoutFromMenu() {
        openMenu();
        wait.until(ExpectedConditions.elementToBeClickable(logoutMenuLink));
        logoutMenuLink.click();
    }
    
    public String getPageHeader() {
        return pageHeader.getText();
    }
    
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    public boolean isMakeAppointmentButtonDisplayed() {
        return makeAppointmentButton.isDisplayed();
    }
}