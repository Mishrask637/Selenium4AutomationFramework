package com.stepdefinition;

import com.amazon.basetest.BaseClass;
import com.amazon.basetest.DriverFactory;
import com.amazon.pageobjects.CartPage;
import com.amazon.pageobjects.HomePage;
import com.amazon.pageobjects.ProductPage;
import com.amazon.pageobjects.SearchPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public class HomePageTest {
    Properties properties;
    WebDriver driver = DriverFactory.getDriver();
    HomePage homePage;
    SearchPage searchPage = new SearchPage(driver);
    ProductPage productPage = new ProductPage(driver);
    CartPage cartPage = new CartPage(driver);

    @Given("user is on amazon home page")
    public void user_is_on_amazon_home_page() {
        properties = BaseClass.propertyReader();
        homePage = new HomePage(driver);
        driver.get(properties.get("url").toString());
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.titleContains("Amazon.com"));
        homePage.handleCaptch();
    }
    @When("user searches for an iphone")
    public void user_searches_for_an_iphone() {
        homePage.enterValueInSearchField("Iphone");
    }

    @Then("user can find the required phone in results page")
    public void user_can_find_the_required_phone_in_results_page() {
        searchPage.searchElementWithTitle("Apple iPhone SE 3rd Gen, 64GB, Midnight - Unlocked (Renewed Premium)");
    }

    @When("user clicks on product and adds to cart")
    public void user_clicks_on_product_and_adds_to_cart() {
        productPage.verifyPage();
        productPage.addProductToCart();
        productPage.checkAddToOrderSectionIsDisplayed();
        productPage.clickNoThanks();
        productPage.navigateToCart();
    }
    @Then("product is added to cart")
    public void product_is_added_to_cart() {
        cartPage.verifyPage();
        cartPage.verifyProductIsAdded();
        cartPage.removeProductFromCart();
        cartPage.verifyProductIsRemoved();
    }


}
