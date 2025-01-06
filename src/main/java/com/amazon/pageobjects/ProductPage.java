package com.amazon.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class ProductPage {
    private WebDriver driver;
    private By addToCartButton = By.id("add-to-cart-button");
    private By buyNowButton = By.id("buy-now-button");
    private By addToOrderSection = By.xpath("//h3[text()=' Add to your order ']");
    private By noThanksButton = By.xpath("//span[@id='attachSiNoCoverage-announce']/../input");
    private By goToCartButton = By.xpath("//a[contains(text(),'Go to Cart')]");

    public ProductPage(WebDriver driver){
        this.driver = driver;
    }

    public void verifyPage(){
        Assert.assertTrue(driver.findElement(addToCartButton).isDisplayed());
    }

    public void addProductToCart(){
        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(addToCartButton)).isDisplayed();
        driver.findElement(addToCartButton).click();
    }

    public Boolean checkAddToOrderSectionIsDisplayed(){
        try{
            new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(addToOrderSection)).isDisplayed();
        }
        catch (Exception e){
            System.out.println("Add to order Section is not displayed....");
        }
        return driver.findElements(addToOrderSection).size()>1;
    }

    public void clickNoThanks(){
        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(noThanksButton)).isDisplayed();
        driver.findElement(noThanksButton).click();
    }

    public void navigateToCart(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(goToCartButton)).isDisplayed();
        driver.findElements(goToCartButton).get(0).click();
    }
}
