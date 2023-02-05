package com.example.seleniumdemo.pageobjects;

import com.example.seleniumdemo.utils.WebDriverLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CaptureValues {
    WebDriver driver;
    WebDriverWait w;

    public CaptureValues(String browser, String url) throws Exception {
        try {
            driver = WebDriverLibrary.getDriver(browser);
            driver.get(url);

            w = new WebDriverWait(driver, Duration.ofSeconds(30));
            System.out.println("Page Loaded!");
        } catch (Exception e) {
            assert driver != null;
            driver.quit();

            throw new Exception(e);
        }
    }

    public ArrayList<Map<String, String>> executeStateDistrict() throws Exception {
        List<WebElement> lwe;
        WebElement we;
        ArrayList<Map<String, String>> ret = new ArrayList<>();

        try {
            w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"block-views-india-block\"]/div/div/div/ul/li")));
            lwe = driver.findElements(By.xpath("//*[@id=\"block-views-india-block\"]/div/div/div/ul/li"));

            for (int i = 1; i <= lwe.size(); i++) {
                String state;
                we = driver.findElement(By.xpath("//*[@id=\"block-views-india-block\"]/div/div/div/ul/li[" + i +
                        "]/a"));

                state = we.getText().trim();
                System.out.println("State: " + state + " (" + i + ")");
                we.click();

                List<WebElement> lwe2;
                if (!state.equalsIgnoreCase("West Bengal")) {
                    w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"map-position-list\"]/div/div/div[2]/div/div/div/ul/li")));
                    lwe2 = driver.findElements(By.xpath("//*[@id=\"map-position-list\"]/div/div/div[2]/div/div/div/ul/li"));
                } else {
                    w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"map-position-list\"]/div/div/div[3]/div/div/div/ul/li")));
                    lwe2 = driver.findElements(By.xpath("//*[@id=\"map-position-list\"]/div/div/div[3]/div/div/div/ul/li"));
                }

                for (int x = 1; x <= lwe2.size(); x++) {
                    Map<String, String> temp = new LinkedHashMap<>();
                    String district;

                    if (!state.equalsIgnoreCase("West Bengal"))
                        we = driver.findElement(By.xpath("//*[@id=\"map-position-list\"]/div/div/div[2]/div/div/div/ul/li[" + x +
                                "]/a"));
                    else
                        we = driver.findElement(By.xpath("//*[@id=\"map-position-list\"]/div/div/div[3]/div/div/div/ul/li[" + x +
                                "]/a"));

                    district = we.getText().trim();
                    System.out.println("District: " + district + " (" + x + ")");

                    temp.put("State", state);
                    temp.put("State Code", String.valueOf(i));
                    temp.put("District", district);
                    temp.put("District Code", String.valueOf(x));

                    ret.add(temp);
                }

                System.out.println("State [" + state + "] Completed!");
                driver.navigate().back();
            }

            System.out.println("All States/Districts Captured!");
        } catch (
                Exception e) {
            driver.quit();
            e.printStackTrace(System.out);
            throw new Exception("Error has occurred: " + e);
        }

        return ret;
    }

    public ArrayList<Map<String, String>> executeBenchVal() throws Exception {
        List<WebElement> lwe;
        WebElement we;
        ArrayList<Map<String, String>> ret = new ArrayList<>();

        try {
            we = w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"radCourtComplex\"]")));
            we.click();

            we = w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"court_complex_code\"]")));
            Select s = new Select(we);
            lwe = s.getOptions();

            System.out.println("Capturing Bench Values for Court Complex");

            for (int i = 1; i < lwe.size(); i++) {
                Map<String, String> temp = new LinkedHashMap<>();

                temp.put("Court Complex/Establishment", "0");
                temp.put("Bench Name", lwe.get(i).getText());
                temp.put("Bench Value", lwe.get(i).getAttribute("value"));

                ret.add(temp);
            }

            System.out.println("Court Complex Bench Values Captured!");

            we = w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"radCourtEst\"]")));
            we.click();

            we = w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"court_code\"]")));
            s = new Select(we);
            lwe = s.getOptions();

            System.out.println("Capturing Bench Values for Court Establishment");

            for (int i = 1; i < lwe.size(); i++) {
                Map<String, String> temp = new LinkedHashMap<>();

                temp.put("Court Complex/Establishment", "1");
                temp.put("Bench Name", lwe.get(i).getText());
                temp.put("Bench Value", lwe.get(i).getAttribute("value"));

                ret.add(temp);
            }

            System.out.println("Court Establishment Bench Values Captured!");
            System.out.println("All Bench Values Captured!");
        } catch (Exception e) {
            driver.quit();
            e.printStackTrace(System.out);
            throw new Exception("Error has occurred: " + e);
        }

        return ret;
    }
}