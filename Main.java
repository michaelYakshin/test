package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Переход на страницу авторизации

        driver.get("http://127.0.0.1:9090/");

        WebElement login = driver.findElement(By.name("transLog"));
        login.sendKeys("user");

        WebElement password = driver.findElement(By.name("transPwd"));
        password.sendKeys("123");

        WebElement okBtn = driver.findElement(By.id("authOk"));
        okBtn.click();

        //Ожидание информационного окна

        WebElement element = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.className("stwindow"))));

        if(driver.findElement(By.className("stwindow")).isDisplayed()){
            driver.findElement(By.className("alert-button")).click();
        }

        //Нажатие на кнопку "Фильтры"

        WebElement filters = driver.findElement(By.className("pan-buttons"));
        filters.click();

        //Ввод фильтра

        WebElement objectField = driver.findElement(By.id("object_0"));
        objectField.sendKeys("ТП24");

        WebElement parameterField = driver.findElement(By.id("parametr_0"));
        parameterField.sendKeys("ДЗ");

        WebElement causeField = driver.findElement(By.id("cause_0"));
        causeField.sendKeys("значение выше нормы");

        WebElement okFilter = driver.findElement(By.id("goalrt"));
        okFilter.click();

        //Поиск отфильтрованных элементов в таблице
        //Ищутся заданные фильтры: в столбце "Объект" - "ТП24", в столбце "Уставка" - "ДЗ",
        //в столбце "Причина" - "значение выше нормы".
        //Для каждого элемента фильтра (объект, уставка, причина) создан счетчик.
        //После фильтрации на странице должно быть 9 строк.
        //Соответственно, у счетчиков объекта, уставки и причины должны быть значения 9.

        List<WebElement> object = driver.findElements(By.cssSelector("tr td[data-name='object']"));

        int objectCounter = 0;

        for (int i = 0; i < object.size(); i++){
            WebElement element1 = object.get(i);
            if (!element1.getAttribute("textContent").equals("ТП24")){
                break;
            }
            else {
                objectCounter++;
            }

        }

        List<WebElement> parametr = driver.findElements(By.cssSelector("tr td[data-name='parametr']"));

        int parametrCounter = 0;

        for (int i = 0; i < parametr.size(); i++){
            WebElement element1 = parametr.get(i);
            if (!element1.getAttribute("textContent").equals("ДЗ")){
                break;
            }
            else {
                parametrCounter++;
            }

        }

        List<WebElement> cause = driver.findElements(By.cssSelector("tr td[data-name='cause']"));

        int causeCounter = 0;

        for (int i = 0; i < cause.size(); i++){
            WebElement element1 = cause.get(i);
            if (!element1.getAttribute("textContent").equals("значение выше нормы")){
                break;
            }
            else {
                causeCounter++;
            }

        }

        //Если какой-либо счетчик не равен 9, то выводится сообщение Test failed

        if (objectCounter != 9 || parametrCounter != 9 || causeCounter != 9){
            System.out.println("Test failed");
        }

        //Сброс счетчиков

        objectCounter = 0;
        parametrCounter = 0;
        causeCounter = 0;

        //Выход из аккаунта

        driver.findElement(By.cssSelector("#outpunkt")).click();

        //Переопределение элементов на странице авторизации и вход

        login = driver.findElement(By.name("transLog"));
        login.sendKeys("user");

        password = driver.findElement(By.name("transPwd"));
        password.sendKeys("123");

        okBtn = driver.findElement(By.id("authOk"));
        okBtn.click();

        //После входа фильтр должен сохраниться. Проверяем значения отфильтрованной таблицы

        object = driver.findElements(By.cssSelector("tr td[data-name='object']"));


        for (int i = 0; i < object.size(); i++){
            WebElement element1 = object.get(i);
            if (!element1.getAttribute("textContent").equals("ТП24")){
                break;
            }
            else {
                objectCounter++;
            }

        }

        parametr = driver.findElements(By.cssSelector("tr td[data-name='parametr']"));


        for (int i = 0; i < parametr.size(); i++){
            WebElement element1 = parametr.get(i);
            if (!element1.getAttribute("textContent").equals("ДЗ")){
                break;
            }
            else {
                parametrCounter++;
            }

        }

        cause = driver.findElements(By.cssSelector("tr td[data-name='cause']"));


        for (int i = 0; i < cause.size(); i++){
            WebElement element1 = cause.get(i);
            if (!element1.getAttribute("textContent").equals("значение выше нормы")){
                break;
            }
            else {
                causeCounter++;
            }

        }


        //Проверяем значения счетчиков. Если все счетчики равны 9, то тест прошел.
        //Если хоть один не равен, то тест провален.

        if (objectCounter == 9 && parametrCounter == 9 && causeCounter == 9){
            System.out.println("Test passed!");
        }
        else {
            System.out.println("Test failed");
        }

        driver.close();
    }
}