package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Filters extends TransvisionUserPage{
    private WebDriver driver;
    private By objectField = By.id("object_0");
    private By parameterField = By.id("parametr_0");
    private By causeField = By.id("cause_0");
    private By okFilterBtn = By.id("goalrt");
    public Filters(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void setObject(String object){
        driver.findElement(this.objectField).sendKeys(object);
    }

    public void setParameter(String parameter){
        driver.findElement(this.parameterField).sendKeys(parameter);
    }

    public void setCause(String cause){
        driver.findElement(causeField).sendKeys(cause);
    }

    TransvisionUserPage okBtnClick(){
        driver.findElement(this.okFilterBtn).click();
        return new TransvisionUserPage(this.driver);
    }



}
