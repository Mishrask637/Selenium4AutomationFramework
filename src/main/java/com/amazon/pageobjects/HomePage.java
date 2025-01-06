package com.amazon.pageobjects;

import com.amazon.basetest.BaseClass;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.time.Duration;

public class HomePage extends BaseClass {
    private WebDriver driver;
    private By searchField = By.id("twotabsearchtextbox");
    private By amazonLogo = By.id("nav-logo-sprites");
    private By captchaImg = By.xpath("(//img)[1]");
    private By captchaInput = By.id("captchacharacters");
    private By continueShopping = By.xpath("//button[text()='Continue shopping']");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void verifyUrl(String url){
        Assert.assertEquals(driver.getCurrentUrl(),url);
    }

    public void enterValueInSearchField(String value){
        driver.findElement(searchField).sendKeys(value+ Keys.ENTER);
    }

    public void handleCaptch(){
        try{
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(captchaInput)).isDisplayed();
            while(new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(continueShopping)).isDisplayed()) {
                File src = driver.findElement(captchaImg).getScreenshotAs(OutputType.FILE);
                FileUtils.forceMkdir(new File(System.getProperty("user.dir") + "/screenshots/"));
                String path = System.getProperty("user.dir") + "/screenshots/captcha.png";
                FileHandler.copy(src, new File(path));
                ITesseract image = new Tesseract();
                image.setDatapath(System.getProperty("user.dir") + "/tessdata");
                String imageText = image.doOCR(new File(System.getProperty("user.dir") + "/screenshots/captcha.png"));
                System.out.println(imageText);
                driver.findElement(captchaInput).sendKeys(imageText);
                driver.findElement(continueShopping).click();
            }
        }
        catch (Exception e){
            System.out.println("Captcha is not displayed...."+e.getMessage());
        }
    }
}
