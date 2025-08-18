package com.stephen.operation;

import com.stephen.base.BaseAction;
import com.stephen.page.LoginPage;
import org.openqa.selenium.WebDriver;

/**
 * 注册登录页面的具体操作类 / Login Page Operation Class
 * <p>
 * 继承BaseAction类，从LoginPage类获取By类型对象作为参数，调用父类BaseAction的各个方法。<br>
 * Extends BaseAction class, uses By objects from LoginPage as parameters,
 * invokes methods from parent BaseAction class.
 * </p>
 */
public class LoginPageOperation extends BaseAction {

    /**
     * 构造函数 / Constructor
     * <p>
     * 调用父类构造函数。<br>
     * Invokes parent class constructor.
     * </p>
     *
     * @param driver WebDriver实例 / WebDriver instance
     */
    public LoginPageOperation(WebDriver driver) {
        super(driver);
    }

    /**
     * 点击注册单选按钮 / Clicks register radio button
     * <p>
     * 调用父类Click()方法点击注册单选按钮。<br>
     * Invokes parent click() method to click register radio button.
     * </p>
     */
    public void clickRegisterRadioButton() {
        super.click(LoginPage.REGISTER_RADIO_BUTTON);
    }

    /**
     * 点击登录单选按钮 / Clicks login radio button
     * <p>
     * 调用父类Click()方法点击登录单选按钮。<br>
     * Invokes parent click() method to click login radio button.
     * </p>
     */
    public void clickLoginRadioButton() {
        super.click(LoginPage.LOGIN_RADIO_BUTTON);
    }

    /**
     * 输入账号 / Inputs username
     * <p>
     * 调用父类input()方法输入账号。<br>
     * Invokes parent input() method to input username.
     * </p>
     *
     * @param username 账号 / Username
     */
    public void inputUsername(String username) {
        super.input(LoginPage.USERNAME, username);
    }

    /**
     * 输入密码 / Inputs password
     * <p>
     * 调用父类input()方法输入密码。<br>
     * Invokes parent input() method to input password.
     * </p>
     *
     * @param password 密码 / Password
     */
    public void inputPassword(String password) {
        super.input(LoginPage.PASSWORD, password);
    }

    /**
     * 输入确认密码 / Inputs confirm password
     * <p>
     * 调用父类input()方法输入确认密码。<br>
     * Invokes parent input() method to input confirm password.
     * </p>
     *
     * @param confirmPassword 确认密码 / Confirm password
     */
    public void inputConfirmPassword(String confirmPassword) {
        super.input(LoginPage.CONFIRM_PASSWORD, confirmPassword);
    }

    /**
     * 点击确定按钮 / Clicks confirm button
     * <p>
     * 调用父类click()方法点击确定按钮。<br>
     * Invokes parent click() method to click confirm button.
     * </p>
     */
    public void clickConfirmButton() {
        super.click(LoginPage.CONFIRM_BUTTON);
    }

    /**
     * 获取消息文本 / Gets message text
     * <p>
     * 调用父类getText()方法，获取注册或登录操作后，页面上的提示信息文本（用于断言）<br>
     * Invokes parent getText() method to retrieve notification text after
     * registration or login operation (used for assertions).
     * </p>
     *
     * @return 注册或登录结果提示文本 / Notification text of registration or login result
     */
    public String getMessage() {
        return super.getText(LoginPage.MESSAGE);
    }

    /**
     * 清除账号输入框内的文本内容 / Clears text in username input field
     * <p>
     * 调用父类clearText()方法，清除账号输入框内的文本内容<br>
     * Invokes parent clearText() method to clear text in username input field.
     * </p>
     */
    public void clearUsername() {
        super.clearText(LoginPage.USERNAME);
    }

    /**
     * 清除密码输入框内的文本内容 / Clears text in password input field
     * <p>
     * 调用父类clearText()方法，清除密码输入框内的文本内容<br>
     * Invokes parent clearText() method to clear text in password input field.
     * </p>
     */
    public void clearPassword() {
        super.clearText(LoginPage.PASSWORD);
    }
}