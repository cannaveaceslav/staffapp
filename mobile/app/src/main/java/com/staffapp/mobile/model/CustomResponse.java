package com.staffapp.mobile.model;


import java.time.LocalDateTime;
import java.util.Map;

public class CustomResponse {

    String timeStamp = null;
    Long statusCode = null;
    String status = null;
    String reason = null;
    String message = null;
    String developerMessage = null;
    Map<?, ?> data = null;

    public CustomResponse(String timeStamp, Long statusCode, String status, String reason, String message, String developerMessage, Map<?, ?> data) {
        this.timeStamp = timeStamp;
        this.statusCode = statusCode;
        this.status = status;
        this.reason = reason;
        this.message = message;
        this.developerMessage = developerMessage;
        this.data = data;
    }

    public CustomResponse() {
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public Long getStatusCode() {
        return statusCode;
    }

    public String getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    public String getMessage() {
        return message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public Map<?, ?> getData() {
        return data;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setStatusCode(Long statusCode) {
        this.statusCode = statusCode;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public void setData(Map<?, ?> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CustomResponse{" +
                "timeStamp='" + timeStamp + '\'' +
                ", statusCode=" + statusCode +
                ", status='" + status + '\'' +
                ", reason='" + reason + '\'' +
                ", message='" + message + '\'' +
                ", developerMessage='" + developerMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
