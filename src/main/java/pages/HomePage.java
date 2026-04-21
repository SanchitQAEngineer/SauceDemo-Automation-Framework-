package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By inventoryContainer = By.id("inventory_container");
    private By cartBadge          = By.className("shopping_cart_badge");
    private By cartIcon           = By.className("shopping_cart_link");
    private By menuButton         = By.id("react-burger-menu-btn");
    private By logoutLink         = By.id("logout_sidebar_link");
    private By loginButton        = By.id("login-button");
    // ADDED: sidebar open indicator
    private By sidebarMenu        = By.className("bm-menu-wrap");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isHomePageLoaded() {
        try {
            wait.until(ExpectedConditions.urlContains("inventory"));
            return wait.until(ExpectedConditions
                    .visibilityOfElementLocated(inventoryContainer)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void addProductToCart(String productName) {
        String id = "";
        switch (productName) {
            case "Sauce Labs Backpack":
                id = "add-to-cart-sauce-labs-backpack"; break;
            case "Sauce Labs Bolt T-Shirt":
                id = "add-to-cart-sauce-labs-bolt-t-shirt"; break;
            case "Sauce Labs Bike Light":
                id = "add-to-cart-sauce-labs-bike-light"; break;
            case "Sauce Labs Fleece Jacket":
                id = "add-to-cart-sauce-labs-fleece-jacket"; break;
            case "Sauce Labs Onesie":
                id = "add-to-cart-sauce-labs-onesie"; break;
            case "Test.allTheThings() T-Shirt (Red)":
                id = "add-to-cart-test.allthethings()-t-shirt-(red)"; break;
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).click();
    }

    public void removeProductFromHome(String productName) {
        String id = "";
        switch (productName) {
            case "Sauce Labs Backpack":
                id = "remove-sauce-labs-backpack"; break;
            case "Sauce Labs Bolt T-Shirt":
                id = "remove-sauce-labs-bolt-t-shirt"; break;
            case "Sauce Labs Bike Light":
                id = "remove-sauce-labs-bike-light"; break;
            case "Sauce Labs Fleece Jacket":
                id = "remove-sauce-labs-fleece-jacket"; break;
            case "Sauce Labs Onesie":
                id = "remove-sauce-labs-onesie"; break;
            case "Test.allTheThings() T-Shirt (Red)":
                id = "remove-test.allthethings()-t-shirt-(red)"; break;
        }
        driver.findElement(By.id(id)).click();
    }

    public boolean isCartBadgeDisplayed() {
        try {
            return driver.findElement(cartBadge).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCartBadgeCount() {
        try {
            return driver.findElement(cartBadge).getText();
        } catch (Exception e) {
            return "0";
        }
    }

    public void clickCartIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
        wait.until(ExpectedConditions.urlContains("cart"));
    }

    public void logout() {
        // Step 1: Click menu button
        WebElement menu = wait.until(
                ExpectedConditions.elementToBeClickable(menuButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", menu);

        // FIXED: Wait for sidebar to fully open using aria-hidden attribute
        wait.until(ExpectedConditions.attributeToBe(
                sidebarMenu, "aria-hidden", "false"));

        // Step 2: Click logout using JS
        WebElement logout = wait.until(
                ExpectedConditions.elementToBeClickable(logoutLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logout);

        // Step 3: Wait for login page
        wait.until(ExpectedConditions.urlContains("saucedemo.com"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }
}