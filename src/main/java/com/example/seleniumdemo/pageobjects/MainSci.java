package com.example.seleniumdemo.pageobjects;

import com.example.seleniumdemo.jsonbody.JsonBodyInputCaseNumberSci;
import com.example.seleniumdemo.jsonbody.JsonBodyOutputMainSci;
import com.example.seleniumdemo.pages.MainSciPage;
import com.example.seleniumdemo.utils.WebDriverLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class MainSci {
    WebDriver driver;
    JavascriptExecutor executor;
    WebDriverWait w;

    public MainSci(String browser) throws Exception {
        try {
            driver = WebDriverLibrary.getDriver(browser);
            executor = (JavascriptExecutor) driver;
            driver.get("https://main.sci.gov.in/case-status");

            w = new WebDriverWait(driver, Duration.ofSeconds(4));
        } catch (Exception e) {
            assert driver != null;
            driver.quit();

            throw new Exception(e);
        }
    }

    public void search(JsonBodyInputCaseNumberSci input) throws Exception {
        WebElement we;
        Select dd;

        try {
            we = w.until(ExpectedConditions.elementToBeClickable(MainSciPage.caseNumberTab));
            we.click();
            Thread.sleep(1500);
            executor.executeScript("arguments[0].click();", we);

            we = w.until(ExpectedConditions.elementToBeClickable(MainSciPage.caseType));
            dd = new Select(we);
            dd.selectByValue(input.getCt());

            we = w.until(ExpectedConditions.presenceOfElementLocated(MainSciPage.caseNumber));
            we.sendKeys(input.getCn());

            we = w.until(ExpectedConditions.elementToBeClickable(MainSciPage.caseYear));
            dd = new Select(we);
            dd.selectByValue(input.getCy());

            System.out.println("Trying Captcha");

            we = w.until(ExpectedConditions.presenceOfElementLocated(MainSciPage.captchaText));
            String captchaPath = we.getText().trim();

            System.out.println("Trying [" + captchaPath + "]");

            we = w.until(ExpectedConditions.presenceOfElementLocated(MainSciPage.captcha));
            we.sendKeys(captchaPath);

            we = w.until(ExpectedConditions.presenceOfElementLocated(MainSciPage.search));
            we.click();

            Thread.sleep(1500);

            if (driver.getPageSource().toLowerCase().contains("case not found"))
                throw new Exception("No records found");
        } catch (Exception e) {
            driver.quit();
            e.printStackTrace(System.out);
            throw new Exception("Error has occurred while operating Search Page: " + e);
        }
    }

    public JsonBodyOutputMainSci casePage() throws Exception {
        JsonBodyOutputMainSci jInit = new JsonBodyOutputMainSci();

        try {
            Thread.sleep(4000);
            saveRecordCaseNumber(jInit);
            System.out.println("Record captured successfully");
        } catch (Exception e) {
            driver.quit();
            e.printStackTrace(System.out);
            throw new Exception("Error has occurred while operating Case Page: " + e);
        }

        driver.quit();
        System.out.println("Web Driver has successfully terminated");

        return jInit;
    }

    private void saveRecordCaseNumber(JsonBodyOutputMainSci j) throws InterruptedException, ParseException {
        List<WebElement> lwe;
        WebElement we;
        String xPath;

        //Misc. Hardcoded
        j.setCourtID("SupremeCourt");
        j.setListingDateSource("From Case Status");
        j.setLastUpdated();

        //Misc.
        Select se = new Select(w.until(ExpectedConditions.presenceOfElementLocated(MainSciPage.caseType)));
        we = se.getFirstSelectedOption();
        j.setCaseNoString(we.getText().trim());

        we = w.until(ExpectedConditions.presenceOfElementLocated(MainSciPage.title));
        j.setTitle(we.getText().trim());

        //Table 1
        int t1Size = 0;
        lwe = w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(MainSciPage.caseDetails));
        if (lwe.size() == 12) t1Size = 1;

        we = w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"collapse1\"]/div/table/tbody" +
                "/tr[" + (1) + "]/td[2]")));
        j.setDiaryNo(we.getText().trim());

        we = w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"collapse1\"]/div/table/tbody" +
                "/tr[" + (2) + "]/td[2]")));
        j.setCaseNo(we.getText().trim());

        we = w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"collapse1\"]/div/table/tbody" +
                "/tr[" + (3) + "]/td[2]")));
        if (!we.getText().isEmpty()) {
            LinkedHashMap<String, String> list = new LinkedHashMap<>();
            String[] tt = we.getText().trim().split("\\[")[0].split("-");

            list.put("date", tt[0]);
            list.put("month", tt[1]);

            String d = we.getText().trim().split("\\[")[0];
            j.setListingDate(d);

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date enteredDate = sdf.parse(d);
            Date currentDate = new Date();

            list.put("inFuture", (enteredDate.after(currentDate) ? "true" : "false"));
            j.setListing(list);
        }

        we = w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"collapse1\"]/div/table/tbody" +
                "/tr[" + (4) + "]/td[2]")));
        j.setStatus(we.getText().trim());

        we = w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"collapse1\"]/div/table/tbody" +
                "/tr[" + (4 + t1Size) + "]/td[2]")));
        if (!we.getText().isEmpty()) {
            j.setListingStage(we.getText().trim());

            LinkedHashMap<String, String> ss = new LinkedHashMap<>();
            if (we.getText().toLowerCase().contains("dismissal") || we.getText().toLowerCase().contains("disposed")) {
                ss.put("label", "DISPOSED");
                ss.put("id", "disposed");
            } else {
                ss.put("label", "LISTED");
                ss.put("id", "progress");
            }

            j.setStatusGroup(ss);
        }

        we = w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"collapse1\"]/div/table/tbody" +
                "/tr[" + (5 + t1Size) + "]/td[2]")));
        j.setCategory(we.getText().trim());

        we = w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"collapse1\"]/div/table/tbody" +
                "/tr[" + (6 + t1Size) + "]/td[2]")));
        j.setAct(we.getText().trim());

        String[] pe;
        we = w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"collapse1\"]/div/table/tbody" +
                "/tr[" + (7 + t1Size) + "]/td[2]")));
        if (!we.getText().isEmpty()) {
            pe = we.getText().split("\n {2}");
            j.setPetitioners(pe);
        }

        we = w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"collapse1\"]/div/table/tbody" +
                "/tr[" + (8 + t1Size) + "]/td[2]")));
        if (!we.getText().isEmpty()) {
            pe = we.getText().split("\n {2}");
            j.setRespondents(pe);
        }

        we = w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"collapse1\"]/div/table/tbody" +
                "/tr[" + (9 + t1Size) + "]/td[2]")));
        if (!we.getText().isEmpty()) {
            pe = we.getText().split("\n {2}");
            j.setPadvocates(pe);
        }

        we = w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"collapse1\"]/div/table/tbody" +
                "/tr[" + (10 + t1Size) + "]/td[2]")));
        if (!we.getText().isEmpty()) {
            pe = we.getText().split("\n {2}");
            j.setRadvocates(pe);
        }

        //Table 2
        we = w.until(ExpectedConditions.elementToBeClickable(MainSciPage.indexing));
        we.click();
        Thread.sleep(2500);
        if (!driver.findElements(By.xpath("//*[@id=\"result2\"]/table/tbody/tr[2]/td")).isEmpty()) {
            xPath = "//*[@id=\"result2\"]/table";
            j.setIndexing(getTable(xPath, 1));
        }

        //Table 3
        we = w.until(ExpectedConditions.elementToBeClickable(MainSciPage.earlierCourtDetails));
        we.click();
        Thread.sleep(10000);
        if (!driver.findElements(By.xpath("//*[@id=\"result3\"]/table/tbody/tr[2]/td")).isEmpty()) {
            xPath = "//*[@id=\"result3\"]/table";
            j.setEarlierCourtDetails(getTable(xPath, 1));
        }

        //Table 4
        we = w.until(ExpectedConditions.elementToBeClickable(MainSciPage.taggedMatters));
        we.click();
        Thread.sleep(2500);
        if (!driver.findElements(By.xpath("//*[@id=\"result4\"]/table/tbody/tr[2]/td")).isEmpty()) {
            xPath = "//*[@id=\"result4\"]/table";
            j.setTaggedMatters(getTable(xPath, 1));
        }

        //Table 5
        we = w.until(ExpectedConditions.elementToBeClickable(MainSciPage.listingDates));
        we.click();
        Thread.sleep(2500);
        if (!driver.findElements(By.xpath("//*[@id=\"result5\"]/table/tbody/tr[2]/td")).isEmpty()) {
            xPath = "//*[@id=\"result5\"]/table";
            j.setListingDates(getTable(xPath, 1));
        }

        //Table 6
        we = w.until(ExpectedConditions.elementToBeClickable(MainSciPage.interlocutoryApplicationDocuments));
        we.click();
        Thread.sleep(2500);

        xPath = "//*[@id=\"result6\"]/table";
        j.setIA(getTable(xPath, 2));

        //Table 7
        we = w.until(ExpectedConditions.elementToBeClickable(MainSciPage.courtFees));
        we.click();
        Thread.sleep(2500);

        LinkedHashMap<String, String> temp = new LinkedHashMap<>();
        we = w.until(ExpectedConditions.elementToBeClickable(MainSciPage.fees));
        temp.put("totalCourtFee", we.getText().split("\n")[0].split(":")[1].trim());
        temp.put("courtFeePaid", we.getText().split("\n")[1].split(":")[1].trim());
        j.setCourtFees(temp);

        //Table 8
        we = w.until(ExpectedConditions.elementToBeClickable(MainSciPage.notices));
        we.click();
        Thread.sleep(2500);
        if (!driver.findElements(By.xpath("//*[@id=\"result8\"]/table/tbody/tr[2]/td")).isEmpty()) {
            xPath = "//*[@id=\"result8\"]/table";
            j.setNotices(getTable(xPath, 1));
        }

        //Table 9
        we = w.until(ExpectedConditions.elementToBeClickable(MainSciPage.defects));
        we.click();
        Thread.sleep(2500);
        if (!driver.findElements(By.xpath("//*[@id=\"result9\"]/fieldset/table/tbody/tr[2]/td")).isEmpty()) {
            xPath = "//*[@id=\"result9\"]/fieldset/table";
            j.setDefects(getTable(xPath, 1));
        }

        //Table 10
        we = w.until(ExpectedConditions.elementToBeClickable(MainSciPage.judgementOrders));
        we.click();
        Thread.sleep(11000);

        lwe = driver.findElements(By.xpath("//*[@id=\"result10\"]/table/tbody/tr[2]/td/a"));
        ArrayList<LinkedHashMap<String, String>> orders = new ArrayList<>();
        for (int i = 1; i <= lwe.size(); i++) {
            LinkedHashMap<String, String> order = new LinkedHashMap<>();

            order.put("detail", driver.findElement(By.xpath("//*[@id=\"result10\"]/table/tbody/tr[2]/td")).getText().split("\n")[i - 1].split(" {2}")[1].trim());
            order.put("date", driver.findElement(By.xpath("//*[@id=\"result10\"]/table/tbody/tr[2]/td/a[" + i + "]")).getText().trim());
            order.put("link", driver.findElement(By.xpath("//*[@id=\"result10\"]/table/tbody/tr[2]/td/a[" + i + "]")).getAttribute("href"));

            orders.add(order);
        }
        j.setOrders(orders);

        //Table 12
        we = w.until(ExpectedConditions.elementToBeClickable(MainSciPage.mentionMemo));
        we.click();
        Thread.sleep(2500);
        if (!driver.findElements(By.xpath("//*[@id=\"result12\"]/table/tbody/tr[2]/td")).isEmpty()) {
            xPath = "//*[@id=\"result12\"]/table";
            j.setMemos(getTable(xPath, 1));
        }

        //Table 13
        we = w.until(ExpectedConditions.elementToBeClickable(MainSciPage.restorationDetails));
        we.click();
        Thread.sleep(2500);
        if (!driver.findElements(By.xpath("//*[@id=\"result13\"]/table/tbody/tr[2]/td")).isEmpty()) {
            xPath = "//*[@id=\"result13\"]/table";
            j.setRestoration(getTable(xPath, 1));
        }

        //Table 14
        we = w.until(ExpectedConditions.elementToBeClickable(MainSciPage.dropNote));
        we.click();
        Thread.sleep(2500);
        if (!driver.findElements(By.xpath("//*[@id=\"result14\"]/table/tbody/tr[2]/td")).isEmpty()) {
            xPath = "//*[@id=\"result14\"]/table";
            j.setNotes(getTable(xPath, 1));
        }

        //Table 15
        we = w.until(ExpectedConditions.elementToBeClickable(MainSciPage.appearance));
        we.click();
        Thread.sleep(2500);
        if (!driver.findElements(By.xpath("//*[@id=\"result15\"]/table/tbody/tr[2]/td")).isEmpty()) {
            xPath = "//*[@id=\"result15\"]/table";
            j.setAppearance(getTable(xPath, 1));
        }

        //Table 16
        we = w.until(ExpectedConditions.elementToBeClickable(MainSciPage.officeReport));
        we.click();
        Thread.sleep(2500);
        if (!driver.findElements(By.xpath("//*[@id=\"result16\"]/table/tbody/tr[2]/td")).isEmpty()) {
            xPath = "//*[@id=\"result16\"]/table";
            j.setOfficeReports(getTable(xPath, 1));
        }

        //Skip Table 17

        //Table 18
        we = w.until(ExpectedConditions.elementToBeClickable(MainSciPage.caveat));
        we.click();
        Thread.sleep(2500);
        if (!driver.findElements(By.xpath("//*[@id=\"result18\"]/table/tbody/tr[2]/td")).isEmpty()) {
            xPath = "//*[@id=\"result18\"]/table";
            j.setCaveats(getTable(xPath, 1));
        }
    }

    private ArrayList<LinkedHashMap<String, String>> getTable(String table, int adj) {
        ArrayList<LinkedHashMap<String, String>> map = new ArrayList<>();
        List<WebElement> weListTable = driver.findElements(By.xpath(table));

        for (int y = 1; y <= weListTable.size(); y++) {
            String xPath = table + "[" + y + "]/tbody/tr";
            List<WebElement> weListRow = driver.findElements(By.xpath(xPath));
            List<WebElement> weListColumn;

            String head = xPath + "[" + adj + "]/td";
            if (driver.findElements(By.xpath(head)).isEmpty()) head = xPath + "[" + adj + "]/th";
            weListColumn = driver.findElements(By.xpath(head));

            WebElement weHead;
            WebElement weVal;

            for (int i = adj; i < weListRow.size(); i++) {
                LinkedHashMap<String, String> row = new LinkedHashMap<>();
                for (int x = 0; x < weListColumn.size(); x++) {
                    weHead = w.until(ExpectedConditions.presenceOfElementLocated(By.xpath(head + "[" + (x + 1) + "]")));
                    weVal = w.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath + "[" + (adj + 1) + "]/td[" + (x + 1) + "]")));

                    String[] temp = weHead.getText().trim().toLowerCase().split(" ");
                    StringBuilder weHeadFin = new StringBuilder(temp[0]);
                    for (int z = 1; z < temp.length; z++)
                        weHeadFin.append(temp[z].substring(0, 1).toUpperCase()).append(temp[z].substring(1));

                    row.put(weHeadFin.toString(), weVal.getText().trim());
                }

                map.add(row);
            }
        }

        return map;
    }
}