package com.sisf.Pojo;

import com.google.gson.annotations.SerializedName;

public class Response_register {

    @SerializedName("status")
    public Boolean status;

    @SerializedName("message")
    public String message;

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
