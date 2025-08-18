package com.stephen.page;

import org.openqa.selenium.By;

/**
 * 注册登录页页面元素类 / Login Page Elements Class
 * <p>
 * 封装注册登录页的各个元素。<br>
 * Encapsulates elements of the login/registration page.
 * </p>
 */
public class LoginPage {

    /**
     * 私有构造函数 / Private constructor
     * <p>
     * 防止类被实例化。<br>
     * Prevents class instantiation.
     * </p>
     */
    private LoginPage() {

    }

    /**
     * 注册单选按钮 / Register radio button
     * <p>
     * CSS选择器: #registerRadio<br>
     * CSS selector: #registerRadio
     * </p>
     */
    public static final By REGISTER_RADIO_BUTTON = By.cssSelector("#registerRadio");

    /**
     * 登录单选按钮 / Login radio button
     * <p>
     * CSS选择器: #loginRadio<br>
     * CSS selector: #loginRadio
     * </p>
     */
    public static final By LOGIN_RADIO_BUTTON = By.cssSelector("#loginRadio");

    /**
     * 账号输入框 / Username input field
     * <p>
     * CSS选择器: #username<br>
     * CSS selector: #username
     * </p>
     */
    public static final By USERNAME = By.cssSelector("#username");

    /**
     * 密码输入框 / Password input field
     * <p>
     * CSS选择器: #password<br>
     * CSS selector: #password
     * </p>
     */
    public static final By PASSWORD = By.cssSelector("#password");

    /**
     * 确认密码输入框 / Confirm password input field
     * <p>
     * CSS选择器: #confirmPassword<br>
     * CSS selector: #confirmPassword
     * </p>
     */
    public static final By CONFIRM_PASSWORD = By.cssSelector("#confirmPassword");

    /**
     * 确定按钮 / Confirm button
     * <p>
     * CSS选择器: #submitBtn<br>
     * CSS selector: #submitBtn
     * </p>
     */
    public static final By CONFIRM_BUTTON = By.cssSelector("#submitBtn");

    /**
     * 进行注册或登录操作后的消息文本 / Message text after registration or login operation
     * <p>
     * CSS选择器: #message<br>
     * CSS selector: #message
     * </p>
     */
    public static final By MESSAGE = By.cssSelector("#message");

}
