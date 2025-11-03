package com.cura.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AppointmentConfirmationPage {
    
    private WebDriver driver;
    
    @FindBy(xpath = "//h2[contains(text(),'Appointment Confirmation')]")
    private WebElement confirmationHeader;
    
    @FindBy(xpath = "//p[contains(text(),'Please be informed')]")
    private WebElement confirmationMessage;
    
    @FindBy(id = "facility")
    private WebElement facilityValue;
    
    @FindBy(id = "hospital_readmission")
    private WebElement hospitalReadmissionValue;
    
    @FindBy(id = "program")
    private WebElement programValue;
    
    @FindBy(id = "visit_date")
    private WebElement visitDateValue;
    
    @FindBy(id = "comment")
    private WebElement commentValue;
    
    @FindBy(linkText = "Go to Homepage")
    private WebElement goToHomepageLink;
    
    public AppointmentConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public boolean isConfirmationHeaderDisplayed() {
        return confirmationHeader.isDisplayed();
    }
    
    public String getConfirmationHeader() {
        return confirmationHeader.getText();
    }
    
    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }
    
    public String getFacility() {
        return facilityValue.getText();
    }
    
    public String getHospitalReadmission() {
        return hospitalReadmissionValue.getText();
    }
    
    public String getProgram() {
        return programValue.getText();
    }
    
    public String getVisitDate() {
        return visitDateValue.getText();
    }
    
    public String getComment() {
        return commentValue.getText();
    }
    
    public void clickGoToHomepage() {
        goToHomepageLink.click();
    }
    
    public void verifyAppointmentDetails(String expectedFacility, String expectedReadmission,
                                        String expectedProgram, String expectedDate, 
                                        String expectedComment) {
        assert getFacility().equals(expectedFacility) : "Facility mismatch";
        assert getHospitalReadmission().equals(expectedReadmission) : "Readmission status mismatch";
        assert getProgram().equals(expectedProgram) : "Program mismatch";
        assert getVisitDate().equals(expectedDate) : "Visit date mismatch";
        assert getComment().equals(expectedComment) : "Comment mismatch";
    }
    
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}