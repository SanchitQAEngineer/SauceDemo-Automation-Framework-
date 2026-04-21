package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LogoutTests extends BaseTest {

    @Test(priority = 15, description = "Logout after login")
    public void testLogout() {

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToApp("standard_user", "secret_sauce");

        // Verify home page loaded
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageLoaded(),
                "Home page did not load after login");

        // Logout
        homePage.logout();

        // Verify login page is visible again
        Assert.assertTrue(loginPage.isLoginPageLoaded(),
                "Login page not visible after logout");
    }

    @Test(priority = 16, description = "Login, Logout and Login again")
    public void testLoginLogoutLoginAgain() {

        // First Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToApp("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageLoaded(),
                "Home page did not load after first login");

        // Logout
        homePage.logout();
        Assert.assertTrue(loginPage.isLoginPageLoaded(),
                "Login page not visible after logout");

        // Login Again
        loginPage.loginToApp("standard_user", "secret_sauce");
        Assert.assertTrue(homePage.isHomePageLoaded(),
                "Home page did not load after second login");
    }
}