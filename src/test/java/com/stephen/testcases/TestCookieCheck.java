package com.stephen.testcases;

import com.stephen.operation.CookieCheckPageOperation;
import com.stephen.utils.DriverUtils;
import com.stephen.utils.GetTestData;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TestCookieCheck {
    public static final Logger LOGGER = LoggerFactory.getLogger("TestCookieCheck.class");
    WebDriver driver;

    public static CookieCheckPageOperation cookieCheckPageOperation;

    @BeforeMethod
    public void getDriver() {
        //调用浏览器工具类，获取浏览器对象，并打开登录态检查页
        driver = DriverUtils.getDriver();
        driver.get("http://127.0.0.1:8080/cookie_check");

        //每个测试方法（每条测试用例）执行前都需要实例化一个登录态检查页面操作类的对象
        cookieCheckPageOperation = new CookieCheckPageOperation(driver);

    }

    @AfterMethod
    public void quitDriver() {
        DriverUtils.quitDriver(driver);
    }

    @Test(groups = "CookieCheck", dependsOnGroups = {"Login"}, priority = 1, dataProvider = "LoginCookieCheckData", dataProviderClass = GetTestData.class)
    public void testCookieCheck(String expectedResult) {

        try {
            DriverUtils.addAllCookie(driver);
            driver.navigate().refresh();
            Thread.sleep(1000);

            cookieCheckPageOperation.clickCookieCheckButton();
            Thread.sleep(1000);

            Assert.assertEquals(cookieCheckPageOperation.getCookieCheckResult(), expectedResult);
        } catch (InterruptedException e) {
            LOGGER.error("检查登录态测试用例异常/Cookie Check Testcase Error", e);
        }

    }
}
