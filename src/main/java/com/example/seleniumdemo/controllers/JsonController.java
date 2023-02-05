package com.example.seleniumdemo.controllers;

import com.example.seleniumdemo.ErrorHandling;
import com.example.seleniumdemo.jsonbody.*;
import com.example.seleniumdemo.pageobjects.AlternativeRequest;
import com.example.seleniumdemo.pageobjects.DistrictCourt;
import com.example.seleniumdemo.pageobjects.MainSci;
import com.example.seleniumdemo.pageobjects.eCourt;
import com.example.seleniumdemo.utils.ExcelUtil;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;


@Controller
public class JsonController {
    @PostMapping("/caseNumber")
    @ResponseBody
    public ArrayList<Map<String, Object>> caseNumber(@Valid @RequestBody JsonBodyInputCaseNumber j) {
        ArrayList<Map<String, Object>> ret = new ArrayList<>();
        String input = new Gson().toJson(j);

        try {
            if (j.getApi().equalsIgnoreCase("m")) {
                AlternativeRequest ar = new AlternativeRequest();
                String t = ar.executeCaseNumber(j);
                ret.add(new JSONObject(t).toMap());
            } else {
                DistrictCourt d = new DistrictCourt("chrome");
                int viewTabsNo = d.caseNumber(j);
                int index = 0;

                for (int i = 0; i < viewTabsNo && index < j.getRecordsReturned(); i++) {
                    try {
                        String t;

                        if (j.getFormat().equalsIgnoreCase("0")) {
                            JsonBodyOutput temp = d.casePage(i, j.getUpdate().equalsIgnoreCase("y"));
                            temp.setState_val(j.getState_val());
                            temp.setDistrict_val(j.getDistrict_val());
                            temp.setBench_val(j.getBench_val());
                            t = new Gson().toJson(temp);
                        } else {
                            JsonBodyOutputAlt temp = d.casePageAlt(i, j.getUpdate().equalsIgnoreCase("y"));
                            temp.setInputs(new JSONObject(input).toMap());
                            t = new Gson().toJson(temp);
                        }

                        ret.add(new JSONObject(t).toMap());
                        index++;
                    } catch (Exception e) {
                        if (!e.getMessage().toLowerCase().contains("not a valid index")) {
                            System.out.println("Index [" + i + "] has failed: " + e);
                            ret.add(new JSONObject(new ErrorHandling(i, new JSONObject(input).toMap(), e)).toMap());
                        }
                    }
                }

                d.driverQuit();
            }
        } catch (Exception e) {
            e.printStackTrace();

            try {
                AlternativeRequest ar = new AlternativeRequest();
                String t = ar.executeCaseNumber(j);
                ret.add(new JSONObject(t).toMap());
            } catch (Exception f) {
                f.printStackTrace();
                ret.add(new JSONObject(new ErrorHandling(0, new JSONObject(input).toMap(), f)).toMap());
            }
        }

        return ret;
    }

    @PostMapping("/partyName")
    @ResponseBody
    public ArrayList<Map<String, Object>> partyName(@Valid @RequestBody JsonBodyInputPartyName j) {
        ArrayList<Map<String, Object>> ret = new ArrayList<>();
        String input = new Gson().toJson(j);

        try {
            DistrictCourt d = new DistrictCourt("chrome");
            int viewTabsNo = d.partyName(j);
            int index = 0;

            for (int i = 0; i < viewTabsNo && index < j.getRecordsReturned(); i++) {
                try {
                    String t;

                    if (j.getFormat().equalsIgnoreCase("0")) {
                        JsonBodyOutput temp = d.casePage(i, (j.getUpdate().equalsIgnoreCase("y")));
                        temp.setState_val(j.getState_val());
                        temp.setDistrict_val(j.getDistrict_val());
                        temp.setBench_val(j.getBench_val());
                        t = new Gson().toJson(temp);
                    } else {
                        JsonBodyOutputAlt temp = d.casePageAlt(i, j.getUpdate().equalsIgnoreCase("y"));
                        temp.setInputs(new JSONObject(input).toMap());
                        t = new Gson().toJson(temp);
                    }

                    ret.add(new JSONObject(t).toMap());
                    index++;
                } catch (Exception e) {
                    if (!e.getMessage().toLowerCase().contains("not a valid index")) {
                        System.out.println("Index [" + i + "] has failed: " + e);
                        ret.add(new JSONObject(new ErrorHandling(i, new JSONObject(input).toMap(), e)).toMap());
                    }
                }
            }

            d.driverQuit();
        } catch (Exception e) {
            e.printStackTrace();
            ret.add(new JSONObject(new ErrorHandling(0, new JSONObject(input).toMap(), e)).toMap());
        }

        return ret;
    }

