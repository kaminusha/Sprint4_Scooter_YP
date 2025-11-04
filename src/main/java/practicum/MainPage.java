package practicum;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private WebDriver driver;
    private final WebDriverWait wait;

    // Базовый URL страницы
    private final String url = "https://qa-scooter.praktikum-services.ru/";

    // Локаторы элементов "Заказать" вверх/вниз
    private final By orderButtonTop = By.className("Button_Button__ra12g");
    private final By orderButtonDown = By.xpath("//div[contains(@class,'Home_FinishButton__1_cWm')]//button[text()='Заказать']");
    private final By acceptCookiesButton = By.id("rcc-confirm-button");

    // Локаторы FAQ вопросы/ответы
    private final By allQuestions = By.cssSelector("div[id^='accordion__heading-']");
    private final By allAnswers = By.cssSelector("div[id^='accordion__panel-']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        // Инициализация ожидания с таймаутом 10 секунд
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Открываем главную страницу сервиса
    public void open() {
        driver.get(url);
    }

    // Принимает куки, кликаем по кнопке согласия
    public void acceptCookies() {
        driver.findElement(acceptCookiesButton).click();
    }

    /**
     * Проверяет корректность отображения FAQ:
     * - находит вопрос по индексу;
     * - прокручивает к нему;
     * - сверяет текст вопроса;
     * - раскрывает ответ;
     * - сверяет текст ответа.
     *
     * @param index индекс элемента FAQ в списке
     * @param expectedQuestionText ожидаемый текст вопроса
     * @param expectedAnswerText ожидаемый текст ответа
     */
    public void locatorFAQ(int index, String expectedQuestionText, String expectedAnswerText) {
        // Получаем список всех вопросов
        List<WebElement> questions = driver.findElements(allQuestions);
        // Ожидаем, пока нужный вопрос станет кликабельным
        WebElement question = wait.until(ExpectedConditions.elementToBeClickable(
                questions.get(index)));
        // Прокручиваем страницу к вопросу
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", question);
        // Получаем фактический текст вопроса
        String actualQuestionText = question.getText();
        // Сравниваем с ожидаемым
        Assert.assertEquals("Текст вопроса не соответствует ожидаемому",
                expectedQuestionText, actualQuestionText);
        // Кликаем по вопросу, чтобы раскрыть ответ
        question.click();
        // Получаем список всех ответов
        List<WebElement> answers = driver.findElements(allAnswers);
        // Ожидаем видимости нужного ответа
        WebElement answer = wait.until(ExpectedConditions.visibilityOf
                (answers.get(index)));
        // Получаем фактический текст ответа
        String actualAnswerText = answer.getText();
        // Сравниваем с ожидаемым
        Assert.assertEquals("Текст ответа не соответствует ожидаемому",
                expectedAnswerText, actualAnswerText);
    }

    // Кликаем по верхней кнопке "Заказать"
    public void clickOrderButtonTop() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonTop)).click();
    }

    // Кликаем по нижней кнопке "Заказать"
    public void clickOrderButtonDown() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonDown)).click();
    }

}