package com.sisf.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response_Chapter_list {
    @SerializedName("status")
    public Boolean status;

    @SerializedName("Error")
    public String Error;

    @SerializedName("Responce")
    public List<Data> Responce=null;
    public class Data{
        @SerializedName("id")
        public String id;

        @SerializedName("name")
        public String name;

        @SerializedName("description")
        public String description;

        @SerializedName("subject_id")
        public String subject_id;

        @SerializedName("timing")
        public String timing;

        @SerializedName("no_of_question")
        public String no_of_question;

        public String getSubject_id() {
            return subject_id;
        }

        public void setSubject_id(String subject_id) {
            this.subject_id = subject_id;
        }

        public String getTiming() {
            return timing;
        }

        public void setTiming(String timing) {
            this.timing = timing;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String gettiming() {
            return timing;
        }

        public void settiming(String timing) {
            this.timing = timing;
        }

        public String getNo_of_question() {
            return no_of_question;
        }

        public void setNo_of_question(String no_of_question) {
            this.no_of_question = no_of_question;
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

    public List<Data> getResponce() {
        return Responce;
    }

    public void setResponce(List<Data> responce) {
        Responce = responce;
    }
}
