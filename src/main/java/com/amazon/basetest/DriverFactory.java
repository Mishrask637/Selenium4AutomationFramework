package com.amazon.basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverFactory {
    private static DriverFactory driverFactory = null;
    private static WebDriver driver = null;

    private DriverFactory(){
        String browser = System.getenv("Browser");

        switch (browser.toLowerCase()){
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
            default:
                System.out.println("Please enter proper value");
                System.exit(1);
        }
    }

    public static synchronized DriverFactory getInstance(){
        if(driverFactory == null)
            driverFactory = new DriverFactory();
        return driverFactory;
    }

    public static WebDriver getDriver(){
        DriverFactory.getInstance();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        return driver;
    }
}
