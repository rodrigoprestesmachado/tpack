package edu.ifrs.tpack.integration;

import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *  Exemplo Selenium/Firefox Driver com Junit 5.
 */
public class WebTest {
    private static WebDriver driver;
    private JavascriptExecutor js = (JavascriptExecutor) driver;

    /**
     *  Firefox Driver.
     */
    @BeforeAll
    public static void setUp() {
        WebTest.driver = new FirefoxDriver();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @SuppressWarnings("checkstyle:magicnumber")
    @Test
    public void web() {
        driver.get("http://localhost/openapi/ui/");
        driver.manage().window().setSize(new Dimension(952, 741));
        driver.findElement(By.cssSelector("#operations-default-getSessions > .opblock-summary")).click();
        driver.findElement(By.cssSelector(".btn")).click();
        driver.findElement(By.cssSelector(".execute")).click();
    }

}