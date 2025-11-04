package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import practicum.MainPage;
import practicum.util.DriverManager;
import java.util.Collection;
import java.util.Arrays;

/**
 * Тест для проверки содержимого FAQ (вопросов и ответов) на главной странице.
 * Использует параметризацию JUnit для проверки нескольких пар «вопрос‑ответ».
 */
@RunWith(Parameterized.class)
public class DropDownListTest {
    // Индекс элемента в списке FAQ
    private final int index;
    // Ожидаемый тест вопроса
    private final String expectedQuestionText;
    // Ожидаемый тест ответа
    private final String expectedAnswerText;

    private MainPage mainPage;
    private WebDriver driver;
    private DriverManager driverManager;

    /**
     * Метод, возвращающий тестовые данные для параметризованных тестов.
     * Каждый массив — это набор параметров для одного запуска теста:
     * {индекс, ожидаемый вопрос, ожидаемый ответ}
     */
    @Parameterized.Parameters (name = "Тестовые данные FAQ {0}: вопрос {1} ответ {2}")
    public static Collection <Object[]> faqIndexes() {
        return Arrays.asList(new Object[][]{
                {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        });
    }

    /**
     * Конструктор, получающий параметры из @Parameters.
     * Порядок параметров должен строго соответствовать порядку в массивах из faqIndexes().
     */
    public DropDownListTest (int index, String expectedQuestionText, String expectedAnswerText) {
        this.index = index;
        this.expectedQuestionText = expectedQuestionText;
        this.expectedAnswerText = expectedAnswerText;
    }

    /**
     * Подготовка перед каждым тестом:
     * - Запускает браузер через DriverManager
     * - Открывает главную страницу
     * - Принимает куки
     */
    @Before
    public void setUp() {
        driverManager = new DriverManager();
        driver = driverManager.getDriver();
        mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.acceptCookies();
    }

    /**
     * Тест: кликаем на стрелку FAQ, проверяем текст вопроса и ответа.
     * Использует метод mainPage.locatorFAQ(), который должен:
     * 1. Найти элемент FAQ по индексу.
     * 2. Кликнуть на стрелку (раскрыть ответ).
     * 3. Проверить, что текст вопроса и ответа совпадает с ожидаемым.
     */
    @Test
    public void clickArrowOpenedAnswerText() {
        // Вызываем метод, который выполняет действия и проверки
        mainPage.locatorFAQ(index, expectedQuestionText, expectedAnswerText);
    }


    @After
    public void teardown() {
        if (driverManager != null) {
            driverManager.teardown(); // закрываем браузер
        }
    }
}