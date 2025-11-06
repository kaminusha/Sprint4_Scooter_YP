package practicum;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Локаторы элементов
    // имя
    public final By nameField = By.xpath("//input[@placeholder='* Имя']");
    // фамилия
    public final By lastnameField = By.xpath("//input[@placeholder='* Фамилия']");
    // адрес
    public final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    // метро
    public final By metroField = By.className("select-search__input");
    // телефон
    public final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    // кнопка "Далее"
    public final By nextButton = By.className("Button_Middle__1CSJM");
    // дата доставки
    public final By dateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    // коментарии
    public final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    // кнопка "Заказать"
    public final By orderButton = By.xpath(
            "//button[contains(@class, 'Button_Middle__1CSJM') and contains(text(), 'Заказать')]");
    // кнопка "Да"
    public final By yesButton = By.xpath(
            "//button[contains(@class, 'Button_Middle__1CSJM') and contains(text(), 'Да')]");
    // окно "Заказ оформлен"
    public final By orderYes = By.xpath(
            "//div[contains(@class,'Order_ModalHeader__3FDaJ') and contains(text(),'Заказ оформлен')]");
    // цвет - черный жемчуг
    private final By blackColor = By.cssSelector("label[for='black'] input");
    // цвет - серая безысходность
    private final By greyColor = By.cssSelector("label[for='grey'] input");


    // Методы заполнения полей
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setLastname(String lastname) {
        driver.findElement(lastnameField).sendKeys(lastname);
    }

    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    // Метод выбора станции метро из выпадающего списка
    public void setMetro(String metro) {
        driver.findElement(metroField).sendKeys(metro);
    }
    public WebElement selectMetroOption(String metro) {
        return driver.findElement(By.xpath("//div[@class='select-search__select']//div[text()='" + metro + "']"));
    }

    public void setPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }


    // Переход к следующему шагу
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    // Комплексное заполнение первой формы
    public void fieldToFormScooter(String name, String lastname, String address,
                                   String metro, String phone) {
        setName(name);
        setLastname(lastname);
        setAddress(address);
        setMetro(metro);
        selectMetroOption(metro).click();
        setPhone(phone);
    }

    // Установка даты доставки
    public void setDate(String date) {
        driver.findElement(dateField).sendKeys(date, Keys.ENTER);
    }

    // Выбор срока аренды из выпадающего списка
    public void rentalPeriodDropdown(String duration) {
        WebElement dropdown = driver.findElement(By.className("Dropdown-root"));
        dropdown.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'Dropdown-option') and text()='" + duration + "']")));
        option.click();
    }

    // Выбор черного цвета самоката
    public void clickColorBoxBlack() {
        driver.findElement(blackColor).click();
    }
    // Выбор серого цвета самоката
    public void clickColorBoxGrey() {
        driver.findElement(greyColor).click();
    }

    // Заполнение поля комментария
    public void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    // Комплексное заполнение второй формы
    public void filedAboutRental(String date, String duration, String color, String comment) {
        setDate(date);
        rentalPeriodDropdown(duration);

        if ("black".equals(color)) {
            clickColorBoxBlack();
        } else {
            clickColorBoxGrey();
        }
        setComment(comment);
    }

    // Нажатие кнопки "Заказать"
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    // Подтверждение заказа
    public void clickConfirmButton() {
        driver.findElement(yesButton).click();
    }

    // Проверка успешного оформления заказа
    public void checkOrderConfirm() {
        Assert.assertTrue(driver.findElement(orderYes).isDisplayed());
    }
}

