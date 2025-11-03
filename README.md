# CURA Healthcare Service - Selenium Automation Framework

## Project Overview
This is a comprehensive Selenium + Java automation testing framework for the CURA Healthcare Service application (https://katalon-demo-cura.herokuapp.com/).

## Prerequisites
- Java JDK 11 or higher
- Maven 3.6+
- Chrome/Firefox/Edge browser installed

## Setup Instructions

### 1. Clone or Download the Project
```bash
git clone <repository-url>
cd cura-automation
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Run Tests
```bash
mvn clean test
```

## Test Credentials
- **Username**: John Doe
- **Password**: ThisIsNotAPassword

## Features
- ✅ Page Object Model (POM)
- ✅ Multi-browser support
- ✅ Automatic driver management
- ✅ Screenshot on failure
- ✅ Extent Reports
- ✅ TestNG listeners
- ✅ Utility classes

## Test Reports
After execution, check:
- `test-output/ExtentReport.html` - Detailed HTML report
- `screenshots/` - Failure screenshots

Happy Testing! 🚀