package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.firefox.FirefoxDriver;
import practicum.MainPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import practicum.OrderPage;
import java.util.Arrays;
import java.util.Collection;

/**
 * Тест для проверки оформления заказа через верхнюю и нижнюю кнопки "Заказать".
 * Использует параметризацию JUnit для запуска тестов с разными наборами данных.
 */
@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private OrderPage orderPage;
    private MainPage mainPage;

    // Параметры для параметризованных тестов
    private final String name;
    private final String lastname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String duration;
    private final String color;
    private final String comment;

    /**
     * Конструктор для передачи параметров из @Parameters
     */
    public OrderTest (String name, String lastname, String address, String metro, String phone,
                      String date, String duration, String color, String comment) {
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.duration = duration;
        this.color = color;
        this.comment = comment;
    }
    /**
     * Метод для передачи тестовых данных.
     * Возвращает коллекцию массивов объектов (каждый массив — набор параметров для одного теста).
     */
    @Parameterized.Parameters (name = "Тестовые данные FAQ {index}: {0} {1}")
    public static Collection <Object[]> Data() {
        return Arrays.asList (new Object[][] {
                // Тест 1 (Дональд Дак)
                {"Дональд", "Дак", "Москва, Красная площадь, 1", "Красносельская", "89059995500", "01.11.2025", "трое суток", "black", "хочу розовый самокат"},
                // Тест 2 (Микки Маус)
                {"Микки", "Маус", "Москва, Фрунзенская набережная, 99", "Парк культуры", "89001001010", "01.01.2026", "двое суток", "grey", "дайте трехколесный"}
        });
    }
    /**
     * Подготовка перед каждым тестом:
     * - Запускает ChromeDriver
     * - Запускает FirefoxDriver
     * - Инициализирует страницы (MainPage, OrderPage)
     * - Открывает главную страницу
     * - Принимает куки
     */
    @Before
    public void setUp() {
        // Запуск ChromeDriver
        ChromeOptions options = new ChromeOptions();
        // driver = new FirefoxDriver(options); /// Запуск FirefoxDriver
        driver = new ChromeDriver(options);
        orderPage = new OrderPage(driver);
        mainPage = new MainPage(driver);
        // Открытие главной страницы и принятие куки
        mainPage.open();
        mainPage.acceptCookies();
    }

    /**
     * Тест для верхней кнопки "Заказать"
     */
    @Test
    // кнопка "Заказать" верхняя
    public void orderTestForTopButton() {
        // Кликаем по верхней кнопке "Заказать"
        mainPage.clickOrderButtonTop();
        // Заполняем первую форму
        orderPage.fieldToFormScooter(name, lastname, address, metro, phone);
        // Жмем "Далее"
        orderPage.clickNextButton();
        // Заполняем вторую форму
        orderPage.filedAboutRental(date, duration, color, comment);
        // Нажимаем "Заказать"
        orderPage.clickOrderButton();
        // Подтверждаем заказ
        orderPage.clickConfirmButton();
        // Окно подтверждения заказа
        orderPage.checkOrderConfirm();
    }
    /**
     * Тест для нижней кнопки "Заказать"
     */
    @Test
    // кнопка "Заказать" нижняя
    public void orderTestForDownButton() {
        // Кликаем по нижней кнопке "Заказать"
        mainPage.clickOrderButtonDown();
        // Заполняем первую форму
        orderPage.fieldToFormScooter(name, lastname, address, metro, phone);
        // Жмем "Далее"
        orderPage.clickNextButton();
        // Заполняем вторую форму
        orderPage.filedAboutRental(date, duration, color, comment);
        // Нажимаем Заказать
        orderPage.clickOrderButton();
        // Подтверждаем заказ
        orderPage.clickConfirmButton();
        // Окно подтверждения заказа
        orderPage.checkOrderConfirm();
    }

    @After
    public void teardown() {
        /// if (driver != null) {
        driver.quit(); // Закрываем браузер
        /// driver = null;
    }
}