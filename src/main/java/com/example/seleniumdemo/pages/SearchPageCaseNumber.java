package com.example.seleniumdemo.pages;

import org.openqa.selenium.By;

public class SearchPageCaseNumber {
    public static final By courtComplex = By.xpath("//*[@id=\"radCourtComplex\"]");
    public static final By courtEstablishment = By.xpath("//*[@id=\"radCourtEst\"]");
    public static final By complexStatus = By.xpath("//*[@id=\"court_complex_code\"]");
    public static final By establishmentStatus = By.xpath("//*[@id=\"court_code\"]");
    public static final By caseType = By.xpath(".//select[@name='case_type']");
    public static final By caseNoYear = By.xpath("//*[@id='rgyear']");
    public static final By caseNo = By.xpath("//*[@id=\"search_case_no\"]");

    public static final By captcha = By.xpath("//*[@id=\"captcha_image\"]");
    public static final By captchaText = By.xpath("//*[@id=\"captcha\"]");

    public static final By search = By.xpath("//*[@id=\"caseNoDet\"]/div[8]/span[3]/input[1]");
    public static final By viewTab = By.xpath("//*[@id=\"showList1\"]/tr");
    public static final By error = By.xpath("//*[@id=\"errSpan\"]/p");
}