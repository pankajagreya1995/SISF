package com.sisf.Utils;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {
    //This method is used for "POST"

    @FormUrlEncoded
    @POST("api/register")
    Call<Response_register> register(@FieldMap HashMap<String, String> data);

}
