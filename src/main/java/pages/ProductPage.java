package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;

    By backpackAddBtn = By.id("add-to-cart-sauce-labs-backpack");
    By bikeLightAddBtn = By.id("add-to-cart-sauce-labs-bike-light");
    By cartBadge = By.className("shopping_cart_badge");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addBackpackToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(backpackAddBtn)).click();
    }

    public void addBikeLightToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(bikeLightAddBtn)).click();
    }

    public String getCartCount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).getText();
    }
}