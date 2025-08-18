package com.stephen.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * 基础页面操作类 / Base Page Action Class
 *
 * <p>
 * 作为父类提供给各个页面的Operation子类进行继承，由Operation子类实现具体的页面操作。
 * 封装了常见的WebDriver操作和元素交互方法。
 * <br>
 * Serves as the base class for page-specific Operation subclasses.
 * Encapsulates common WebDriver operations and element interactions.
 * </p>
 */
public class BaseAction {

    private final WebDriver driver;

    /**
     * 构造函数 / Constructor
     *
     * @param driver WebDriver实例 / WebDriver instance
     */
    public BaseAction(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * 定位元素并添加显式等待 / Locates element with explicit wait
     *
     * <p>
     * 等待元素在DOM中出现（最多10秒），不保证元素可见或可交互。
     * <br>
     * Waits for element presence in DOM (max 10 seconds),
     * does not guarantee visibility or interactability.
     * </p>
     *
     * @param by 元素定位器 (不能为null) / Element locator (cannot be null)
     * @return 定位到的页面元素 / Located page element
     */
    public WebElement findEle(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * 在输入框元素中输入文本 / Inputs text into an input field element
     *
     * <p>
     * 先定位元素，然后执行sendKeys()操作。不会清空现有内容，文本会追加到现有内容后。
     * <br>
     * Locates element first, then performs sendKeys() operation.
     * Does not clear existing content - appends text to current value.
     * </p>
     *
     * @param by 元素定位器 (不能为null) / Element locator (cannot be null)
     * @param text 需要输入的文本内容 (null会被转换为空字符串) / Text to input (null converts to empty string)
     */
    public void input(By by, String text) {
        String inputText = (text == null) ? "" : text;
        WebElement element = findEle(by);
        element.sendKeys(inputText);
    }

    /**
     * 清除文本 / Clear Text
     *
     * <p>
     * 清除输入框内的文本
     * <br>
     * Clears the text content in an input field element.
     * </p>
     *
     * @param by 元素定位器（不能为null） / Element locator (cannot be null)
     */
    public void clearText(By by) {
        WebElement element = findEle(by);
        element.clear();
    }

    /**
     * 鼠标左键单击元素 / Clicks element with left mouse button
     *
     * <p>
     * 使用显式等待定位元素后执行点击操作。
     * <br>
     * Locates element with explicit wait then performs click operation.
     * </p>
     *
     * @param by 元素定位器 (不能为null) / Element locator (cannot be null)
     */
    public void click(By by) {
        WebElement element = findEle(by);
        element.click();
    }

    /**
     * 切换到指定的frame / Switches to the specified frame
     *
     * <p>
     * 使用frame的ID或name进行切换。
     * <br>
     * Uses frame ID or name for switching.
     * </p>
     *
     * @param frame 目标frame的ID值或name值 / Target frame ID or name
     */
    public void switchToFrame(String frame){
        driver.switchTo().frame(frame);
    }

    /**
     * 从frame切换回默认content / Switches back to default content from frame
     *
     * <p>
     * 将上下文切换回主文档。
     * <br>
     * Switches context back to main document.
     * </p>
     */
    public void switchToDefaultContent(){
        driver.switchTo().defaultContent();
    }

    /**
     * 获取元素的文本内容 / Gets text content of element
     *
     * <p>
     * 返回元素的可见文本，对于隐藏元素返回空字符串。
     * <br>
     * Returns visible text of element, returns empty string for hidden elements.
     * </p>
     *
     * @param by 元素定位器 (不能为null) / Element locator (cannot be null)
     * @return 获取到的文本内容 / Retrieved text content
     */
    public String getText(By by) {
        return findEle(by).getText();
    }

    /**
     * 勾选复选框（如果未勾选） / Checks checkbox (if not already selected)
     *
     * <p>
     * 如果复选框已勾选，则不执行任何操作。
     * <br>
     * Does nothing if checkbox is already selected.
     * </p>
     *
     * @param by 复选框元素定位器 (不能为null) / Checkbox element locator (cannot be null)
     */
    public void selectCheckbox(By by) {
        WebElement element = findEle(by);
        if (!element.isSelected()) {
            element.click();
        }
    }
}