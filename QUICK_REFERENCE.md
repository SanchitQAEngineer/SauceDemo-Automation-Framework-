# Quick Reference Guide - Selenium Automation Framework

## Framework Overview

This is a production-ready Selenium automation framework with:
- ✅ Page Object Model (POM) architecture
- ✅ TestNG test framework
- ✅ Data-driven testing with Apache POI
- ✅ Extent Reports for HTML reporting
- ✅ Automatic screenshot capture on failure
- ✅ WebDriverManager for automatic driver management
- ✅ Cross-browser support (Chrome & Edge)
- ✅ Maven build automation

## Project Structure

```
AutomationFramework/
├── src/test/java/
│   ├── base/               # BaseTest class
│   ├── pages/              # Page Object Model classes
│   ├── tests/              # Test classes
│   ├── listeners/          # TestNG listeners
│   └── utils/              # Utility classes
├── src/test/resources/     # Configuration files
├── test-data/              # Excel test data
├── testng.xml              # TestNG configuration
├── pom.xml                 # Maven configuration
└── README.md               # Full documentation
```

## Quick Start

### 1. First Time Setup (One-Time)
```bash
# Navigate to project
cd C:\Users\SANCHIT\IdeaProjects\AutomationFramework

# Install dependencies
mvn clean install

# Generate test data
mvn exec:java -Dexec.mainClass="utils.ExcelDataCreator"
```

### 2. Run Tests
```bash
# Run all tests
mvn clean test

# Run specific test class
mvn test -Dtest=LoginTest

# Run specific test method
mvn test -Dtest=LoginTest#testValidLogin
```

### 3. View Results
- **HTML Report:** `reports/ExtentReport_<timestamp>.html`
- **Screenshots:** `screenshots/` folder
- **Console Output:** TestNG results in terminal

## Test Classes Overview

### LoginTest
- `testValidLogin()` - Valid credentials
- `testInvalidLogin()` - Invalid credentials (data-driven)
- `testLockedUserLogin()` - Locked user scenario
- `testEmptyUsernameLogin()` - Empty username
- `testEmptyPasswordLogin()` - Empty password

### CartTest
- `testAddProductToCart()` - Add single product
- `testAddMultipleProductsToCart()` - Add multiple products
- `testViewCart()` - View cart page
- `testRemoveProductFromCartOnHomePage()` - Remove from home
- `testRemoveProductFromCartPage()` - Remove from cart
- `testRemoveAllProductsFromCart()` - Remove all items
- `testVerifySpecificItemInCart()` - Item verification
- `testCheckoutButtonVisibility()` - Checkout button

### LogoutTest
- `testLogout()` - Basic logout
- `testLogoutAfterAddingToCart()` - Logout with items
- `testLoginAfterLogout()` - Re-login after logout
- `testCartClearedAfterLogout()` - Cart behavior

## Page Objects Available

### LoginPage
- `login(username, password)` - Perform login
- `getErrorMessage()` - Get error text
- `isLoginPageLoaded()` - Check page loaded
- `isErrorMessageDisplayed()` - Check error visible

### HomePage
- `isHomePageLoaded()` - Check home page
- `addProductToCart(index)` - Add by index
- `addProductToCartByName(name)` - Add by name
- `removeProductFromCart(index)` - Remove by index
- `getCartItemCount()` - Get cart count
- `clickOnCart()` - Navigate to cart
- `logout()` - Logout user

### CartPage
- `isCartPageLoaded()` - Check cart loaded
- `getCartItemCount()` - Get item count
- `getCartItemNames()` - Get item names
- `getCartItemPrices()` - Get prices
- `removeItemByIndex(index)` - Remove item
- `removeAllItems()` - Clear cart
- `isCartEmpty()` - Check if empty
- `isItemInCart(name)` - Verify item exists

## Utility Classes

### ExcelReader
```java
ExcelReader excel = new ExcelReader("file.xlsx", "SheetName");
String value = excel.getCellData(rowNum, colNum);
excel.close();
```

### ScreenshotUtil
```java
ScreenshotUtil.takeScreenshot(driver, "testName");
ScreenshotUtil.clearScreenshotDirectory();
```

### ConfigReader
```java
String browser = ConfigReader.getBrowser();
int timeout = ConfigReader.getExplicitWait();
String url = ConfigReader.getBaseUrl();
```

## Key Features

### 1. Page Object Model
- Separates test logic from UI locators
- Reusable page methods
- Easy maintenance and scaling

### 2. Data-Driven Testing
- Read test data from Excel
- Multiple test scenarios from single test
- Easy to add new test data

### 3. Reporting
- Beautiful Extent Reports with screenshots
- Test summary dashboard
- Individual test details
- System information

### 4. Waits & Synchronization
- Explicit WebDriver waits
- Expected conditions
- Handles dynamic elements

### 5. Exception Handling
- Try-catch blocks
- Graceful error handling
- Detailed error messages

## Configuration

### Browser Configuration
Edit `testng.xml`:
```xml
<parameter name="browser" value="chrome"/>  <!-- chrome or edge -->
```

### Base URL
Edit `BaseTest.java`:
```java
protected String baseURL = "https://www.saucedemo.com/";
```

