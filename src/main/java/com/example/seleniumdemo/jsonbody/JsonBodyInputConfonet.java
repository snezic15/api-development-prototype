package com.example.seleniumdemo.jsonbody;

import javax.validation.constraints.NotNull;

public class JsonBodyInputConfonet {
    @NotNull
    private String bench_val;
    @NotNull
    private String ct;
    @NotNull
    private String cn;
    @NotNull
    private String cy;
    @NotNull
    private String stateCode;
    @NotNull
    private String distCode;
    @NotNull
    private String api = "";

    public String getBench_val() {
        return bench_val;
    }

    public void setBench_val(String bench_val) {
        this.bench_val = bench_val;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getCy() {
        return cy;
    }

    public void setCy(String cy) {
        this.cy = cy;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getDistCode() {
        return distCode;
    }

    public void setDistCode(String distCode) {
        this.distCode = distCode;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }
}