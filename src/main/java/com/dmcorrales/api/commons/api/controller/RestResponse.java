package com.dmcorrales.api.commons.api.controller;

import java.io.Serializable;

public class RestResponse<HV> implements Serializable {

    private int status;
    private boolean ok;
    private String error;
    private String message;
    private HV content;

    public RestResponse(int status, boolean ok, String error, String message, HV content) {
        this.status = status;
        this.ok = ok;
        this.error = error;
        this.message = message;
        this.content = content;
    }

    public String toJson(){
        return "{" +
                "\"status\":" + this.status + "," +
                "\"ok\":" + this.ok + "," +
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

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public int getStatus() {
        return status;
    }

    public boolean isOk() {
        return ok;
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
