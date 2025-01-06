package com.amazon.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class SearchPage {
    private WebDriver driver;
    private By resultsText = By.xpath("//h2[text()='Results']");
    private By noOfResults = By.xpath("//div[@data-component-type='s-search-result']");
    private By totalAvailablePages = By.cssSelector("span .s-pagination-disabled");
    private By paginationNext = By.xpath("//a[text()='Next']");

    public SearchPage(WebDriver driver){
        this.driver = driver;
    }

    public void searchElementWithTitle(String title){

        Actions actions = new Actions(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(totalAvailablePages)).isDisplayed();
        actions.moveToElement(driver.findElements(totalAvailablePages).get(1)).build().perform();
        actions.moveToElement(driver.findElement(resultsText)).build().perform();
        String pagesCount = driver.findElements(totalAvailablePages).get(1).getText();
        System.out.println("Pages count : "+pagesCount);
        int totalPages = Integer.parseInt(pagesCount);
        for(int i=0;i<totalPages;i++){
            List<WebElement> resultList = driver.findElements(noOfResults);
            Optional<WebElement> resultElement = resultList.stream().filter(e->e.getText().contains(title)).findFirst();
            if(resultElement.isPresent())
            {
                System.out.println("Inside if with index "+i);
                By elementToClick = By.xpath("//span[text()='"+title+"']");
                resultElement.get().findElement(elementToClick).click();
                break;
            }
            try{
                actions.moveToElement(driver.findElement(paginationNext)).click().build().perform();
            }
            catch (Exception e){
                System.out.println("Clicked ..."+i);
            }
        }
    }

    public void verifyPage(){
        Assert.assertTrue(driver.findElement(resultsText).isDisplayed());
    }

}
