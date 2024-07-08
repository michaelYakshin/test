package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginTransvision {
    private WebDriver driver;
    private By login = By.name("transLog");
    private By password = By.name("transPwd");
    private By okBtn = By.id("authOk");


    public LoginTransvision(WebDriver driver){
        this.driver = driver;
    }

    public void setLogin(String login){
        driver.findElement(this.login).sendKeys(login);
    }

    public void setPassword(String password){
        driver.findElement(this.password).sendKeys(password);
    }

    public TransvisionUserPage okBtnClick(){
        driver.findElement(this.okBtn).click();
        return new TransvisionUserPage(this.driver);
    }
}
