# Selenium Automation Framework - Setup & Usage Guide

## Quick Start Guide

### Step 1: Prerequisites Installation

1. **Install Java Development Kit (JDK) 11 or higher**
   - Download from: https://www.oracle.com/java/technologies/downloads/
   - Verify installation: `java -version`

2. **Install Maven 3.6.0 or higher**
   - Download from: https://maven.apache.org/download.cgi
   - Add Maven to PATH
   - Verify installation: `mvn -version`

3. **Install a Git client (optional)**
   - Download from: https://git-scm.com/downloads

### Step 2: Project Setup

1. **Navigate to project directory**
   ```bash
   cd C:\Users\SANCHIT\IdeaProjects\AutomationFramework
   ```

2. **Install Maven dependencies**
   ```bash
   mvn clean install
   ```

3. **Create test data Excel file**
   ```bash
   mvn exec:java -Dexec.mainClass="utils.ExcelDataCreator"
   ```

### Step 3: Run Tests

1. **Run all tests with Chrome browser**
   ```bash
   mvn clean test
   ```

2. **View test results**
   - Extent Report: `reports/ExtentReport_<timestamp>.html`
   - Screenshots: `screenshots/` folder

## Folder Structure Explanation

```
AutomationFramework/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── (Main application classes if needed)
│   │   └── resources/
│   │       └── (Main application resources)
│   │
│   └── test/
│       ├── java/
│       │   ├── base/
│       │   │   └── BaseTest.java
│       │   │       - Parent class for all tests
│       │   │       - Handles browser setup/teardown
│       │   │       - Initializes WebDriver
│       │   │
│       │   ├── pages/
│       │   │   ├── LoginPage.java
│       │   │   │   - Locators for login page elements
│       │   │   │   - Methods for login functionality
│       │   │   │
│       │   │   ├── HomePage.java
│       │   │   │   - Locators for home page elements
│       │   │   │   - Methods for product operations
│       │   │   │
│       │   │   └── CartPage.java
│       │   │       - Locators for cart page elements
│       │   │       - Methods for cart operations
│       │   │
│       │   ├── tests/
│       │   │   ├── LoginTest.java
│       │   │   │   - Login test cases
│       │   │   │   - Invalid login tests with data provider
│       │   │   │
│       │   │   ├── CartTest.java
│       │   │   │   - Cart operations tests
│       │   │   │   - Product addition/removal tests
│       │   │   │
│       │   │   └── LogoutTest.java
│       │   │       - Logout test cases
│       │   │       - Session validation tests
│       │   │
│       │   ├── listeners/
│       │   │   └── TestListener.java
│       │   │       - TestNG listener implementation
│       │   │       - Extent Reports integration
│       │   │       - Screenshot capture on failure
│       │   │
│       │   └── utils/
│       │       ├── ExcelReader.java
│       │       │   - Read test data from Excel
│       │       │   - Support for different data formats
│       │       │
│       │       ├── ScreenshotUtil.java
│       │       │   - Capture screenshots
│       │       │   - Screenshot management
│       │       │
│       │       ├── ExcelDataCreator.java
│       │       │   - Generate test data Excel files
│       │       │   - One-time utility
│       │       │
│       │       └── ConfigReader.java
│       │           - Read configuration properties
│       │           - Centralized config management
│       │
│       └── resources/
│           └── config.properties
│               - Configuration file for framework
│               - Browser, URLs, timeouts, etc.
│
├── test-data/
│   └── LoginTestData.xlsx
│       - Test data in Excel format
│       - Invalid login credentials
│       - Used by @DataProvider in tests
│
├── screenshots/
│   └── (Auto-generated screenshots on failure)
│       - Format: <testname>_<timestamp>.png
│       - Created automatically during test execution
│
├── reports/
│   └── (Auto-generated Extent Reports)
│       - Format: ExtentReport_<timestamp>.html
│       - Beautiful HTML report with details
│
├── pom.xml
│   - Maven project configuration
│   - Dependency management
│   - Build plugins configuration
│
├── testng.xml
│   - TestNG test suite configuration
│   - Browser parameters
│   - Test grouping and ordering
│   - Listener configuration
│
└── README.md
    - Complete framework documentation
```

## Page Object Model (POM) Explanation

### Why POM?
- **Maintainability**: Changes to locators are in one place
- **Reusability**: Methods can be used across multiple tests
- **Readability**: Tests are easier to understand
- **Scalability**: Easy to add new pages and tests

### Creating a New Page Object

```java
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class NewPage {
    
    // Locators as By objects
    private By elementLocator = By.id("element-id");
    
    // WebDriver and Wait instances
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Constructor
    public NewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // Methods for page operations
    public void performAction() {
        wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
        driver.findElement(elementLocator).click();
    }
}
```

## Test Writing Guidelines

### 1. Basic Test Structure
```java
@Test(description = "Test description")
public void testName() {
    // Arrange - Setup
    LoginPage loginPage = new LoginPage(driver);
    
    // Act - Execute
    loginPage.login("user", "pass");
    
    // Assert - Verify
    Assert.assertTrue(condition, "Message on failure");
}
```

### 2. Using Page Objects
```java
// Create page object instance
HomePage homePage = new HomePage(driver);

// Use page methods
homePage.addProductToCart(0);

// Verify results
Assert.assertEquals(homePage.getCartItemCount(), 1);
```

### 3. Data-Driven Tests
```java
@Test(dataProvider = "getTestData")
public void testWithData(String param1, String param2) {
    // Test implementation
}

@DataProvider(name = "getTestData")
public Object[][] getTestData() {
    // Return test data
    return new Object[][] {
        {"value1", "value2"},
        {"value3", "value4"}
    };
}
```

