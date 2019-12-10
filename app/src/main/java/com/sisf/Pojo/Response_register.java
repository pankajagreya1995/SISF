package com.sisf.Pojo;

import com.google.gson.annotations.SerializedName;

public class Response_register {

    @SerializedName("status")
    public Boolean status;

    @SerializedName("message")
    public String message;

    @SerializedName("Responce")
    public String Responce;

    public String getResponce() {
        return Responce;
    }

    public void setResponce(String responce) {
        Responce = responce;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
