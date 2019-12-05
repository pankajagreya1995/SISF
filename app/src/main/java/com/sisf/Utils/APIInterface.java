package com.sisf.Utils;

import com.sisf.Pojo.Response_Chapter_list;
import com.sisf.Pojo.Response_Question;
import com.sisf.Pojo.Response_login;
import com.sisf.Pojo.Response_register;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {
    //This method is used for "POST"

    @FormUrlEncoded
    @POST("app/api/register")
    Call<Response_register> register(@FieldMap HashMap<String, String> data);

    @FormUrlEncoded
    @POST("app/api/submitAnswers")
    Call<Response_register> SubmitAnswer(@FieldMap HashMap<String, String> data);

    @FormUrlEncoded
    @POST("app/api/login")
    Call<Response_login> login(@FieldMap HashMap<String, String> data);

    @GET("app/api/chapterList")
    Call<Response_Chapter_list> getChapter();

    @GET("app/api/questionList?")
    Call<Response_Question> getQuestions(@Query("chapter_id") String chapter_id);
}