## Running Tests

### Maven Commands

1. **Run all tests**
   ```bash
   mvn clean test
   ```

2. **Run specific test class**
   ```bash
   mvn test -Dtest=LoginTest
   ```

3. **Run specific test method**
   ```bash
   mvn test -Dtest=LoginTest#testValidLogin
   ```

4. **Run with custom testng.xml**
   ```bash
   mvn test -DsuiteXmlFile=testng.xml
   ```

5. **Skip tests during build**
   ```bash
   mvn clean install -DskipTests
   ```

### IDE Execution (IntelliJ IDEA)

1. Right-click on test class or method
2. Select "Run <TestName>"
3. View results in the Run tab
4. Check screenshots and reports after execution

## Understanding Test Reports

### Extent Report
- **Location**: `reports/ExtentReport_<timestamp>.html`
- **Contents**:
  - Test summary (passed, failed, skipped counts)
  - Individual test details
  - Start/end time and duration
  - Screenshots on failure
  - System information
  - Test logs

### How to View Report
1. Open the HTML file in a web browser
2. View overall statistics
3. Click on individual tests for details
4. View attached screenshots
5. Check system and environment info

## Test Data Management

### Adding Test Data to Excel

1. Open `test-data/LoginTestData.xlsx`
2. Go to "InvalidLogins" sheet
3. Add new rows with username and password
4. Save file
5. Run tests - data provider will automatically use new data

### Creating New Excel Files

Use the `ExcelDataCreator` class pattern:
```java
public static void main(String[] args) throws IOException {
    // Create workbook and sheet
    XSSFWorkbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("SheetName");
    
    // Add headers and data
    Row headerRow = sheet.createRow(0);
    headerRow.createCell(0).setCellValue("Column1");
    
    // Write to file
    FileOutputStream fos = new FileOutputStream("file.xlsx");
    workbook.write(fos);
    fos.close();
}
```

## Debugging Tips

### 1. Enable Debug Logging
- Check console output for detailed logs
- Look at screenshot files for visual debugging
- Review Extent Reports for step-by-step execution

### 2. Add Wait Times (Temporary)
```java
Thread.sleep(2000); // Wait 2 seconds (not recommended for production)
```

### 3. Use WebDriver Waits (Recommended)
```java
wait.until(ExpectedConditions.presenceOfElementLocated(locator));
```

### 4. Check Screenshots
- Review screenshots in `screenshots/` folder
- Compare with expected page state
- Identify issues with elements or page layout

### 5. Enable Headless Mode (Optional)
Edit `BaseTest.java`:
```java
ChromeOptions options = new ChromeOptions();
options.addArguments("--headless");
driver = new ChromeDriver(options);
```

## Common Issues and Solutions

### Issue 1: Element Not Found
**Solution:**
- Verify locator is correct
- Add explicit wait
- Check page has loaded
- Inspect element in browser

### Issue 2: Tests Timeout
**Solution:**
- Increase wait time in config.properties
- Check network connectivity
- Verify application is responsive
- Check for JavaScript errors

### Issue 3: WebDriver Not Found
**Solution:**
- Ensure WebDriverManager is added as dependency
- Check internet connection (downloads driver)
- Verify browser is installed

### Issue 4: Excel File Not Found
**Solution:**
- Run ExcelDataCreator utility
- Verify file path in ExcelReader
- Check test-data folder exists

## Performance Optimization

### 1. Parallel Execution
Edit `testng.xml`:
```xml
<suite name="Suite" thread-count="4" parallel="tests">
```

### 2. Skip Non-Critical Tests
```java
@Test(enabled = false)
public void skippedTest() {
    // This test will be skipped
}
```

### 3. Implement Test Retry
```java
@Test(retryAnalyzer = RetryAnalyzer.class)
public void flakyTest() {
    // Retry on failure
}
```

## Best Practices

1. **Use Page Objects**: Separate test logic from UI locators
2. **Meaningful Names**: Use descriptive test and method names
3. **Assertions**: Use TestNG assertions for clear failures
4. **Wait Properly**: Use WebDriverWait instead of Thread.sleep
5. **Error Handling**: Handle exceptions gracefully
6. **Comments**: Add meaningful comments for complex logic
7. **DRY Principle**: Reuse code, avoid duplication
8. **Test Data**: Use external data sources, not hardcoded values
9. **Logging**: Add logs for debugging
10. **CI/CD**: Integrate with CI/CD pipeline

## Next Steps

1. **Explore Page Objects**: Understand how to create new pages
2. **Add More Tests**: Create tests for more scenarios
3. **Customize Configuration**: Adjust timeouts and settings
4. **Integrate with CI/CD**: Setup automatic test runs
5. **Extend Utilities**: Add more helper methods
6. **Document Your Tests**: Add descriptions and comments

## Support Resources

### Official Documentation
- Selenium: https://www.selenium.dev/documentation/
- TestNG: https://testng.org/doc/documentation-main.html
- Apache POI: https://poi.apache.org/
- Maven: https://maven.apache.org/guides/

### Community
- Stack Overflow: Search for selenium + automation
- GitHub: Browse similar projects
- Forums: Participate in testing communities

## Version History

- **v1.0** (2026-04-16): Initial release
  - Complete test framework setup
  - Three test classes with comprehensive test cases
  - Page Object Model implementation
  - Excel-based data-driven testing
  - Extent Reports integration
  - Screenshot capture on failure
  - Cross-browser support

---

**Last Updated:** April 16, 2026
**Maintained by:** Automation Framework Team

