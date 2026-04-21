package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CartPage;
import pages.CheckoutPage;

public class CheckoutTests {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open SauceDemo
        driver.get("https://www.saucedemo.com/");

        // 🔑 Login (basic flow)
        driver.findElement(org.openqa.selenium.By.id("user-name")).sendKeys("standard_user");
        driver.findElement(org.openqa.selenium.By.id("password")).sendKeys("secret_sauce");
        driver.findElement(org.openqa.selenium.By.id("login-button")).click();

        // 🛒 Add product to cart
        driver.findElement(org.openqa.selenium.By.id("add-to-cart-sauce-labs-backpack")).click();

        // Go to cart
        driver.findElement(org.openqa.selenium.By.className("shopping_cart_link")).click();
    }

    @Test
    public void testCheckoutFlow() {

        CartPage cartPage = new CartPage(driver);

        // ✅ Verify cart page
        Assert.assertTrue(cartPage.isCartPageLoaded(), "Cart page not loaded");

        // ✅ Proceed to checkout
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        // ✅ Fill details (IMPORTANT FIX)
        checkoutPage.fillCheckoutInfo("Sanchit", "Mathur", "202001");

        // ✅ Click continue
        checkoutPage.clickContinue();

        // ✅ Verify navigation
        Assert.assertTrue(
                checkoutPage.isOnCheckoutStepTwo(),
                "Checkout Step Two not loaded"
        );
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}