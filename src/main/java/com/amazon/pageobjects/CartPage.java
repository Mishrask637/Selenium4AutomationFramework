package com.amazon.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private By cartText = By.id("sc-active-items-header");
    private By proceedButton = By.name("proceedToRetailCheckout");
    private By deleteButton = By.cssSelector(".a-icon-small-trash");
    private By subTotal = By.id("sc-subtotal-label-activecart");

    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    public void verifyPage(){
       Assert.assertTrue(driver.findElement(cartText).isDisplayed());
    }

    public void verifyProductIsAdded(){
        Assert.assertTrue(driver.findElement(subTotal).getText().contains("1"));
    }

    public void removeProductFromCart(){
        driver.findElement(deleteButton).click();
    }

    public void verifyProductIsRemoved(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(subTotal)).isDisplayed();
        Assert.assertTrue(driver.findElement(subTotal).getText().contains("0"));
    }
}
