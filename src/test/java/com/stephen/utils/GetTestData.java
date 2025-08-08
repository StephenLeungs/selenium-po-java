package com.stephen.utils;

import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import java.io.File;

public class GetTestData {
    public static final Logger LOGGER = LoggerFactory.getLogger("GetTestData.class");

    public static Object[][] getDataFromExcel(String excelPath, String sheet) {
        try (Workbook workbook = WorkbookFactory.create(new File(excelPath))) {
            Sheet dataSheet = workbook.getSheet(sheet);
            if (dataSheet == null) return new Object[0][0];

            // 获取实际数据行数（跳过标题行）
            int startDataRowIndex = 1; // 数据从第二行开始（索引1）
            int lastRowIndex = dataSheet.getLastRowNum(); // 最后一行索引
            int dataRowCount = lastRowIndex - startDataRowIndex + 1; // 实际数据行数

            // 当没有数据行时直接返回空数组
            if (dataRowCount <= 0) return new Object[0][0];

            // 获取列数（基于标题行）
            Row titleRow = dataSheet.getRow(0); // 第一行是标题
            int columnCount = titleRow.getLastCellNum(); // 列数量

            // 初始化二维数组 [数据行数][列数]
            Object[][] datas = new Object[dataRowCount][columnCount];

            DataFormatter dataFormatter = new DataFormatter();

            // 遍历数据行（从索引1开始）
            for (int i = startDataRowIndex; i <= lastRowIndex; i++) {
                Row dataRow = dataSheet.getRow(i);
                if (dataRow == null) continue;

                // 遍历所有列
                for (int j = 0; j < columnCount; j++) {
                    Cell cell = dataRow.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String value = dataFormatter.formatCellValue(cell);
                    datas[i - startDataRowIndex][j] = value; // 计算数组行索引
                }
            }
            return datas;
        } catch (Exception e) {
            LOGGER.error("获取测试数据异常/Get Testdata Error", e);//发生异常时记录日志信息
            return new Object[0][0]; // 返回空数组避免NullPointerException
        }
    }

    //获取注册测试数据的DataProvider方法
    @DataProvider(name = "RegisterData")
    public static  Object[][] getRegisterData(){
        return getDataFromExcel("src/test/resources/TestData.xls",  "RegisterData");
    }

    //获取登录测试数据的DataProvider方法
    @DataProvider(name = "LoginData")
    public static  Object[][] getLoginData(){
        return getDataFromExcel("src/test/resources/TestData.xls",  "LoginData");
    }

    //获取登录态检查的测试数据的DataProvider方法
    @DataProvider(name = "LoginCookieCheckData")
    public static  Object[][] getLoginCookieCheckData(){
        return getDataFromExcel("src/test/resources/TestData.xls",  "LoginCookieCheckData");
    }




}
