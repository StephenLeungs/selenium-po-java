package com.stephen.page;

import org.openqa.selenium.By;

/**
 * 登录态检查页页面元素类 / Cookie Check Page Elements Class
 * <p>
 * 封装登录态检查页的各个元素。<br>
 * Encapsulates elements of the cookie check page.
 * </p>
 */
public class CookieCheckPage {

    /**
     * 私有构造函数 / Private constructor
     * <p>
     * 防止类被实例化。<br>
     * Prevents class instantiation.
     * </p>
     */
    private CookieCheckPage() {

    }

    /**
     * 登录态检查页标题文本 / Cookie check page title text
     * <p>
     * CSS选择器: body > div > h2<br>
     * CSS selector: body > div > h2
     * </p>
     */
    public static final By CHECK_COOKIE_PAGE_TEXT = By.cssSelector("body > div > h2");

    /**
     * 检查登录态按钮 / Check cookie button
     * <p>
     * CSS选择器: #checkBtn<br>
     * CSS selector: #checkBtn
     * </p>
     */
    public static final By CHECK_COOKIE_BUTTON = By.cssSelector("#checkBtn");

    /**
     * 检查登录态结果文本 / Cookie check result text
     * <p>
     * CSS选择器: #result<br>
     * CSS selector: #result
     * </p>
     */
    public static final By CHECK_COOKIE_RESULT = By.cssSelector("#result");

}
