package com.staffapp.mobile.model;


import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Map;

public class CustomResponse {

    @SerializedName("timeStamp")
    String timeStamp = null;
    @SerializedName("statusCode")
    Long statusCode = null;
    @SerializedName("status")
    String status = null;
    @SerializedName("reason")
    String reason = null;
    @SerializedName("message")
    String message = null;
    @SerializedName("developerMessage")
    String developerMessage = null;
    @SerializedName("data")
    Map<?, ?> data = null;

    public CustomResponse() {

    }

    public CustomResponse(String timeStamp, Long statusCode, String status, String reason, String message, String developerMessage, Map<?, ?> data) {
        this.timeStamp = timeStamp;
        this.statusCode = statusCode;
        this.status = status;
        this.reason = reason;
        this.message = message;
        this.developerMessage = developerMessage;
        this.data = data;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Long getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Long statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public Map<?, ?> getData() {
        return data;
    }

    public void setData(Map<?, ?> data) {
        this.data = data;
    }


}
