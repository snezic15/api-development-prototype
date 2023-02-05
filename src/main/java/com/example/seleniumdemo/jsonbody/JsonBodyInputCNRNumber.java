package com.example.seleniumdemo.jsonbody;

import javax.validation.constraints.NotNull;

public class JsonBodyInputCNRNumber {
    @NotNull
    private String cino;
    private String api = "";
    private String update = "";

    public String getCino() {
        return cino;
    }

    public void setCino(String cino) {
        this.cino = cino;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }
}