### Timeouts
Edit `src/test/resources/config.properties`:
```properties
implicit.wait=10
explicit.wait=15
page.load.timeout=30
```

## Common Commands

| Command | Purpose |
|---------|---------|
| `mvn clean` | Remove build artifacts |
| `mvn compile` | Compile source code |
| `mvn test` | Run tests |
| `mvn install` | Install dependencies |
| `mvn clean test` | Clean and run tests |
| `mvn test -Dtest=TestClass` | Run specific test class |
| `mvn test -Dtest=TestClass#method` | Run specific test |

## Adding New Tests

### 1. Create Test Class
```java
package tests;

import base.BaseTest;
import org.testng.annotations.Test;

public class NewTest extends BaseTest {
    
    @Test(description = "Test description")
    public void testNewFeature() {
        // Test implementation
    }
}
```

### 2. Create Page Object (if needed)
```java
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewPage {
    
    private By locator = By.id("element-id");
    private WebDriver driver;
    
    public NewPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void performAction() {
        driver.findElement(locator).click();
    }
}
```

### 3. Add to testng.xml
```xml
<test name="New Tests">
    <parameter name="browser" value="chrome"/>
    <classes>
        <class name="tests.NewTest"/>
    </classes>
</test>
```

## Assertions Used

```java
// String assertions
Assert.assertEquals(actual, expected, "Message");
Assert.assertTrue(condition, "Message");
Assert.assertFalse(condition, "Message");

// Boolean assertions
Assert.assertTrue(homePage.isLoggedIn());
Assert.assertFalse(cartPage.isCartEmpty());

// Null checks
Assert.assertNotNull(element);
Assert.assertNull(element);
```

## Debugging Tips

1. **Check Screenshots**
   - Look in `screenshots/` folder
   - See what page looked like at failure

2. **Review Extent Report**
   - Open HTML report in browser
   - See step-by-step execution
   - Check attached screenshots

3. **Add Print Statements**
   ```java
   System.out.println("Current URL: " + driver.getCurrentUrl());
   ```

4. **Use IDE Debugger**
   - Set breakpoints in IDE
   - Step through code
   - Inspect variables

5. **Enable Headless Mode** (for debugging)
   - Add to ChromeOptions in BaseTest
   - See page rendering differences

## File Locations

| File/Folder | Purpose |
|------------|---------|
| `src/test/java/base/` | Base test class |
| `src/test/java/pages/` | Page objects |
| `src/test/java/tests/` | Test classes |
| `src/test/java/utils/` | Utility classes |
| `src/test/java/listeners/` | Event listeners |
| `src/test/resources/` | Configuration files |
| `test-data/` | Excel test data |
| `screenshots/` | Failed test screenshots |
| `reports/` | HTML test reports |
| `pom.xml` | Maven configuration |
| `testng.xml` | TestNG configuration |

## Test Data Format (Excel)

### LoginTestData.xlsx
**Sheet: InvalidLogins**
| username | password |
|----------|----------|
| invalid_user | wrong_password |
| admin | 123456 |
| test_user | test123 |

## Maven Phases

```
validate  → validate project is correct
compile   → compile source code
test      → run unit tests
package   → package compiled code
install   → install package to local repository
deploy    → deploy package to remote repository
```

## Support Resources

- **Selenium Docs:** https://www.selenium.dev/documentation/
- **TestNG Docs:** https://testng.org/doc/documentation-main.html
- **Maven Docs:** https://maven.apache.org/guides/
- **Apache POI:** https://poi.apache.org/
- **Extent Reports:** https://www.extentreports.com/

## Tips & Tricks

1. **Run tests in parallel**
   - Edit `testng.xml`: `thread-count="4"`

2. **Skip specific tests**
   - Set `enabled = false` on @Test annotation

3. **Retry failed tests**
   - Implement IRetryAnalyzer

4. **Group tests**
   - Use `@Test(groups = "smoke")`
   - Run: `mvn test -Dgroups="smoke"`

5. **Generate reports offline**
   - Use Extent Reports standalone library

## Troubleshooting

| Issue | Solution |
|-------|----------|
| Tests not running | Check testng.xml, verify class names |
| Screenshots not captured | Check screenshots/ folder, verify paths |
| Excel not found | Run ExcelDataCreator utility |
| WebDriver not found | Check internet, WebDriverManager downloads drivers |
| Timeout errors | Increase waits in config.properties |

## Next Steps

1. ✅ Installation complete
2. ✅ Framework set up
3. 🔄 Run first test: `mvn clean test`
4. 🔄 View report: `reports/ExtentReport_<timestamp>.html`
5. 🔄 Add new tests following patterns
6. 🔄 Integrate with CI/CD pipeline

## Version Info

- **Framework Version:** 1.0-SNAPSHOT
- **Selenium:** 4.18.1
- **TestNG:** 7.10.2
- **Java:** 11+
- **Maven:** 3.6.0+
- **Created:** April 16, 2026

---

For detailed information, see:
- README.md - Complete documentation
- SETUP_GUIDE.md - Detailed setup guide
- INSTALLATION.md - Installation instructions

