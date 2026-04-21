package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CartPage;

public class CartTests {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open SauceDemo
        driver.get("https://www.saucedemo.com/");

        // 🔑 Login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // 🛒 Add products
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();

        // Go to cart
        driver.findElement(By.className("shopping_cart_link")).click();
    }

    @Test
    public void testCartPageLoad() {
        CartPage cartPage = new CartPage(driver);

        Assert.assertTrue(
                cartPage.isCartPageLoaded(),
                "Cart page is not loaded"
        );
    }

    @Test
    public void testCartItemsCount() {
        CartPage cartPage = new CartPage(driver);

        int itemCount = cartPage.getCartItemsCount();

        Assert.assertEquals(
                itemCount,
                2,
                "Cart item count mismatch"
        );
    }

    @Test
    public void testVerifyItemInCart() {
        CartPage cartPage = new CartPage(driver);

        Assert.assertTrue(
                cartPage.verifyItemInCart("Sauce Labs Backpack"),
                "Backpack not found in cart"
        );

        Assert.assertTrue(
                cartPage.verifyItemInCart("Sauce Labs Bike Light"),
                "Bike Light not found in cart"
        );
    }

    @Test
    public void testRemoveItemFromCart() {
        CartPage cartPage = new CartPage(driver);

        // Remove one item
        cartPage.removeItem("Sauce Labs Backpack");

        // Verify count reduced
        int updatedCount = cartPage.getCartItemsCount();

        Assert.assertEquals(
                updatedCount,
                1,
                "Item was not removed from cart"
        );
    }

    @Test
    public void testContinueShopping() {
        CartPage cartPage = new CartPage(driver);

        cartPage.clickContinueShopping();

        // Verify redirected to inventory page
        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory"),
                "Did not navigate back to inventory page"
        );
    }

    @Test
    public void testCheckoutButton() {
        CartPage cartPage = new CartPage(driver);

        cartPage.clickCheckout();

        // Verify checkout page
        Assert.assertTrue(
                driver.getCurrentUrl().contains("checkout-step-one"),
                "Checkout page not opened"
        );
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}