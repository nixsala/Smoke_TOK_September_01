
package com.test.qa.utils;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import utils.ExcelDataConfig;

public class TestData extends ExcelDataConfig {

    private static final Logger LOGGER = Logger.getLogger(TestData.class);

    public TestData() {
        super("src\\test\\resources\\ExcelSheet\\testconfigure.xlsx");
    }
    @DataProvider (name = "TestConfig")
    public Object[][] TestConfig() {

        int rows = getRowCount("configdata");
        int col = getColumnCount("configdata");


        LOGGER.info("row = "+ rows + " columns = "+ col);
        System.out.println("row = "+ rows + " columns = "+ col);
        Object[][] data = new Object[rows][col];
        for (int i = 1; i <= rows; i++) {
            for (int j = 0; j < col; j++) {
                data[i - 1][j] = getData("configdata", i, j);
            }
        }
        return data;
    }

}
