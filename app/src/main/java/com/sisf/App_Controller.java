package com.sisf;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.sisf.Activity.Register;

public class App_Controller extends Application {
    static ProgressDialog progressDialog;
    static SharedPreferences preferences;

    //save info in save prefrence
    public static void SharedPre_setDefaults(String key, String value, Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    //get value in save prefrence
    public static String SharedPre_getDefaults(String key, Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    //Remove value in save prefrence
    public static void Sharedpre_Remove(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();

    }

   // show progressbar
    public static void Progressbar_Show(Context con)
    {
        progressDialog=new ProgressDialog(con);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

    }
    //progressbar dismiss
    public static void Progressbar_Dismiss()
    {
        progressDialog.dismiss();
    }


    public static void Hide_keyboard(View v,Context con)
    {
        InputMethodManager imm = (InputMethodManager)con. getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public static boolean isNetworkConnected(Context con) {
        ConnectivityManager cm = (ConnectivityManager) con.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

}
