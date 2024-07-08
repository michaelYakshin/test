package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.*;


import java.time.Duration;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        //Инициализация драйвера, переход на страницу приложения
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://127.0.0.1:9090/");

        //Авторизация
        LoginTransvision authorization = new LoginTransvision(driver);
        authorization.setLogin("user1");
        authorization.setPassword("123");
        //Клик по кнопке "Ок" на странице авторизации, переход на страницу пользователя (создание объекта страницы пользователя)
        TransvisionUserPage userPage = authorization.okBtnClick();

        //Ожидание информационного окна, клик на кнопку подтверждения
        WebElement element = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.className("stwindow"))));

        if(driver.findElement(By.className("stwindow")).isDisplayed()){
            driver.findElement(By.className("alert-button")).click();
        }

        //Нажатие на кнопку "Фильтры"
        Filters filter = userPage.openFilters();

        //Ввод фильтра
        filter.setObject("Минск");
        filter.setParameter("ДП");
       filter.setCause("ПУ 5/Д:068 1");

        //Подтверждение фильтра, переход к отфильтрованной странице пользователя (создание нового объектра страницы пользователя)
        TransvisionUserPage filteredPage = filter.okBtnClick();

        //После применения фильтра должна остаться одна строка с осциллограммой.
        //Проверяем количество строк в таблице

        List<WebElement> tableAfterFilter = filteredPage.getObjects();
        assertTrue(tableAfterFilter.size() == 1, "Фильтр не прошел проверку");

        //Заносим параметры отфильтрованной строки в Map

        Map<String, String> filteredValues = new HashMap<>();

        filteredValues.put("timestamp", filteredPage.getTimestamp());
        filteredValues.put("объект", filteredPage.getObject());
        filteredValues.put("регистратор", filteredPage.getDevice());
        filteredValues.put("присоединение", filteredPage.getJoining());
        filteredValues.put("уставка", filteredPage.getParametr());
        filteredValues.put("причина", filteredPage.getCause());
        filteredValues.put("длительность", filteredPage.getDuration());
        filteredValues.put("имя файла", filteredPage.getName());

        //Разлогиниваемся

        LoginTransvision exit = filteredPage.exit();

        //Логинимся заново

        authorization.setLogin("user1");
        authorization.setPassword("123");
        //Клик по кнопке "Ок" на странице авторизации, переход на страницу пользователя (переопределение объекта страницы пользователя)
        userPage = authorization.okBtnClick();

        //Проверяем сохранился ли фильтр по количеству строк таблицы

        List<WebElement> tableAfterLogin = userPage.getObjects();
        assertTrue(tableAfterLogin.size() == 1, "Фильтр не прошел проверку");

        //Заносим параметры строки в новую Map

        Map<String, String> afterLoginValues = new HashMap<>();

        afterLoginValues.put("timestamp", filteredPage.getTimestamp());
        afterLoginValues.put("объект", filteredPage.getObject());
        afterLoginValues.put("регистратор", filteredPage.getDevice());
        afterLoginValues.put("присоединение", filteredPage.getJoining());
        afterLoginValues.put("уставка", filteredPage.getParametr());
        afterLoginValues.put("причина", filteredPage.getCause());
        afterLoginValues.put("длительность", filteredPage.getDuration());
        afterLoginValues.put("имя файла", filteredPage.getName());

        //Проверяем, что до разлогинивания и после строка не изменилась

        assertTrue(filteredValues.get("timestamp").equals(afterLoginValues.get("timestamp")), "Не совпадает поле Timestamp");
        assertTrue(filteredValues.get("объект").equals(afterLoginValues.get("объект")), "Не совпадает поле Объект");
        assertTrue(filteredValues.get("регистратор").equals(afterLoginValues.get("регистратор")), "Не совпадает поле Регистратор");
        assertTrue(filteredValues.get("присоединение").equals(afterLoginValues.get("присоединение")), "Не совпадает поле Присоединение");
        assertTrue(filteredValues.get("уставка").equals(afterLoginValues.get("уставка")), "Не совпадает поле Уставка");
        assertTrue(filteredValues.get("причина").equals(afterLoginValues.get("причина")), "Не совпадает поле Причина");
        assertTrue(filteredValues.get("длительность").equals(afterLoginValues.get("длительность")), "Не совпадает поле Длительность");
        assertTrue(filteredValues.get("имя файла").equals(afterLoginValues.get("имя файла")), "Не совпадает поле Имя файла");

        driver.close();
    }
}