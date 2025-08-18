package com.stephen.operation;

import com.stephen.base.BaseAction;
import com.stephen.page.CookieCheckPage;
import org.openqa.selenium.WebDriver;

/**
 * 登录态检查页面的具体操作类 / Login Status Check Page Operation Class
 * <p>
 * 继承BaseAction类，从CookieCheckPage类获取By类型对象作为参数，调用父类BaseAction的各个方法。<br>
 * Extends BaseAction class, uses By objects from CookieCheckPage as parameters,
 * invokes methods from parent BaseAction class.
 * </p>
 */
public class CookieCheckPageOperation extends BaseAction {
    /**
     * 构造函数 / Constructor
     * <p>
     * 调用父类构造函数。<br>
     * Invokes parent class constructor.
     * </p>
     *
     * @param driver WebDriver实例 / WebDriver instance
     */
    public CookieCheckPageOperation(WebDriver driver) {
        super(driver);
    }

    /**
     * 点击登录态检查按钮 / Clicks login status check button
     * <p>
     * 调用父类Click()方法点击登录态检查按钮。<br>
     * Invokes parent click() method to click login status check button.
     * </p>
     */
    public void clickCookieCheckButton() {
        super.click(CookieCheckPage.CHECK_COOKIE_BUTTON);
    }

    /**
     * 获取登录态检查结果文本 / Gets login status check result text
     * <p>
     * 调用父类getText()方法，获取点击登录态检查按钮后，页面上的提示信息文本（用于断言）<br>
     * Invokes parent getText() method to retrieve notification text after clicking
     * login status check button (used for assertions).
     * </p>
     *
     * @return 登录态检查结果 / Login status check result
     */
    public String getCookieCheckResult() {
        return super.getText(CookieCheckPage.CHECK_COOKIE_RESULT);
    }
}