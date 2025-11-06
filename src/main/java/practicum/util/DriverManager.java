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

    public void teardown() {
            driver.quit(); // закрываем браузер
        }
    }

