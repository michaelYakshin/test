import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


import java.time.Duration;

public class Locators extends Base {


    @Test
    public void test(){
        driver.get("http://google.com");
        WebElement waitElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("btnK"))
        );


        WebElement btn = driver.findElement(By.name("btnK"));
        if (btn.isDisplayed()){
            System.out.println("It's here!");
        }
        else {
            System.out.println("No Element");
            return;
        }
        driver.findElement(By.name("q")).sendKeys("webdriver");
        WebElement btnK = driver.findElement(By.name("btnK"));
        btnK.click();
        wait.until(titleIs("webdriver - Поиск в Google"));
    }


}
