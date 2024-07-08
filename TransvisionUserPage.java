package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TransvisionUserPage {
    private WebDriver driver;
    public TransvisionUserPage(WebDriver driver){
        this.driver = driver;
    }

    private By exitBtn = By.className("transAuth-out");
    private By filters = By.className("pan-buttons");
    private By timestamp = By.cssSelector("tr td[data-name='timestamp']");
    private By object = By.cssSelector("tr td[data-name='object']");
    private By device = By.cssSelector("tr td[data-name='device']");
    private By joining = By.cssSelector("tr td[data-name='joining']");
    private By parametr = By.cssSelector("tr td[data-name='parametr']");
    private By cause = By.cssSelector("tr td[data-name='cause']");
    private By duration = By.cssSelector("tr td[data-name='duration']");
    private By name = By.cssSelector("tr td[data-name='name']");

    public Filters openFilters(){
        driver.findElement(this.filters).click();
        return new Filters(this.driver);
    }

    public String getTimestamp(){
        return driver.findElement(timestamp).getAttribute("textContent");
    }

    public String getObject(){
        return driver.findElement(object).getAttribute("textContent");
    }

    public String getDevice(){
        return driver.findElement(device).getAttribute("textContent");
    }

    public String getJoining(){
        return driver.findElement(joining).getAttribute("textContent");
    }

    public String getParametr(){
        return driver.findElement(parametr).getAttribute("textContent");
    }

    public String getCause(){
        return driver.findElement(cause).getAttribute("textContent");
    }

    public String getDuration(){
        return driver.findElement(duration).getAttribute("textContent");
    }

    public String getName(){
        return driver.findElement(name).getAttribute("textContent");
    }

    //Выгрузка элементов таблицы пользовательской страницы
    public List<WebElement> getObjects(){
        List<WebElement> objects = driver.findElements(object);
        return objects;
    }

    public LoginTransvision exit(){
        driver.findElement(exitBtn).click();
        return new LoginTransvision(this.driver);
    }


}
