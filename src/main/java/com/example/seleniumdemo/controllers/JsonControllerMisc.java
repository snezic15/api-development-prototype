package com.example.seleniumdemo.controllers;

import com.example.seleniumdemo.ErrorHandling;
import com.example.seleniumdemo.jsonbody.*;
import com.example.seleniumdemo.pageobjects.AlternativeRequest;
import com.example.seleniumdemo.pageobjects.DistrictCourt;
import com.example.seleniumdemo.pageobjects.CaptureValues;
import com.example.seleniumdemo.pageobjects.eCourt;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class JsonControllerMisc {
    @PostMapping("/advocateNameBasic")
    @ResponseBody
    public ArrayList<Map<String, Object>> advocateName(@Valid @RequestBody JsonBodyInputAdvocateName j) {
        ArrayList<Map<String, Object>> ret = new ArrayList<>();
        String input = new Gson().toJson(j);

        try {
            DistrictCourt d = new DistrictCourt("chrome");
            int viewTabsNo = d.advocateName(j);

            for (int i = 0; i < viewTabsNo && i < j.getRecordsReturned(); i++) {
                try {
                    LinkedHashMap<String, String> out = d.casePageAdvocateBasic(i);
                    LinkedHashMap<String, String> temp = new LinkedHashMap<>();
                    JsonBodyOutputToCaseNumber jo = new JsonBodyOutputToCaseNumber();

                    temp.put("state_val", j.getState_val());
                    temp.put("district_val", j.getDistrict_val());
                    temp.put("courtComplexEstb", j.getCourtComplexEstb());
                    temp.put("bench_val", j.getBench_val());
                    temp.put("ct", out.get("CT"));
                    temp.put("cn", out.get("CN"));
                    temp.put("cy", out.get("CY"));
                    temp.put("recordsReturned", "1");
                    jo.setIndex(out.get("Index"));
                    jo.setTitle(out.get("Title"));
                    jo.setCourt(out.get("Court"));
                    jo.setCaseNumber(temp);
                    jo.setLastUpdated();

                    String t = new Gson().toJson(jo);
                    ret.add(new JSONObject(t).toMap());
                } catch (Exception e) {
                    System.out.println("Index [" + i + "] has failed: " + e);
                    ret.add(new JSONObject(new ErrorHandling(i, new JSONObject(input).toMap(), e)).toMap());
                }
            }

            d.driverQuit();
        } catch (Exception e) {
            e.printStackTrace();
            ret.add(new JSONObject(new ErrorHandling(0, new JSONObject(input).toMap(), e)).toMap());
        }

        return ret;
    }

    @PostMapping("/cnrNumberBasic")
    @ResponseBody
    public Map<String, Object> cnrNumber(@Valid @RequestBody JsonBodyInputCNRNumber j) {
        Map<String, Object> ret;
        String input = new Gson().toJson(j);

        try {
            eCourt ec = new eCourt("chrome");

            ec.search(j);
            JsonBodyOutputToCaseNumber out = ec.casePageBasic();

            String t = new Gson().toJson(out);
            ret = new JSONObject(t).toMap();
        } catch (Exception e) {
            e.printStackTrace();
            ret = new JSONObject(new ErrorHandling(0, new JSONObject(input).toMap(), e)).toMap();
        }

        return ret;
    }

    @PostMapping("/confonet")
    @ResponseBody
    public Map<String, Object> confonet(@Valid @RequestBody JsonBodyInputConfonet j) {
        Map<String, Object> ret;
        String input = new Gson().toJson(j);

        try {
            AlternativeRequest ar = new AlternativeRequest();
            ret = new JSONObject(ar.executeConfonet(j)).toMap();
        } catch (Exception e) {
            e.printStackTrace();
            ret = new JSONObject(new ErrorHandling(0, new JSONObject(input).toMap(), e)).toMap();
        }

        return ret;
    }

    @PostMapping("/districtStateVals")
    @ResponseBody
    public ArrayList<Map<String, String>> districtStateVals() {
        ArrayList<Map<String, String>> ret = new ArrayList<>();

        try {
            CaptureValues sd = new CaptureValues("chrome", "https://districts.ecourts.gov.in/");
            ret = sd.executeStateDistrict();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    @PostMapping("/benchVals")
    @ResponseBody
    public ArrayList<Map<String, String>> benchVals() {
        ArrayList<Map<String, String>> ret = new ArrayList<>();

        try {
            CaptureValues sd = new CaptureValues("chrome", "https://services.ecourts.gov.in/ecourtindia_v4_bilingual/cases/case_no.php?state=D&state_cd=1&dist_cd=9");
            ret = sd.executeBenchVal();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    @PostMapping("/test")
    @ResponseBody
    public void test() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver") ;
            Connection conn = DriverManager.getConnection("jdbc:mysql://hp322.servername.online:3306/fresh231_courts",
                    "fresh231_test", "Sydney@389") ;
            Statement stmt = conn.createStatement() ;
            //String query = "select columnname from tablename ;" ;
            String query = "select `Name` from `Bench` ;" ;
            ResultSet rs = stmt.executeQuery(query) ;
            while(rs.next())
            {

                System.out.println(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}