package com.dmcorrales.api.commons.api.controller;

import java.io.Serializable;

public class RestResponse<HV> implements Serializable {

    private int status;
    private String error;
    private String message;
    private HV content;

    public RestResponse(int status, String error, String message, HV content) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.content = content;
    }

    public String toJson(){
        return "{" +
                "\"status\":" + this.status + "," +
                "\"error\":\"" + this.error + "\"," +
                "\"message\":\"" + this.message + "\"," +
                "\"content\":\"" + this.content + "\"" +
                "}";
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setContent(HV content) {
        this.content = content;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public HV getContent() {
        return content;
    }
}
