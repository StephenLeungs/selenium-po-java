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


public class TestLogin {
    public static final Logger LOGGER = LoggerFactory.getLogger("TestLogin.class");
    WebDriver driver;

    public static LoginPageOperation loginPageOperation;

    @BeforeMethod
    public void getDriver() {
        //调用浏览器工具类，获取浏览器对象，并打开注册登录页
        driver = DriverUtils.getDriver();
        driver.get("http://127.0.0.1:8080/login");

        //每个测试方法（每条测试用例）执行前都需要实例化一个注册登录页面操作类的对象
        loginPageOperation = new LoginPageOperation(driver);
    }

    @AfterMethod
    public void quitDriver() {
        DriverUtils.quitDriver(driver);
    }

    @Test(groups = "Login", priority = 1,dataProvider = "RegisterData", dataProviderClass = GetTestData.class)
    public void testRegister(String username, String password, String confirmPassword, String expectedResult) {

        try {
            Thread.sleep(1000);

            loginPageOperation.clickRegisterRadioButton();
            Thread.sleep(1000);

            loginPageOperation.inputUsername(username);
            Thread.sleep(1000);

            loginPageOperation.inputPassword(password);
            Thread.sleep(1000);

            loginPageOperation.inputConfirmPassword(confirmPassword);
            Thread.sleep(1000);

            loginPageOperation.clickConfirmButton();

            Assert.assertEquals(loginPageOperation.getMessage(), expectedResult);

        } catch (InterruptedException e) {
            LOGGER.error("注册测试用例异常/Register Testcase Error", e);//发生异常时记录日志信息
        }

    }

    @Test(groups = "Login", priority = 2, dataProvider = "LoginData", dataProviderClass = GetTestData.class)
    public void testLogin(String username, String password, String expectedResult) {

        try {
            Thread.sleep(1000);

            loginPageOperation.clickLoginRadioButton();
            Thread.sleep(1000);

            loginPageOperation.clearUsername();
            Thread.sleep(1000);

            loginPageOperation.clearPassword();
            Thread.sleep(1000);

            loginPageOperation.inputUsername(username);
            Thread.sleep(1000);

            loginPageOperation.inputPassword(password);
            Thread.sleep(1000);

            loginPageOperation.clickConfirmButton();
            Thread.sleep(1000);

            Assert.assertEquals(loginPageOperation.getMessage(), expectedResult);

            DriverUtils.getAllCookie(driver);

        } catch (InterruptedException e) {
            LOGGER.error("登录测试用例异常/Login Testcase Error", e);
        }


    }




}
