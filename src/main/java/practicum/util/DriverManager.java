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
        // if (driver != null) {
        // try {
        driver.quit();
        //  } catch (Exception e) {
        // System.err.println("Ошибка при закрытии драйвера: " + e.getMessage());
        // } finally {
        //  driver = null;
        //  }
        //  } else {
        //  System.out.println("Драйвер уже закрыт или не был инициализирован.");
    }
}
/**
 * Альтернативный метод для мягкого закрытия драйвера (без quit).
 * Использует close() для закрытия текущего окна, но оставляет драйвер активным.
 * Может быть полезен в сценариях, где требуется сохранить сеанс.
 */
// public void close() {
// if (driver != null) {
//  try {
//driver.close();
// } catch (Exception e) {
// System.err.println("Ошибка при закрытии окна драйвера: " + e.getMessage());}
// } else {
// System.out.println("Драйвер не инициализирован, close() не требуется.");
// }
//  }
// }

