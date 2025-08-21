package com.stephen.testcases;

import com.stephen.operation.LoginPageOperation;
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
 * 注册和登录相关的测试用例 / Registration and Login Test Cases
 * <p>
 * 包含注册和登录两条用于示例的测试用例<br>
 * Contains sample test cases for registration and login functionalities.
 * </p>
 */
public class TestLogin {
    //日志器 / Logger
    public static final Logger LOGGER = LoggerFactory.getLogger("TestLogin.class");

    //浏览器实例对象 / WebDriver instance
    WebDriver driver;

    //登录页面操作类实例对象 / Login page operation class instance
    public static LoginPageOperation loginPageOperation;

    /**
     * BeforeMethod特殊方法 / BeforeMethod Special Method
     * <p>
     * 每个测试方法（每条测试用例）执行前都会执行一次，用于调用浏览器工具类的静态方法获取浏览器实例对象以及实例化登录页面操作类的对象<br>
     * Executes before each test method (test case) to:
     * 1. Acquire WebDriver instance via DriverUtils
     * 2. Instantiate LoginPageOperation object
     * </p>
     */
    @BeforeMethod
    public void getDriver() {
        //调用浏览器工具类的静态方法，获取浏览器对象，并打开注册登录页
        //Invokes DriverUtils to get WebDriver instance and open registration/login page
        driver = DriverUtils.getDriver();
        driver.get("http://127.0.0.1:8080/login");

        //每个测试方法（每条测试用例）执行前都需要实例化一个注册登录页面操作类的对象
        //Instantiates LoginPageOperation before each test case
        loginPageOperation = new LoginPageOperation(driver);
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
     * 注册功能测试用例 / Registration Functionality Test Case
     * <p>
     * 用于测试注册，各个参数均由DataProvider读取Excel文件提供测试数据参数化传入
     * （@Test注解声明了所使用的DataProvider以及DataProvider所在的类）<br>
     * Tests registration functionality with parameters from Excel via DataProvider.
     * (@Test annotation specifies DataProvider and its class).
     * </p>
     * @param username DataProvider读取到的账号测试数据 / Username from DataProvider
     * @param password DataProvider读取到的密码测试数据 / Password from DataProvider
     * @param confirmPassword DataProvider读取到的确认密码测试数据 / Confirm password from DataProvider
     * @param expectedResult DataProvider读取到的期望结果测试数据（用于断言） / Expected result for assertions
     */
    @Test(groups = "Login", priority = 1,dataProvider = "RegisterData", dataProviderClass = GetTestData.class)
    public void testRegister(String username, String password, String confirmPassword, String expectedResult) {

        //调用sleep()方法实现的暂停仅供调试时观察效果，实际执行可根据脚本执行效率提升等需要删除掉sleep()方法
        //Sleep pauses are for debugging only, remove for better execution efficiency
        try {
            Thread.sleep(1000);

            //点击注册单选按钮 / Click register radio button
            loginPageOperation.clickRegisterRadioButton();
            Thread.sleep(1000);

            //输入账号 / Input username
            loginPageOperation.inputUsername(username);
            Thread.sleep(1000);

            //输入密码 / Input password
            loginPageOperation.inputPassword(password);
            Thread.sleep(1000);

            //输入确认密码 / Input confirm password
            loginPageOperation.inputConfirmPassword(confirmPassword);
            Thread.sleep(1000);

            //点击确认按钮 / Click confirm button
            loginPageOperation.clickConfirmButton();

            //根据注册操作后的提示信息进行断言 / Assert based on post-registration message
            Assert.assertEquals(loginPageOperation.getMessage(), expectedResult);

        } catch (InterruptedException e) {
            //发生异常时记录日志信息 / Log error on exception
            LOGGER.error("注册测试用例异常/Register Testcase Error", e);
        }
    }

    /**
     * 登录功能测试用例 / Login Functionality Test Case
     * <p>
     * 用于测试登录，各个参数均由DataProvider读取Excel文件提供测试数据参数化传入
     * （@Test注解声明了所使用的DataProvider以及DataProvider所在的类）<br>
     * Tests login functionality with parameters from Excel via DataProvider.
     * (@Test annotation specifies DataProvider and its class).
     * </p>
     * @param username DataProvider读取到的账号测试数据 / Username from DataProvider
     * @param password DataProvider读取到的密码测试数据 / Password from DataProvider
     * @param expectedResult DataProvider读取到的期望结果测试数据（用于断言） / Expected result for assertions
     */
    @Test(groups = "Login", priority = 2, dataProvider = "LoginData", dataProviderClass = GetTestData.class)
    public void testLogin(String username, String password, String expectedResult) {

        //调用sleep()方法实现的暂停仅供调试时观察效果，实际执行可根据脚本执行效率提升等需要删除掉sleep()方法
        //Sleep pauses are for debugging only, remove for better execution efficiency
        try {
            Thread.sleep(1000);

            //点击登录单选按钮 / Click login radio button
            loginPageOperation.clickLoginRadioButton();
            Thread.sleep(1000);

            //输入账号前需要先清除账号输入框里的内容 / Clear username field before input
            loginPageOperation.clearUsername();
            Thread.sleep(1000);

            //输入密码前需要先清除密码输入框里的内容 / Clear password field before input
            loginPageOperation.clearPassword();
            Thread.sleep(1000);

            //输入账号 / Input username
            loginPageOperation.inputUsername(username);
            Thread.sleep(1000);

            //输入密码 / Input password
            loginPageOperation.inputPassword(password);
            Thread.sleep(1000);

            //点击确认按钮 / Click confirm button
            loginPageOperation.clickConfirmButton();
            Thread.sleep(1000);

            //根据登录操作后的提示信息进行断言 / Assert based on post-login message
            Assert.assertEquals(loginPageOperation.getMessage(), expectedResult);

            //登录成功（断言通过）后，再调用浏览器工具类的静态方法获取浏览器上的所有Cookie
            //After successful login (assertion passed), retrieve all cookies
            DriverUtils.getAllCookie(driver);

        } catch (InterruptedException e) {
            //发生异常时记录日志信息 / Log error on exception
            LOGGER.error("登录测试用例异常/Login Testcase Error", e);
        }
    }
}