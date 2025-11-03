package com.cura.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AppointmentPage {
    
    private WebDriver driver;
    
    @FindBy(id = "combo_facility")
    private WebElement facilityDropdown;
    
    @FindBy(id = "chk_hospotal_readmission")
    private WebElement hospitalReadmissionCheckbox;
    
    @FindBy(id = "radio_program_medicare")
    private WebElement medicareRadioButton;
    
    @FindBy(id = "radio_program_medicaid")
    private WebElement medicaidRadioButton;
    
    @FindBy(id = "radio_program_none")
    private WebElement noneRadioButton;
    
    @FindBy(id = "txt_visit_date")
    private WebElement visitDateField;
    
    @FindBy(id = "txt_comment")
    private WebElement commentField;
    
    @FindBy(id = "btn-book-appointment")
    private WebElement bookAppointmentButton;
    
    @FindBy(xpath = "//h2[contains(text(),'Make Appointment')]")
    private WebElement appointmentHeader;
    
    public AppointmentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public void selectFacility(String facility) {
        Select select = new Select(facilityDropdown);
        select.selectByVisibleText(facility);
    }
    
    public void selectFacilityByIndex(int index) {
        Select select = new Select(facilityDropdown);
        select.selectByIndex(index);
    }
    
    public void checkHospitalReadmission() {
        if (!hospitalReadmissionCheckbox.isSelected()) {
            hospitalReadmissionCheckbox.click();
        }
    }
    
    public void uncheckHospitalReadmission() {
        if (hospitalReadmissionCheckbox.isSelected()) {
            hospitalReadmissionCheckbox.click();
        }
    }
    
    public void selectHealthcareProgram(String program) {
        switch (program.toLowerCase()) {
            case "medicare":
                medicareRadioButton.click();
                break;
            case "medicaid":
                medicaidRadioButton.click();
                break;
            case "none":
                noneRadioButton.click();
                break;
            default:
                throw new IllegalArgumentException("Invalid program: " + program);
        }
    }
    
    public void enterVisitDate(String date) {
        visitDateField.clear();
        visitDateField.sendKeys(date);
    }
    
    public void enterComment(String comment) {
        commentField.clear();
        commentField.sendKeys(comment);
    }
    
    public void clickBookAppointment() {
        bookAppointmentButton.click();
    }
    
    public void fillAppointmentForm(String facility, boolean readmission, 
                                    String program, String date, String comment) {
        selectFacility(facility);
        
        if (readmission) {
            checkHospitalReadmission();
        } else {
            uncheckHospitalReadmission();
        }
        
        selectHealthcareProgram(program);
        enterVisitDate(date);
        enterComment(comment);
        clickBookAppointment();
    }
    
    public boolean isAppointmentHeaderDisplayed() {
        return appointmentHeader.isDisplayed();
    }
    
    public String getAppointmentHeader() {
        return appointmentHeader.getText();
    }
    
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}