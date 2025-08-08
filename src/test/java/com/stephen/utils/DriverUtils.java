package com.stephen.utils;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * 浏览器工具类
 *
 * <p>
 * 用于管理浏览器（打开浏览器、关闭浏览器、添加Cookie等操作）
 * </p>
 *
 */
public class DriverUtils {
    public static final Logger LOGGER = LoggerFactory.getLogger("DriverUtils.class");
    public static Set<Cookie> cookies = null;

    /**
     * 打开浏览器
     *
     * <p>
     * 首次启动或检测到浏览器版本更新后与驱动版本不匹配：自动下载或更新与浏览器版本匹配的浏览器驱动
     * </p>
     *
     * @return WebDriver 浏览器实例对象
     */
    public static WebDriver getDriver() {
        //设置环境变量指定Chrome浏览器驱动的中国地区镜像下载地址（中国地区以外的开发者请删除System.setProperty()这一行代码避免下载速度过慢）
        System.setProperty("wdm.driverManagerUrl",
                "https://registry.npmmirror.com/binary.html?path=chrome-for-testing/");

        //获取浏览器对象并使浏览器窗口最大化
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;

    }

    /**
     * 关闭浏览器
     *
     * <p>
     * 延迟3秒后关闭浏览器
     * </p>
     *
     * @param driver 浏览器实例对象
     */
    public static void quitDriver(WebDriver driver){

        try {
            Thread.sleep(3000);
            driver.quit();
        } catch (InterruptedException e) {
            LOGGER.error("关闭浏览器异常/Fail to quit driver", e);//发生异常时记录日志信息
        }
    }

    /**
     * 获取Cookie
     *
     * <p>
     * 获取当前浏览器对象的所有Cookie存放到Set集合中，用于后续添加Cookie绕过登录态验证
     * </p>
     *
     * @param driver 浏览器实例对象
     */
    public static void getAllCookie(WebDriver driver) {
        cookies = driver.manage().getCookies();
    }

    /**
     * 添加Cookie
     *
     * <p>
     * 遍历存放Cookie的Set集合，并添加Cookie到当前的浏览器
     * </p>
     *
     * @param driver 浏览器实例对象
     */
    public static void addAllCookie(WebDriver driver){
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
    }

}