    @PostMapping("/advocateName")
    @ResponseBody
    public ArrayList<Map<String, Object>> advocateName(@Valid @RequestBody JsonBodyInputAdvocateName j) {
        ArrayList<Map<String, Object>> ret = new ArrayList<>();
        String input = new Gson().toJson(j);

        try {
            DistrictCourt d = new DistrictCourt("chrome");
            int viewTabsNo = d.advocateName(j);
            int index = 0;

            for (int i = 0; i < viewTabsNo && index < j.getRecordsReturned(); i++) {
                try {
                    String t;

                    if (j.getFormat().equalsIgnoreCase("0")) {
                        JsonBodyOutput temp = d.casePage(i, j.getUpdate().equalsIgnoreCase("y"));
                        temp.setState_val(j.getState_val());
                        temp.setDistrict_val(j.getDistrict_val());
                        temp.setBench_val(j.getBench_val());
                        t = new Gson().toJson(temp);
                    } else {
                        JsonBodyOutputAlt temp = d.casePageAlt(i, j.getUpdate().equalsIgnoreCase("y"));
                        temp.setInputs(new JSONObject(input).toMap());
                        t = new Gson().toJson(temp);
                    }

                    ret.add(new JSONObject(t).toMap());
                    index++;
                } catch (Exception e) {
                    if (!e.getMessage().toLowerCase().contains("not a valid index")) {
                        System.out.println("Index [" + i + "] has failed: " + e);
                        ret.add(new JSONObject(new ErrorHandling(i, new JSONObject(input).toMap(), e)).toMap());
                    }
                }
            }

            d.driverQuit();
        } catch (Exception e) {
            e.printStackTrace();
            ret.add(new JSONObject(new ErrorHandling(0, new JSONObject(input).toMap(), e)).toMap());
        }

        return ret;
    }

    @PostMapping("/causeList")
    @ResponseBody
    public ArrayList<Map<String, Object>> causeList(@Valid @RequestBody JsonBodyInputCauseList j) {
        ArrayList<Map<String, Object>> ret = new ArrayList<>();
        String input = new Gson().toJson(j);

        try {
            DistrictCourt d = new DistrictCourt("chrome");
            int viewTabsNo = d.causeList(j);
            int index = 0;

            for (int i = 0; i < viewTabsNo && index < j.getRecordsReturned(); i++) {
                try {
                    String t;

                    if (j.getFormat().equalsIgnoreCase("0")) {
                        JsonBodyOutput temp = d.casePageCauseList(i, j.getUpdate().equalsIgnoreCase("y"));
                        temp.setState_val(j.getState_val());
                        temp.setDistrict_val(j.getDistrict_val());
                        temp.setBench_val(j.getBench_val());
                        t = new Gson().toJson(temp);
                    } else {
                        JsonBodyOutputAlt temp = d.casePageCauseListAlt(i, j.getUpdate().equalsIgnoreCase("y"));
                        temp.setInputs(new JSONObject(input).toMap());
                        t = new Gson().toJson(temp);
                    }

                    ret.add(new JSONObject(t).toMap());
                    index++;
                } catch (Exception e) {
                    if (!e.getMessage().toLowerCase().contains("not a valid index")) {
                        System.out.println("Index [" + i + "] has failed: " + e);
                        ret.add(new JSONObject(new ErrorHandling(i, new JSONObject(input).toMap(), e)).toMap());
                    }
                }
            }

            d.driverQuit();
        } catch (Exception e) {
            e.printStackTrace();
            ret.add(new JSONObject(new ErrorHandling(0, new JSONObject(input).toMap(), e)).toMap());
        }

        return ret;
    }

    @PostMapping("/cnrNumber")
    @ResponseBody
    public Map<String, Object> cnrNumber(@Valid @RequestBody JsonBodyInputCNRNumber j) {
        Map<String, Object> ret;
        String input = new Gson().toJson(j);

        try {
            if (j.getApi().equalsIgnoreCase("m") || j.getApi().equalsIgnoreCase("l")) {
                AlternativeRequest ar = new AlternativeRequest();
                String t = ar.executeCino(j.getApi(), j.getCino());
                ret = new JSONObject(t).toMap();
            } else {
                eCourt ec = new eCourt("chrome");

                ec.search(j);
                JsonBodyOutput temp = ec.casePage(j.getUpdate().equalsIgnoreCase("y"));

                String t = new Gson().toJson(temp);
                ret = new JSONObject(t).toMap();
            }
        } catch (Exception e) {
            e.printStackTrace();

            if (j.getApi().equalsIgnoreCase("")) {
                try {
                    AlternativeRequest ar = new AlternativeRequest();
                    String t = ar.executeCino("m", j.getCino());
                    ret = new JSONObject(t).toMap();
                } catch (Exception f) {
                    f.printStackTrace();
                    ret = new JSONObject(new ErrorHandling(0, new JSONObject(input).toMap(), f)).toMap();
                }
            } else ret = new JSONObject(new ErrorHandling(0, new JSONObject(input).toMap(), e)).toMap();
        }

        return ret;
    }

