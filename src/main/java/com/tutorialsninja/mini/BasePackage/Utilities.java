package com.tutorialsninja.mini.BasePackage;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class Utilities {
        public static Object[][] getTestDataFromTestExcelWithSheetName(String sheetname) throws Exception {
            Object testData[][]= null;
            XSSFWorkbook workbook;
            XSSFSheet sheet;
            try{
                File testFile = new File("D:\\TutorialsNinjaMavenMiniProject\\src\\main\\java\\com\\tutorialsninja\\mini\\ConfigurationPackage\\TutorialsNinjaTestData.xlsx");
                FileInputStream fis = new FileInputStream(testFile);
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheet(sheetname);
                int rowNum = sheet.getLastRowNum();
                int colNum = sheet.getRow(rowNum).getLastCellNum();
                testData = new Object[rowNum][colNum];
                for(int i=0; i<rowNum; i++){
                    XSSFRow row = sheet.getRow(i+1);
                    for(int j=0; j<colNum; j++){
                        XSSFCell cell = row.getCell(j);
                        CellType cellType = cell.getCellType();
                        switch (cellType) {
                            case STRING:
                                testData[i][j] = cell.getStringCellValue();
                                break;
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    testData[i][j] = cell.getDateCellValue();
                                    break;
                                } else {
                                    double numericValue = cell.getNumericCellValue();
                                   if (numericValue >= 1000000000 && numericValue <= 9999999999L) {
                                        testData[i][j] = (long) numericValue;
                                    } else {
                                       testData[i][j] = Double.toString(numericValue);
                                    }
                                    break;
                                }


                        }
                    }

                }
            }
            catch(Throwable ex){
                ex.printStackTrace();
                throw ex;
            }
            return testData;
        }

        public static Properties getProperties() throws Exception {
            File propertyFile = new File("src/main/java/ConfigurationPackage/TutorialsNinjaTestData.xlsx");
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream(propertyFile);
            prop.load(fis);

            return prop;
        }

        public static String captureRobotScreenshot(WebDriver driver, String testName) throws Exception {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String formattedDateTime = currentDateTime.format(formatter);

            String screenshotPath = "TestOutputs/Screenshots"+testName+"_"+formattedDateTime+".png";

            File screenshotFile = new File(screenshotPath);
            Robot robo = new Robot();
            Dimension d= Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle r = new Rectangle(d);
            BufferedImage image = robo.createScreenCapture(r);
            try {
                ImageIO.write(image, "PNG", screenshotFile);
            } catch (IOException e) {
                e.printStackTrace();

            }
            return screenshotPath;
        }
        public static String captureScreenshot(WebDriver driver, String testName){

            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss.SSSSSSSSS");
            String formattedDateTime = currentDateTime.format(formatter);

            String screenshotPath = System.getProperty("user.dir")+"\\test-outputs\\Screenshots\\"+testName+"_"+formattedDateTime+testName+".PNG";

            File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                FileHandler.copy(screenshotFile, new File(screenshotPath));
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }

            return screenshotPath;
        }
}
