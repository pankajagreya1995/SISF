package com.sisf.Utils;

import com.sisf.Pojo.Response_Chapter_list;
import com.sisf.Pojo.Response_Question;
import com.sisf.Pojo.Response_forgot_pwd;
import com.sisf.Pojo.Response_login;
import com.sisf.Pojo.Response_register;

import java.util.HashMap;
import java.util.Queue;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {
    //This method is used for "POST"

    @FormUrlEncoded
    @POST("app/api/forgotPassword")
    Call<Response_forgot_pwd> forget_password(@FieldMap HashMap<String, String> data);

    @FormUrlEncoded
    @POST("app/api/changePassword")
    Call<Response_forgot_pwd> change_password(@FieldMap HashMap<String, String> data);

    @FormUrlEncoded
    @POST("app/api/registerStudent")
    Call<Response_register> register(@FieldMap HashMap<String, String> data);

    @FormUrlEncoded
    @POST("app/api/submitAnswers")
    Call<Response_register> SubmitAnswer(@FieldMap HashMap<String, String> data);

    @FormUrlEncoded
    @POST("app/api/login")
    Call<Response_login> login(@FieldMap HashMap<String, String> data);

    @GET("app/api/subjectList")
    Call<Response_Chapter_list> getSubject();

    @GET("app/api/chapterList")
    Call<Response_Chapter_list> getChapter(@Query("subject_id") String subject_id);

    @GET("app/api/questionList?")
    Call<Response_Question> getQuestions(@Query("chapter_id") String chapter_id);
}
