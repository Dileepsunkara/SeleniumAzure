package com.galenframework.java.sample.tests;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UniqueIds {
	public static WebDriver driver;

	public static void main(String args[]) throws InterruptedException {

		try {

/*			System.setProperty("webdriver.chrome.driver", "C://Driver/chromedriver.exe");
			driver = new ChromeDriver();
			driver.get("http://biotrueonedaylenses.q.evoq-stg-i.com/");
			driver.findElement(By.id("dnn_ctr_Login_Login_DNN_txtUsername")).sendKeys("3itestuser");
			driver.findElement(By.id("dnn_ctr_Login_Login_DNN_txtPassword")).sendKeys("test@123");
			driver.findElement(By.id("dnn_ctr_Login_Login_DNN_cmdLogin")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);*/
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\1001406\\workspace\\galen-java-sample-tests\\galen-java-sample-tests\\ExcelData\\UniqueID-in.xls");
			FileOutputStream fos = new FileOutputStream(
					"C:\\Users\\1001406\\workspace\\galen-java-sample-tests\\galen-java-sample-tests\\ExcelData\\UniqueID-out.xls");
			HSSFWorkbook wb = new HSSFWorkbook(fis);
			HSSFSheet sheet = wb.getSheet("testdata-biotrue-dtc");
			System.setProperty("webdriver.chrome.driver", "C://Driver/chromedriver.exe");

			driver = new ChromeDriver();

			for (int count = 1; count <= sheet.getLastRowNum(); count++) {
				HSSFRow row = sheet.getRow(count);
				System.out.println("Running test case " + row.getCell(0));

				if (row.getCell(1).toString().length()!= 0) {

					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					driver.get(row.getCell(1).toString());
					//driver.get(row.getCell(1).toString());
					if(driver.findElement(By.id("dnn_ctr_Login_Login_DNN_txtUsername")).isDisplayed()) {
					driver.findElement(By.id("dnn_ctr_Login_Login_DNN_txtUsername")).sendKeys("3itestuser");
					driver.findElement(By.id("dnn_ctr_Login_Login_DNN_txtPassword")).sendKeys("test@123");
					driver.findElement(By.id("dnn_ctr_Login_Login_DNN_cmdLogin")).click();
					}
					else {
					driver.get(row.getCell(1).toString());
					System.out.println(driver.findElements(By.id(row.getCell(2).toString())).size());
					int result = driver.findElements(By.id(row.getCell(2).toString())).size();
					System.out.println(result);
					row.createCell(3).setCellValue(result);
					}
				} else {
					int result = driver.findElements(By.id(row.getCell(2).toString())).size();
					System.out.println(result);
					row.createCell(3).setCellValue(result);
				}

			}

			wb.write(fos);
			driver.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String checkUniqueIds(String id) throws InterruptedException {

		int result = 0 ;

		try {
			if (result!=0) {

				System.out.println("Unique ID " + id + ", found");
				return "OK";

			} else {
				System.out.println("Unique ID " + id + ", NOT FOUND");
				return "NOT OK";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ok";
	}

}
