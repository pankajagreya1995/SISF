package com.sisf.Pojo;

import com.google.gson.annotations.SerializedName;

public class Response_forgot_pwd {
    @SerializedName("status")
    public Boolean status;

    @SerializedName("Error")
    public String Error;

    @SerializedName("Responce")
    public Responce Responce;

    public  class Responce{
        @SerializedName("OTP")
        public String OTP;

        @SerializedName("email")
        public String email;

        @SerializedName("id")
        public String id;

        public String getOTP() {
            return OTP;
        }

        public void setOTP(String OTP) {
            this.OTP = OTP;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public Response_forgot_pwd.Responce getResponce() {
        return Responce;
    }

    public void setResponce(Response_forgot_pwd.Responce responce) {
        Responce = responce;
    }
}
