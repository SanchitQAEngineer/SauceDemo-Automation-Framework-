package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.HomePage;

public class LoginTests extends BaseTest {

    @Test(priority = 1, description = "Verify login with valid credentials")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToApp("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page did not load after valid login");
    }

    @Test(priority = 2, description = "Verify login with invalid credentials")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToApp("invalid_user", "wrong_password");

        // ✅ Check that login page is still displayed
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page should remain visible after invalid login");
    }

    @Test(priority = 3, description = "Verify login with empty credentials")
    public void testEmptyLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToApp("", "");

        // ✅ Check that login page is still displayed
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page should remain visible after empty login");
    }
}
