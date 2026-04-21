package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By cartTitle = By.className("title");
    private By checkoutButton = By.id("checkout");
    private By continueShoppingBtn = By.id("continue-shopping");
    private By cartItems = By.className("cart_item");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isCartPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(cartTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int getCartItemsCount() {
        return driver.findElements(cartItems).size();
    }

    public boolean verifyItemInCart(String productName) {
        By itemLocator = By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']");
        return driver.findElements(itemLocator).size() > 0;
    }

    // Dynamic remove (no switch-case)
    public void removeItem(String productName) {
        String id = "remove-" + productName.toLowerCase().replace(" ", "-");
        wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).click();
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public void clickContinueShopping() {
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingBtn)).click();
    }
}