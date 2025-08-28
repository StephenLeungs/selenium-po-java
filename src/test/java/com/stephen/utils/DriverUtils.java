package com.stephen.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * 浏览器工具类 / WebDriver Utility Class
 *
 * <p>
 * 用于管理浏览器（打开浏览器、关闭浏览器、添加Cookie等操作）<br>
 * Manages browser operations (open browser, close browser, add cookies, etc.)
 * </p>
 */
public class DriverUtils {
    //日志器 / Logger
    public static final Logger LOGGER = LoggerFactory.getLogger("DriverUtils.class");

    //用于存放Cookie的Set集合 / Set collection for storing cookies
    public static Set<Cookie> cookies = null;

    /**
     * 打开浏览器 / Opens browser
     *
     * <p>
     * 首次启动或检测到浏览器版本更新后与驱动版本不匹配：自动下载或更新与浏览器版本匹配的浏览器驱动<br>
     * Automatically downloads/updates browser driver matching browser version when:
     * 1. First launch, or
     * 2. Browser version update causes driver version mismatch
     * </p>
     *
     * @return WebDriver 浏览器实例对象 / WebDriver instance
     */
    public static WebDriver getDriver() {
        //设置环境变量指定Chrome浏览器驱动的中国地区镜像下载地址（中国地区以外的开发者请去掉System.setProperty()方法的调用，避免下载速度过慢）
        //Sets environment variable for Chrome driver mirror in China region (Non-China developers should remove this to avoid slow downloads)
        System.setProperty("wdm.driverManagerUrl",
                "https://registry.npmmirror.com/binary.html?path=chrome-for-testing/");

        //获取浏览器对象并使浏览器窗口最大化
        //Acquires WebDriver instance and maximizes browser window
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    /**
     * 关闭浏览器 / Closes browser
     *
     * <p>
     * 延迟3秒后关闭浏览器<br>
     * Closes browser after 3 seconds delay
     * </p>
     *
     * @param driver 浏览器实例对象 / WebDriver instance
     */
    public static void quitDriver(WebDriver driver){
        try {
            Thread.sleep(3000);
            driver.quit();
        } catch (InterruptedException e) {
            //发生异常时记录日志信息
            //Logs error message when exception occurs
            LOGGER.error("关闭浏览器异常/Fail to quit driver", e);
        }
    }

    /**
     * 获取Cookie / Retrieves cookies
     *
     * <p>
     * 获取当前浏览器对象的所有Cookie存放到Set集合中，用于后续添加Cookie绕过登录态验证<br>
     * Retrieves all cookies from current browser and stores in Set collection,
     * used for subsequent login status bypass
     * </p>
     *
     * @param driver 浏览器实例对象 / WebDriver instance
     */
    public static void getAllCookie(WebDriver driver) {
        cookies = driver.manage().getCookies();
    }

    /**
     * 添加Cookie / Adds cookies
     *
     * <p>
     * 遍历存放Cookie的Set集合，并把Cookie添加到当前的浏览器<br>
     * Iterates through cookie Set collection and adds cookies to current browser
     * </p>
     *
     * @param driver 浏览器实例对象 / WebDriver instance
     */
    public static void addAllCookie(WebDriver driver){
        driver.manage().deleteAllCookies();
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        driver.navigate().refresh();
    }
}