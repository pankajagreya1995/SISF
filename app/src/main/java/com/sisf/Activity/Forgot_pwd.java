package com.sisf.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sisf.App_Controller;
import com.sisf.Pojo.Response_forgot_pwd;
import com.sisf.Pojo.Response_register;
import com.sisf.R;
import com.sisf.Utils.APIClient;
import com.sisf.Utils.APIInterface;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Forgot_pwd extends AppCompatActivity {
    @BindView(R.id.mobile)
    TextInputEditText ed_mobile;
    TextInputEditText otp_txt,otp_pwd,otp_confirm_pwd;
    TextInputLayout otp_txt_layout,otp_pass_layout,otp_confirm_pwd_layout;
    @BindView(R.id.mobilel)
    TextInputLayout ed_mobilel;
    @BindView(R.id.img_succ)
    ImageView img_success;
    @BindView(R.id.txt_error)
    TextView ed_error;
    BottomSheetDialog dialog;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pwd);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void send_otp_button(View view) {

        //Check validation email address
        //Call send email address for forgot password
        //get response in otp and user id
        if (Validation())
        {

            HashMap<String,String>map=new HashMap<>();
            map.put("email",ed_mobile.getText().toString().trim());
            Call_Forgot_pwd_Api(map);
        }
    }

    private void Call_Forgot_pwd_Api(final HashMap<String, String> api_map) {
        if (App_Controller.isNetworkConnected(Forgot_pwd.this))
        {
            App_Controller.Progressbar_Show(Forgot_pwd.this);
            APIInterface apiService = APIClient.getClient().create(APIInterface.class);
            Call<Response_forgot_pwd> call = apiService.forget_password(api_map);
            call.enqueue(new Callback<Response_forgot_pwd>() {
                @Override
                public void onResponse(Call<Response_forgot_pwd> call, Response<Response_forgot_pwd> response) {
                    App_Controller.Progressbar_Dismiss();
                    if (response.body() != null) {
                        boolean status = response.body().getStatus();
                        if (status) {
                            Show_Otp_Dialog(response.body().getResponce().getOTP(),response.body().getResponce().getId(),response.body().getError());
                        //    Toast.makeText(Forgot_pwd.this, response.body().getError(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Forgot_pwd.this, response.body().getError(), Toast.LENGTH_SHORT).show();
                            ed_error.setText(response.body().getError().toString());
                            ed_error.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                        }

                    } else {
                        Toast.makeText(Forgot_pwd.this, getResources().getString(R.string.toast_something_wentrong), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Response_forgot_pwd> call, Throwable t) {
                    App_Controller.Progressbar_Dismiss();
                    Toast.makeText(Forgot_pwd.this, getResources().getString(R.string.toast_please_retry), Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(this, getResources().getString(R.string.toast_no_internet), Toast.LENGTH_SHORT).show();
        }

    }

    private void Show_Otp_Dialog(final String otp, final String id,final String msg) {

            View dialogView = getLayoutInflater().inflate(R.layout.layout_otp, null);
            dialog = new BottomSheetDialog(this);
            dialog.setContentView(dialogView);
            dialog.setCancelable(false);
            final TextView btn_submit=dialogView.findViewById(R.id.submit_button);

            otp_txt_layout =dialogView.findViewById(R.id.mobilel);
            otp_txt=dialogView.findViewById(R.id.mobile);

            TextView ed_error=dialogView.findViewById(R.id.txt_error);
            ed_error.setText(msg);
            otp_pass_layout =dialogView.findViewById(R.id.txt_passwordl);
            otp_pwd=dialogView.findViewById(R.id.txt_password);

            otp_confirm_pwd_layout =dialogView.findViewById(R.id.txt_passwordcl);
            otp_confirm_pwd=dialogView.findViewById(R.id.txt_passwordc);
            otp_pass_layout.setVisibility(View.GONE);
            otp_confirm_pwd_layout.setVisibility(View.GONE);


            btn_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if ((!btn_submit.getText().toString().trim().equalsIgnoreCase(getResources().getString(R.string.submit_otp_txt))))
                    {
                        if(Validation_otp(otp))
                        {
                            //enter otp successfull right
                            otp_txt_layout.setVisibility(View.GONE);
                            otp_pass_layout.setVisibility(View.VISIBLE);
                            otp_confirm_pwd_layout.setVisibility(View.VISIBLE);
                            btn_submit.setText(getResources().getString(R.string.submit_otp_txt));
                        }

                    }else if (btn_submit.getText().toString().trim().equalsIgnoreCase(getResources().getString(R.string.submit_otp_txt))){
                            if (Validation_password())
                            {
                                HashMap<String,String>map=new HashMap<>();
                                map.put("password",otp_confirm_pwd.getText().toString().trim());
                                map.put("id",""+id);
                                Call_Change_pwd_Api(map);
                            }
                    }
                }
            });
            dialog.show();
    }

    private void Call_Change_pwd_Api(HashMap<String, String> map) {
        if (App_Controller.isNetworkConnected(Forgot_pwd.this))
        {
            App_Controller.Progressbar_Show(Forgot_pwd.this);
            APIInterface apiService = APIClient.getClient().create(APIInterface.class);
            Call<Response_forgot_pwd> call = apiService.change_password(map);
            call.enqueue(new Callback<Response_forgot_pwd>() {
                @Override
                public void onResponse(Call<Response_forgot_pwd> call, Response<Response_forgot_pwd> response) {
                    App_Controller.Progressbar_Dismiss();
                    if (response.body() != null) {
                        boolean status = response.body().getStatus();
                        if (status) {
                            dialog.dismiss();
                          //  Toast.makeText(Forgot_pwd.this, response.body().getError(), Toast.LENGTH_SHORT).show();
                            img_success.setVisibility(View.VISIBLE);
                            Handler handler=new Handler();
                            Runnable runnable=new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            };
                            handler.postDelayed(runnable,2000);
                        } else {
                            Toast.makeText(Forgot_pwd.this, response.body().getError(), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(Forgot_pwd.this, getResources().getString(R.string.toast_something_wentrong), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Response_forgot_pwd> call, Throwable t) {
                    App_Controller.Progressbar_Dismiss();
                    Toast.makeText(Forgot_pwd.this, getResources().getString(R.string.toast_please_retry), Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(this, getResources().getString(R.string.toast_no_internet), Toast.LENGTH_SHORT).show();
        }
    }


    public boolean Validation_password()
    {

        otp_pass_layout.setErrorEnabled(false);
        otp_confirm_pwd_layout.setErrorEnabled(false);

        if (otp_pwd.getText().toString().trim().isEmpty()) {
            otp_pass_layout.setError("Enter Password");
            otp_pwd.requestFocus();
            return false;
        }else if (otp_confirm_pwd.getText().toString().trim().isEmpty()) {
            otp_txt_layout.setError("Enter Confirm Password");
            otp_confirm_pwd_layout.requestFocus();
            return false;
        }else if (!otp_confirm_pwd.getText().toString().trim().equalsIgnoreCase(otp_pwd.getText().toString().trim())) {
            otp_confirm_pwd_layout.setError("Not match");
            otp_confirm_pwd.requestFocus();
            return false;
        }
        return true;
    }

    public boolean Validation_otp(String otp)
    {

        otp_txt_layout.setErrorEnabled(false);

        if (otp_txt.getText().toString().trim().isEmpty()) {
            otp_txt_layout.setError("Enter OTP");
            otp_txt.requestFocus();
            return false;
        }else if (!(otp_txt.getText().toString().trim().equalsIgnoreCase(otp.trim()))) {
            otp_txt_layout.setError("Enter OTP invalid");
            otp_txt.requestFocus();
            return false;
        }
        return true;
    }

    public boolean Validation()
    {

        ed_mobilel.setErrorEnabled(false);

        if (ed_mobile.getText().toString().trim().isEmpty())
        {
            ed_mobilel.setError("Enter Email");
            ed_mobile.requestFocus();
            return false;
        }else if (!(ed_mobile.getText().toString().trim().matches(emailPattern)))
        {
            ed_mobilel.setError("Enter Email");
            ed_mobile.requestFocus();
            return false;
        }
        return true;
    }

    public void hide_keyboard(View view) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
