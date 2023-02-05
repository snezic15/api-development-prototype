package com.example.seleniumdemo.pages;

import org.openqa.selenium.By;

public class MainSciPage {
	public static final By caseNumberTab = By.xpath("//*[@id=\"tabbed-nav\"]/ul[2]/li[2]/a");
	public static final By caseType = By.xpath("//*[@id=\"case_type\"]");
	public static final By caseNumber = By.xpath("//*[@id=\"CaseNumber\"]");
	public static final By caseYear = By.xpath("//*[@id=\"CaseYear\"]");

	public static final By captchaText = By.xpath("//*[@id=\"cap\"]/font");
	public static final By captcha = By.xpath("//*[@id=\"ansCaptcha\"]");
	public static final By search = By.xpath("//*[@id=\"getCaseData\"]");
	public static final By error = By.xpath("//*[@id=\"CNdisplay\"]/p/font");

	//Table 1
	public static final By title = By.xpath("//*[@id=\"CNdisplay\"]/h5[2]");
	public static final By caseDetails = By.xpath("//*[@id=\"collapse1\"]/div/table/tbody/tr");

	//Table 2
	public static final By indexing = By.xpath("//*[@id=\"accordion\"]/div[2]/div[1]/h4/a");
	//Table 3
	public static final By earlierCourtDetails = By.xpath("//*[@id=\"accordion\"]/div[3]/div[1]/h4/a");
	//Table 4
	public static final By taggedMatters = By.xpath("//*[@id=\"accordion\"]/div[4]/div[1]/h4/a");
	//Table 5
	public static final By listingDates = By.xpath("//*[@id=\"accordion\"]/div[5]/div[1]/h4/a");
	//Table 6
	public static final By interlocutoryApplicationDocuments = By.xpath("//*[@id=\"accordion\"]/div[6]/div[1]/h4/a");
	//Table 7
	public static final By courtFees = By.xpath("//*[@id=\"accordion\"]/div[7]/div[1]/h4/a");
	public static final By fees = By.xpath("//*[@id=\"result7\"]");
	//Table 8
	public static final By notices = By.xpath("//*[@id=\"accordion\"]/div[8]/div[1]/h4/a");
	//Table 9
	public static final By defects = By.xpath("//*[@id=\"accordion\"]/div[9]/div[1]/h4/a");
	//Table 10
	public static final By judgementOrders = By.xpath("//*[@id=\"accordion\"]/div[10]/div[1]/h4/a");
	//Table 12
	public static final By mentionMemo = By.xpath("//*[@id=\"accordion\"]/div[11]/div[1]/h4/a");
	//Table 13
	public static final By restorationDetails = By.xpath("//*[@id=\"accordion\"]/div[12]/div[1]/h4/a");
	//Table 14
	public static final By dropNote = By.xpath("//*[@id=\"accordion\"]/div[13]/div[1]/h4/a");
	//Table 15
	public static final By appearance = By.xpath("//*[@id=\"accordion\"]/div[14]/div[1]/h4/a");
	//Table 16
	public static final By officeReport = By.xpath("//*[@id=\"accordion\"]/div[15]/div[1]/h4/a");
	//Table 18
	public static final By caveat = By.xpath("//*[@id=\"accordion\"]/div[17]/div[1]/h4/a");
}