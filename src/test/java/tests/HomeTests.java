package tests;

import base.BaseTest;
import pages.LoginPage;
import pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTests extends BaseTest {

    private void performLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToApp("standard_user", "secret_sauce");
    }

    @Test(priority = 6, description = "Add single product to cart")
    public void testAddSingleProduct() {
        performLogin();
        HomePage homePage = new HomePage(driver);

        homePage.addProductToCart("Sauce Labs Backpack");

        Assert.assertTrue(homePage.isCartBadgeDisplayed());
        Assert.assertEquals(homePage.getCartBadgeCount(), "1");
    }

    @Test(priority = 7, description = "Add multiple products to cart")
    public void testAddMultipleProducts() {
        performLogin();
        HomePage homePage = new HomePage(driver);

        homePage.addProductToCart("Sauce Labs Backpack");
        homePage.addProductToCart("Sauce Labs Bike Light");
        homePage.addProductToCart("Sauce Labs Bolt T-Shirt");

        Assert.assertEquals(homePage.getCartBadgeCount(), "3");
    }

    @Test(priority = 8, description = "Remove product from cart on Home page")
    public void testRemoveProductFromHome() {
        performLogin();
        HomePage homePage = new HomePage(driver);

        homePage.addProductToCart("Sauce Labs Backpack");
        Assert.assertEquals(homePage.getCartBadgeCount(), "1");

        homePage.removeProductFromHome("Sauce Labs Backpack");
        Assert.assertFalse(homePage.isCartBadgeDisplayed());
    }

    @Test(priority = 9, description = "Validate Cart Badge Count Accuracy")
    public void testCartBadgeAccuracy() {
        performLogin();
        HomePage homePage = new HomePage(driver);

        homePage.addProductToCart("Sauce Labs Onesie");
        Assert.assertEquals(homePage.getCartBadgeCount(), "1");

        homePage.addProductToCart("Sauce Labs Fleece Jacket");
        Assert.assertEquals(homePage.getCartBadgeCount(), "2");
    }
}