package com.example.seleniumdemo.jsonbody;

import javax.validation.constraints.NotNull;

public class JsonBodyInputCaseNumberSci {
    @NotNull
    private String ct;
    @NotNull
    private String cn;
    @NotNull
    private String cy;

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
}