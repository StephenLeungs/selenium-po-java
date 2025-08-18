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

/**
 * 登录态检查相关的测试用例 / Login Status Check Test Cases
 * <p>
 * 包含用于示例的登录态检查测试用例<br>
 * Contains sample test cases for login status check functionality.
 * </p>
 */
public class TestCookieCheck {
    //日志器 / Logger
    public static final Logger LOGGER = LoggerFactory.getLogger("TestCookieCheck.class");

    //浏览器实例对象 / Browser instance object
    WebDriver driver;

    //登录态检查页面操作类实例对象 / Login status check page operation class instance
    public static CookieCheckPageOperation cookieCheckPageOperation;

    /**
     * BeforeMethod特殊方法 / BeforeMethod Special Method
     * <p>
     * 每个测试方法（每条测试用例）执行前都会执行一次，用于调用浏览器工具类的静态方法获取浏览器实例对象以及实例化登录态检查页面操作类的对象<br>
     * Executes before each test method (test case) to:
     * 1. Acquire browser instance via DriverUtils
     * 2. Instantiate CookieCheckPageOperation object
     * </p>
     */
    @BeforeMethod
    public void getDriver() {
        //调用浏览器工具类的静态方法，获取浏览器对象，并打开登录态检查页
        //Invokes DriverUtils to get browser instance and open login status check page
        driver = DriverUtils.getDriver();
        driver.get("http://127.0.0.1:8080/cookie_check");

        //每个测试方法（每条测试用例）执行前都需要实例化一个登录态检查页面操作类的对象
        //Instantiates CookieCheckPageOperation before each test case
        cookieCheckPageOperation = new CookieCheckPageOperation(driver);
    }

    /**
     * AfterMethod特殊方法 / AfterMethod Special Method
     * <p>
     * 每个测试方法（每条测试用例）执行后都会执行一次，用于调用浏览器工具类的静态方法关闭浏览器<br>
     * Executes after each test method (test case) to close browser via DriverUtils.
     * </p>
     */
    @AfterMethod
    public void quitDriver() {
        DriverUtils.quitDriver(driver);
    }

    /**
     * 登录态检查功能测试用例 / Login Status Check Functionality Test Case
     * <p>
     * 测试添加Cookie后是否成功获取了登录态（@Test注解声明了所使用的DataProvider以及DataProvider所在的类）<br>
     * Tests if login status is successfully acquired after adding cookies.
     * (@Test annotation specifies DataProvider and its class).
     * </p>
     * @param expectedResult DataProvider读取到的期望结果测试数据（用于断言） / Expected result for assertions
     */
    @Test(groups = "CookieCheck", dependsOnGroups = {"Login"}, priority = 1, dataProvider = "LoginCookieCheckData",
            dataProviderClass = GetTestData.class)
    public void testCookieCheck(String expectedResult) {

        //调用sleep()方法实现的暂停仅供调试时观察效果，实际执行可根据脚本执行效率提升等需要删除掉sleep()方法
        //Sleep pauses are for debugging only, remove for better execution efficiency
        try {
            //调用浏览器工具类的静态方法，添加Cookie并刷新页面（Cookie来源是登录测试用例断言通过后获取的）
            //Adds cookies (retrieved from login test case) and refreshes page
            DriverUtils.addAllCookie(driver);
            driver.navigate().refresh();
            Thread.sleep(1000);

            //点击登录态检查按钮 / Click login status check button
            cookieCheckPageOperation.clickCookieCheckButton();
            Thread.sleep(1000);

            //根据点击登录态检查按钮后的提示信息进行断言 / Assert based on post-check message
            Assert.assertEquals(cookieCheckPageOperation.getCookieCheckResult(), expectedResult);
        } catch (InterruptedException e) {
            //发生异常时记录日志信息 / Log error on exception
            LOGGER.error("检查登录态测试用例异常/Cookie Check Testcase Error", e);
        }
    }
}