    @PostMapping("/caseNumberSci")
    @ResponseBody
    public Map<String, Object> caseNumberSci(@Valid @RequestBody JsonBodyInputCaseNumberSci j) {
        Map<String, Object> ret;
        String input = new Gson().toJson(j);

        try {
            MainSci ms = new MainSci("chrome");

            ms.search(j);
            JsonBodyOutputMainSci temp = ms.casePage();
            temp.setCn(j.getCn());
            temp.setCy(j.getCy());

            String t = new Gson().toJson(temp);
            ret = new JSONObject(t).toMap();
        } catch (Exception e) {
            e.printStackTrace();
            ret = new JSONObject(new ErrorHandling(0, new JSONObject(input).toMap(), e)).toMap();
        }

        return ret;
    }

    @PostMapping("/dataFetchList")
    @ResponseBody
    public Map<String, Object> bench(@Valid @RequestBody JsonBodyInputCNRNumber j) {
        Map<String, Object> ret;
        String input = new Gson().toJson(j);

        try {
            if (j.getApi().equalsIgnoreCase("m") || j.getApi().equalsIgnoreCase("l")) {
                ArrayList<ArrayList<String>> data = ExcelUtil.getDetailsFromExcel("/Users/gauravpurwar/Documents/springboot-selenium-master/src/main/resources/jsonBodyTemplates/Test.xlsx", "Sheet1");
                AlternativeRequest ar = new AlternativeRequest();
                String t = null;
                FileWriter fileWriter = new FileWriter("hello.txt");

                //M
                if(j.getApi().equalsIgnoreCase("m")){

                    for (int d = 0; d < 2; d++) {//7600
                        t = ar.executeDataFetch(j.getApi(), data.get(d).get(0), data.get(d).get(2), data.get(d).get(4));
                        JSONArray arr = new JSONArray(t);
                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject obj = arr.getJSONObject(i);
                            System.out.println("" + data.get(d).get(0) + " | " + data.get(d).get(1) + " | " + data.get(d).get(2) + " | " + data.get(d).get(3) + " | " + data.get(d).get(4) + " | "  + data.get(d).get(5) + " | " + obj.toMap().get("name") + " | " + obj.toMap().get("val"));
                            String s1 = "" + data.get(d).get(0) + " | " + data.get(d).get(1) + " | " + data.get(d).get(2) + " | " + data.get(d).get(3) + " | " + data.get(d).get(4) + " | "  + data.get(d).get(5) + " | " + obj.toMap().get("name") + " | " + obj.toMap().get("val");

                            fileWriter.write("\r\n");
                            fileWriter.write(s1);
                            fileWriter.flush();

                        }
                    }
                  }

                //L
                if(j.getApi().equalsIgnoreCase("l")) {
                    for (int d = 0; d < 2; d++) {//60
                        t = ar.executeDataFetch(j.getApi(), data.get(d).get(0), "0", "0");
                        //    t = ar.executeBenchVal(j.getApi(), "28", "1");
                        System.out.println("T RESPONSE: " + t);
                        if (!t.equalsIgnoreCase("null")) {
                            JSONArray arr = new JSONArray(t);
                            for (int i = 0; i < arr.length(); i++) {
                                JSONObject obj = arr.getJSONObject(i);
                                System.out.println("" + data.get(d).get(0) + " | " + data.get(d).get(1) + " | " + obj.toMap().get("name") + " | " + obj.toMap().get("id"));

                                byte[] decoded1 = Base64.getDecoder().decode(data.get(d).get(0));
                                String decodedStr1 = new String(decoded1, StandardCharsets.UTF_8);
                                byte[] decoded2 = Base64.getDecoder().decode(obj.toMap().get("id").toString());
                                String decodedStr2 = new String(decoded2, StandardCharsets.UTF_8);

                                String s1 = decodedStr1 + " | " + data.get(d).get(0) + " | " + data.get(d).get(1) + " | " + obj.toMap().get("name") + " | " + obj.toMap().get("id") + " | " + decodedStr2;

                                fileWriter.write("\r\n");
                                fileWriter.write(s1);
                                fileWriter.flush();

                            }
                        }
                    }
                }


                ret = new JSONObject(t).toMap();
            } else {
                eCourt ec = new eCourt("chrome");

                ec.search(j);
                JsonBodyOutput temp = ec.casePage(j.getUpdate().equalsIgnoreCase("y"));

                String t = new Gson().toJson(temp);
                ret = new JSONObject(t).toMap();
            }
        } catch (Exception e) {
            e.printStackTrace();

            if (j.getApi().equalsIgnoreCase("")) {
                try {
                    AlternativeRequest ar = new AlternativeRequest();
                    String t = ar.executeCino("m", j.getCino());
                    ret = new JSONObject(t).toMap();
                } catch (Exception f) {
                    f.printStackTrace();
                    ret = new JSONObject(new ErrorHandling(0, new JSONObject(input).toMap(), f)).toMap();
                }
            } else ret = new JSONObject(new ErrorHandling(0, new JSONObject(input).toMap(), e)).toMap();
        }

        return ret;
    }
}