package com.example.seleniumdemo.pageobjects;

import com.example.seleniumdemo.jsonbody.JsonBodyInputCaseNumber;
import com.example.seleniumdemo.jsonbody.JsonBodyInputConfonet;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.CookieStore;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlternativeRequest {
    private Connection conn;
    private String cookie;

    public String executeCaseNumber(JsonBodyInputCaseNumber j) throws Exception {
        HttpURLConnection http;
        URL url = new URL(mFormatURLCaseNumber(j));

        http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.setDoOutput(true);
        http.setRequestProperty("Cookie", cookie);

        BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
        String inputLine;
        StringBuilder ret = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            ret.append(inputLine);
        in.close();

        System.out.println("JSON Response Retrieved!");
        return ret.toString();
    }

    private String mFormatURLCaseNumber(JsonBodyInputCaseNumber j) throws Exception {
        HttpURLConnection http;
        URL url = new URL("https://mercury.lawyer/api/login");

        http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "*/*");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "";
        byte[] out = data.getBytes(StandardCharsets.UTF_8);
        OutputStream stream = http.getOutputStream();
        stream.write(out);

        List<String> cookies = http.getHeaderFields().get("Set-Cookie");
        if (cookies != null) {
            for (String c : cookies) {
                cookie = c.split(";")[0];
            }
        }

        System.out.println("Cookie Retrieved!");

        sqlConnect();
        j.setState_val(query("M", "State", j.getState_val()));
        j.setDistrict_val(query("M", "District", j.getDistrict_val()));
        j.setBench_val(query("M", "Bench", j.getBench_val()));

        String mFormat = "https://mercury.lawyer/api/caseStatus?courtID=XCOURTIDX&state_val=XSTATEX&district_val" +
                "=XDISTRICTX&courtComplexEstb=XCOURTX&ct=XCTX&cn=XCNX&cy=XCYX&bench_val=XBENCHX";
        return mFormat.replace("XSTATEX", j.getState_val()).replace("XDISTRICTX", j.getDistrict_val()).replace(
                "XCOURTX", j.getCourtComplexEstb()).replace("XCTX", j.getCt()).replace("XCNX", j.getCn()).replace(
                "XCYX", j.getCy()).replace("XBENCHX", j.getBench_val()).replace("XCOURTIDX", j.getCourtId());
    }

    public String executeConfonet(JsonBodyInputConfonet j) throws Exception {
        HttpURLConnection http;
        URL url = new URL((j.getApi().equalsIgnoreCase("m") ? mFormatURLConfonet(j) : lFormatURLConfonet(j)));

        http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.setDoOutput(true);
        http.setRequestProperty("Cookie", cookie);

        BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
        String inputLine;
        StringBuilder ret = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            ret.append(inputLine);
        in.close();

        System.out.println("JSON Response Retrieved!");
        return ret.toString();
    }

    private String mFormatURLConfonet(JsonBodyInputConfonet j) throws Exception {
        HttpURLConnection http;
        URL url = new URL("https://mercury.lawyer/api/login");

        http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "*/*");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "";
        byte[] out = data.getBytes(StandardCharsets.UTF_8);
        OutputStream stream = http.getOutputStream();
        stream.write(out);

        List<String> cookies = http.getHeaderFields().get("Set-Cookie");
        if (cookies != null) {
            for (String c : cookies) {
                cookie = c.split(";")[0];
            }
        }

        System.out.println("Cookie Retrieved!");

        sqlConnect();
        j.setBench_val(query("M", "Bench_Confonet", j.getBench_val()));

        String mFormat = "https://mercury.lawyer/api/caseStatus?courtID=NCDRC%2A&bench_val=XBENCHX&ct=XCTX&cn=XCNX&cy=XCYX";
        return mFormat.replace("XBENCHX", j.getBench_val()).replace("XCTX", j.getCt())
                .replace("XCNX", j.getCn()).replace("XCYX", j.getCy());
    }

    private String lFormatURLConfonet(JsonBodyInputConfonet j) throws Exception {
        List<NameValuePair> urlParameters = new ArrayList<>();

        urlParameters.add(new BasicNameValuePair("username", ""));
        urlParameters.add(new BasicNameValuePair("password", ""));

        HttpClient http;
        CookieStore httpCookieStore = new BasicCookieStore();
        HttpClientBuilder builder = HttpClientBuilder.create().setDefaultCookieStore(httpCookieStore);
        http = builder.build();

        HttpPost request = new HttpPost("https://legaldiary.in/login");
        request.setEntity(new UrlEncodedFormEntity(urlParameters));
        http.execute(request);
        httpCookieStore.getCookies();

        cookie = "session=" + httpCookieStore.getCookies().get(0).getValue();
        System.out.println("Cookie Retrieved!");

        sqlConnect();
        j.setStateCode(query("L", "State", j.getStateCode()));
        j.setDistCode(query("L", "District", j.getDistCode()));

        String lFormat = "https://legaldiary.in/fetch_case_data?casenumber=XCNX&court_type=consumer-forum&court" +
                "=district-forum&statecode=XSTATEX&distcode=XDISTRICTX";
        return lFormat.replace("XCNX", j.getCt() + "%2F" + j.getCn() + "%2F" + j.getCy())
                .replace("XSTATEX", j.getStateCode()).replace("XDISTRICTX", j.getDistCode());
    }

    public String executeCino(String type, String cino) throws IOException {
        HttpURLConnection http;
        URL url = new URL((type.equalsIgnoreCase("m") ? mFormatURLCino(cino) : lFormatURLCino(cino)));

        http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.setDoOutput(true);
        http.setRequestProperty("Cookie", cookie);

        BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
        String inputLine;
        StringBuilder ret = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            ret.append(inputLine);
        in.close();

        System.out.println("JSON Response Retrieved!");
        return (type.equalsIgnoreCase("l") ? lFormatUpdate(ret.toString()) : ret.toString());
    }

    private String mFormatURLCino(String cino) throws IOException {
        HttpURLConnection http;
        URL url = new URL("https://mercury.lawyer/api/login");

        http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "*/*");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "";
        byte[] out = data.getBytes(StandardCharsets.UTF_8);
        OutputStream stream = http.getOutputStream();
        stream.write(out);

        List<String> cookies = http.getHeaderFields().get("Set-Cookie");
        if (cookies != null) {
            for (String c : cookies) {
                cookie = c.split(";")[0];
            }
        }

        System.out.println("Cookie Retrieved!");

        String mFormat = "https://mercury.lawyer/api/caseStatus?courtID=DistrictCourt_CNR&cino=XXXXXX";
        return mFormat.replace("XXXXXX", cino);
    }

    private String lFormatURLCino(String cino) throws IOException {
        List<NameValuePair> urlParameters = new ArrayList<>();

        urlParameters.add(new BasicNameValuePair("username", ""));
        urlParameters.add(new BasicNameValuePair("password", ""));

        HttpClient http;
        CookieStore httpCookieStore = new BasicCookieStore();
        HttpClientBuilder builder = HttpClientBuilder.create().setDefaultCookieStore(httpCookieStore);
        http = builder.build();

        HttpPost request = new HttpPost("https://legaldiary.in/login");
        request.setEntity(new UrlEncodedFormEntity(urlParameters));
        http.execute(request);
        httpCookieStore.getCookies();

        cookie = "session=" + httpCookieStore.getCookies().get(0).getValue();
        System.out.println("Cookie Retrieved!");

        String lFormat = "https://legaldiary.in/fetch_case_data?cnr=XXXXXX&court=district-court&use_eci=true";
        return lFormat.replace("XXXXXX", cino);
    }

    private String lFormatUpdate(String ret) {
        //Initial replacing
        ret = ret.replace("\"actsAndSections\"", "\"act\"").replace("{\"act\":\"Under Act(s)\",\"sections\":\"Under " +
                "Section(s)\"},", "").replace("\"casehistory\"", "\"listingDates\"").replace("\"timestamp\"",
                "\"lastUpdated\"").replace("\"path\"", "\"cino\"").replace("\"court\"", "\"courtName\"").replace(
                "\"court_number_and_judge\"", "\"caseNoString\"").replace("\"first_hearing_date\"",
                "\"listingDate\"").replace("\"case_stage\"", "\"listingStage\"").replace("\"next_hearing_date\"",
                "\"nextHearingDate\"").replace("\"transferHistory\"", "\"history\"").replace("\"type\"",
                "\"caseTypeStr\"");

        //Updating nested objects
        JSONObject json = new JSONObject(ret);

        //'status' nested object
        if (json.getJSONObject("status") != null && !json.getJSONObject("status").isEmpty()) {
            json.put("listingDate", json.getJSONObject("status").get("listingDate"));
            json.put("caseNoString", json.getJSONObject("status").get("caseNoString"));
            json.put("listingStage", json.getJSONObject("status").get("listingStage"));
            json.put("nextHearingDate", json.getJSONObject("status").get("nextHearingDate"));
            json.remove("status");
        }

        //'details' nested object
        if (json.getJSONObject("details") != null && !json.getJSONObject("details").isEmpty()) {
            json.put("file_no", json.getJSONObject("details").getJSONObject("filing").get("number"));
            json.put("file_year", json.getJSONObject("details").getJSONObject("filing").get("date"));
            json.put("reg_no", json.getJSONObject("details").getJSONObject("registration").get("number"));
            json.put("reg_year", json.getJSONObject("details").getJSONObject("registration").get("date"));
            json.put("caseTypeStr", json.getJSONObject("details").get("caseTypeStr"));
            json.remove("details");
        }

        //'act' nested object
        if (json.getJSONArray("act") != null && !json.getJSONArray("act").isEmpty()) {
            json.put("section", json.getJSONArray("act").getJSONObject(0).get("sections"));
            json.put("act", json.getJSONArray("act").getJSONObject(0).get("act"));
        }

        return json.toString();
    }


    private String lFormatURLDataFetch(String state_val) throws Exception {
        List<NameValuePair> urlParameters = new ArrayList<>();

        urlParameters.add(new BasicNameValuePair("username", ""));
        urlParameters.add(new BasicNameValuePair("password", ""));

        HttpClient http;
        CookieStore httpCookieStore = new BasicCookieStore();
        HttpClientBuilder builder = HttpClientBuilder.create().setDefaultCookieStore(httpCookieStore);
        http = builder.build();

        HttpPost request = new HttpPost("https://legaldiary.in/login");
        request.setEntity(new UrlEncodedFormEntity(urlParameters));
        http.execute(request);
        httpCookieStore.getCookies();

        cookie = "session=" + httpCookieStore.getCookies().get(0).getValue();

        System.out.println("Cookie Retrieved! " + cookie);

        String lFormat = "https://legaldiary.in/get_consumer_districts?stateId=XXXXXX";
        return lFormat.replace("XXXXXX", state_val);
    }

    private String mFormatURLDataFetch(String state_val, String district_val, String bench_val) throws Exception {
        HttpURLConnection http;
        URL url = new URL("https://mercury.lawyer/api/login");

        http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "*/*");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "";
        byte[] out = data.getBytes(StandardCharsets.UTF_8);
        OutputStream stream = http.getOutputStream();
        stream.write(out);

        List<String> cookies = http.getHeaderFields().get("Set-Cookie");
        if (cookies != null) {
            for (String c : cookies) {
                cookie = c.split(";")[0];
            }
        }

        System.out.println("Cookie Retrieved!");

        String mFormat = "https://mercury.lawyer/api/getCaseTypesList?courtID=DistrictCourt&state_val=YYYYYY&district_val=XXXXXX&bench_val=ZZZZZZ";
        return mFormat.replace("YYYYYY", state_val).replace("XXXXXX", district_val).replace("ZZZZZZ", bench_val);
    }

    public String executeDataFetch(String type, String state_val, String district_val, String bench_val) throws Exception {
        HttpURLConnection http;
        URL url = new URL((type.equalsIgnoreCase("m") ? mFormatURLDataFetch(state_val, district_val, bench_val) : lFormatURLDataFetch(state_val)));

        http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.setDoOutput(true);
        http.setRequestProperty("Cookie", cookie);

        BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
        String inputLine;
        StringBuilder ret = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            ret.append(inputLine);
        in.close();

        System.out.println("JSON Response Retrieved for Bench!");
        //return (type.equalsIgnoreCase("l") ? lFormatUpdate(ret.toString()) : ret.toString());
        return ret.toString();
    }

    private void sqlConnect() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("",
                    "", "");
        } catch (Exception e) {
            e.printStackTrace(System.out);
            throw new Exception("SQL connection has failed: " + e);
        }
    }

    private String query(String select, String table, String value) throws Exception {
        try {
            String query = "SELECT " + select + " FROM " + table + " WHERE UPPER(Name) LIKE UPPER('" + value + "') LIMIT 1;";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            rs.next();
            return rs.getString(1);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            throw new Exception("SQL value retrieval has failed: " + e);
        }
    }
}
