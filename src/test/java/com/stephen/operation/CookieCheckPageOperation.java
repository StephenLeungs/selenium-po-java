package com.stephen.operation;

import com.stephen.base.BaseAction;
import com.stephen.page.CookieCheckPage;
import org.openqa.selenium.WebDriver;

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

    public void clickCookieCheckButton() {
        super.click(CookieCheckPage.CHECK_COOKIE_BUTTON);
    }

    public String getCookieCheckResult() {
        return super.getText(CookieCheckPage.CHECK_COOKIE_RESULT);
    }

}
