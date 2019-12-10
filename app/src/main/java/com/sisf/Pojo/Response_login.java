package com.sisf.Pojo;

import com.google.gson.annotations.SerializedName;

public class Response_login {
    @SerializedName("status")
    public Boolean status;

    @SerializedName("message")
    public String message;

    @SerializedName("response_code")
    public Integer response_code;

    @SerializedName("responce_id")
    public String responce_id;

    @SerializedName("response")
    public Resp_login response;

    public class Resp_login{
        @SerializedName("id")
        public String id;

        @SerializedName("name")
        public String name;

        @SerializedName("email")
        public String email;

        @SerializedName("course_type")
        public String course_type;

        @SerializedName("gender")
        public String gender;

        @SerializedName("age")
        public String age;

        @SerializedName("school")
        public String school;

        @SerializedName("class")
        public String class_name;

        @SerializedName("created_on")
        public String created_on;

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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCourse_type() {
            return course_type;
        }

        public void setCourse_type(String course_type) {
            this.course_type = course_type;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public String getClass_name() {
            return class_name;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }
    }

    public String getResponce_id() {
        return responce_id;
    }

    public void setResponce_id(String responce_id) {
        this.responce_id = responce_id;
    }

    public Integer getResponse_code() {
        return response_code;
    }

    public void setResponse_code(Integer response_code) {
        this.response_code = response_code;
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

    public Resp_login getResponse() {
        return response;
    }

    public void setResponse(Resp_login response) {
        this.response = response;
    }
}
