package practicum.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {
    private WebDriver driver;

    /**
     * Возвращает инициализированный WebDriver.
     * Если драйвер ещё не создан, создаёт его в соответствии с системным свойством 'browser'.
     *
     * @return инициализированный экземпляр WebDriver
     */
    public WebDriver getDriver() {
        if (driver == null) {
            String browser = System.getProperty("browser", "chrome").toLowerCase();

            switch (browser) {
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "chrome":
                default:
                    ChromeOptions options = new ChromeOptions();
                    driver = new ChromeDriver(options);
                    break;
            }
        }
        return driver;
    }

    /**
     * Корректно закрывает драйвер и освобождает ресурсы.
     * Проверяет, что драйвер не равен null, и вызывает quit().
     * После закрытия устанавливает driver = null для предотвращения повторного использования.
     */
    public void teardown() {
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("Драйвер закрыт (если был инициализирован).");
        }
    }
}

