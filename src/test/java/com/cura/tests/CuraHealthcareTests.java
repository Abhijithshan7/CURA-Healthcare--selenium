package com.cura.tests;

import com.cura.base.BaseTest;
import com.cura.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CuraHealthcareTests extends BaseTest {
    
    // Test credentials (commonly used in demo applications)
    private static final String USERNAME = "John Doe";
    private static final String PASSWORD = "ThisIsNotAPassword";
    
    @Test(priority = 1, description = "Verify home page loads successfully")
    public void testHomePageLoad() {
        HomePage homePage = new HomePage(driver);
        
        Assert.assertTrue(homePage.isMakeAppointmentButtonDisplayed(), 
            "Make Appointment button should be displayed");
        Assert.assertEquals(homePage.getPageTitle(), "CURA Healthcare Service", 
            "Page title should match");
        Assert.assertTrue(homePage.getCurrentUrl().contains("katalon-demo-cura.herokuapp.com"), 
            "URL should contain application domain");
        
        System.out.println("✓ Home page loaded successfully");
    }
    
    @Test(priority = 2, description = "Verify navigation to login page")
    public void testNavigateToLogin() {
        HomePage homePage = new HomePage(driver);
        homePage.clickMakeAppointment();
        
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginHeaderDisplayed(), 
            "Login header should be displayed");
        Assert.assertTrue(loginPage.getCurrentUrl().contains("login"), 
            "URL should contain 'login'");
        
        System.out.println("✓ Successfully navigated to login page");
    }
    
    @Test(priority = 3, description = "Verify login with valid credentials")
    public void testValidLogin() {
        HomePage homePage = new HomePage(driver);
        homePage.clickMakeAppointment();
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USERNAME, PASSWORD);
        
        AppointmentPage appointmentPage = new AppointmentPage(driver);
        Assert.assertTrue(appointmentPage.isAppointmentHeaderDisplayed(), 
            "Appointment page header should be displayed after successful login");
        Assert.assertTrue(appointmentPage.getCurrentUrl().contains("appointment"), 
            "Should navigate to appointment page");
        
        System.out.println("✓ Login successful with valid credentials");
    }
    
    @Test(priority = 4, description = "Verify login with invalid credentials")
    public void testInvalidLogin() {
        HomePage homePage = new HomePage(driver);
        homePage.clickMakeAppointment();
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("InvalidUser", "InvalidPass");
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            "Error message should be displayed for invalid credentials");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Login failed"), 
            "Error message should indicate login failure");
        
        System.out.println("✓ Invalid login handled correctly with error message");
    }
    
    @Test(priority = 5, description = "Verify booking appointment with all details")
    public void testBookAppointmentWithAllDetails() {
        // Login first
        HomePage homePage = new HomePage(driver);
        homePage.clickMakeAppointment();
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USERNAME, PASSWORD);
        
        // Fill appointment form
        AppointmentPage appointmentPage = new AppointmentPage(driver);
        String facility = "Hongkong CURA Healthcare Center";
        String program = "Medicare";
        String date = "15/12/2025";
        String comment = "This is an automated test appointment";
        
        appointmentPage.fillAppointmentForm(facility, true, program, date, comment);
        
        // Verify confirmation
        AppointmentConfirmationPage confirmationPage = new AppointmentConfirmationPage(driver);
        Assert.assertTrue(confirmationPage.isConfirmationHeaderDisplayed(), 
            "Confirmation header should be displayed");
        Assert.assertEquals(confirmationPage.getFacility(), facility, 
            "Facility should match");
        Assert.assertEquals(confirmationPage.getProgram(), program, 
            "Program should match");
        Assert.assertEquals(confirmationPage.getVisitDate(), date, 
            "Visit date should match");
        Assert.assertEquals(confirmationPage.getComment(), comment, 
            "Comment should match");
        Assert.assertEquals(confirmationPage.getHospitalReadmission(), "Yes", 
            "Hospital readmission should be Yes");
        
        System.out.println("✓ Appointment booked successfully with all details");
    }
    
    @Test(priority = 6, description = "Verify booking appointment without readmission")
    public void testBookAppointmentWithoutReadmission() {
        // Login
        HomePage homePage = new HomePage(driver);
        homePage.clickMakeAppointment();
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USERNAME, PASSWORD);
        
        // Fill appointment form
        AppointmentPage appointmentPage = new AppointmentPage(driver);
        appointmentPage.selectFacility("Tokyo CURA Healthcare Center");
        appointmentPage.uncheckHospitalReadmission();
        appointmentPage.selectHealthcareProgram("Medicaid");
        appointmentPage.enterVisitDate("20/12/2025");
        appointmentPage.enterComment("Follow-up appointment");
        appointmentPage.clickBookAppointment();
        
        // Verify confirmation
        AppointmentConfirmationPage confirmationPage = new AppointmentConfirmationPage(driver);
        Assert.assertEquals(confirmationPage.getHospitalReadmission(), "No", 
            "Hospital readmission should be No");
        Assert.assertEquals(confirmationPage.getProgram(), "Medicaid", 
            "Program should be Medicaid");
        
        System.out.println("✓ Appointment booked without readmission");
    }
    
    @Test(priority = 7, description = "Verify booking appointment with None healthcare program")
    public void testBookAppointmentWithNoneProgram() {
        // Login
        HomePage homePage = new HomePage(driver);
        homePage.clickMakeAppointment();
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USERNAME, PASSWORD);
        
        // Fill appointment form
        AppointmentPage appointmentPage = new AppointmentPage(driver);
        appointmentPage.selectFacilityByIndex(2); // Seoul
        appointmentPage.selectHealthcareProgram("None");
        appointmentPage.enterVisitDate("25/12/2025");
        appointmentPage.enterComment("Regular checkup");
        appointmentPage.clickBookAppointment();
        
        // Verify confirmation
        AppointmentConfirmationPage confirmationPage = new AppointmentConfirmationPage(driver);
        Assert.assertEquals(confirmationPage.getProgram(), "None", 
            "Program should be None");
        
        System.out.println("✓ Appointment booked with None program");
    }
    
    @Test(priority = 8, description = "Verify logout functionality")
    public void testLogout() {
        // Login first
        HomePage homePage = new HomePage(driver);
        homePage.clickMakeAppointment();
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USERNAME, PASSWORD);
        
        // Verify we're on appointment page
        AppointmentPage appointmentPage = new AppointmentPage(driver);
        Assert.assertTrue(appointmentPage.isAppointmentHeaderDisplayed(), 
            "Should be on appointment page");
        
        // Logout
        HomePage homePageAfterLogin = new HomePage(driver);
        homePageAfterLogin.clickLogoutFromMenu();
        
        // Verify we're back on home page
        Assert.assertTrue(homePage.isMakeAppointmentButtonDisplayed(), 
            "Should be back on home page after logout");
        
        System.out.println("✓ Logout successful");
    }
    
    @Test(priority = 9, description = "Verify menu navigation")
    public void testMenuNavigation() {
        HomePage homePage = new HomePage(driver);
        
        // Test menu toggle
        homePage.openMenu();
        
        // Click login from menu
        homePage.clickLoginFromMenu();
        
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginHeaderDisplayed(), 
            "Should navigate to login page from menu");
        
        System.out.println("✓ Menu navigation working correctly");
    }
    
    @Test(priority = 10, description = "End to end appointment booking flow")
    public void testEndToEndAppointmentFlow() {
        // Step 1: Navigate to home page
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isMakeAppointmentButtonDisplayed(), 
            "Home page should load");
        System.out.println("Step 1: Home page loaded");
        
        // Step 2: Click make appointment
        homePage.clickMakeAppointment();
        System.out.println("Step 2: Clicked Make Appointment");
        
        // Step 3: Login
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginHeaderDisplayed(), 
            "Login page should display");
        loginPage.login(USERNAME, PASSWORD);
        System.out.println("Step 3: Logged in successfully");
        
        // Step 4: Fill appointment details
        AppointmentPage appointmentPage = new AppointmentPage(driver);
        Assert.assertTrue(appointmentPage.isAppointmentHeaderDisplayed(), 
            "Appointment page should display");
        
        String testFacility = "Hongkong CURA Healthcare Center";
        String testProgram = "Medicare";
        String testDate = "30/11/2025";
        String testComment = "End to end test - automated booking";
        
        appointmentPage.fillAppointmentForm(testFacility, true, testProgram, testDate, testComment);
        System.out.println("Step 4: Filled appointment form");
        
        // Step 5: Verify confirmation
        AppointmentConfirmationPage confirmationPage = new AppointmentConfirmationPage(driver);
        Assert.assertTrue(confirmationPage.isConfirmationHeaderDisplayed(), 
            "Confirmation page should display");
        
        confirmationPage.verifyAppointmentDetails(
            testFacility, "Yes", testProgram, testDate, testComment);
        System.out.println("Step 5: Appointment confirmed");
        
        // Step 6: Go back to homepage
        confirmationPage.clickGoToHomepage();
        
        HomePage finalHomePage = new HomePage(driver);
        Assert.assertTrue(finalHomePage.getCurrentUrl().contains("katalon-demo-cura.herokuapp.com"), 
            "Should be back on home page");
        System.out.println("Step 6: Returned to home page");
        
        System.out.println("✓ ✓ ✓ END TO END TEST PASSED ✓ ✓ ✓");
    }
}