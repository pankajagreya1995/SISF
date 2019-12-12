package com.sisf.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.sisf.App_Controller;
import com.sisf.Pojo.Response_login;
import com.sisf.Pojo.Response_register;
import com.sisf.R;
import com.sisf.Utils.APIClient;
import com.sisf.Utils.APIInterface;
import com.sisf.Utils.App_Utils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    @BindView(R.id.txt_emaill)
    TextInputLayout ed_emaill;
    @BindView(R.id.txt_email)
    TextInputEditText ed_email;
    @BindView(R.id.login_button)
    TextView btn_login;
    @BindView(R.id.txt_passwordl)
    TextInputLayout ed_passl;
    @BindView(R.id.txt_password)
    TextInputEditText ed_pass;
    BottomSheetDialog dialog;
    TextInputEditText otp_txt;
    TextInputLayout otp_txt_layout;
    TextView btn_error;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    //On click Forgot button
    public void Forgot_button(View view) {
        startActivity(new Intent(Login.this, Forgot_pwd.class));
    }


    //on click login button
    public void login_button(View view) {
        if (Validation()) {
            if (App_Controller.isNetworkConnected(Login.this)) {
                App_Controller.Progressbar_Show(Login.this);
                HashMap<String, String> map = new HashMap<>();
                map.put("email", ed_email.getText().toString().trim());
                map.put("password", ed_pass.getText().toString().trim());
                APIInterface apiService = APIClient.getClient().create(APIInterface.class);
                Call<Response_login> call = apiService.login(map);
                call.enqueue(new Callback<Response_login>() {
                    @Override
                    public void onResponse(Call<Response_login> call, Response<Response_login> response) {
                        App_Controller.Progressbar_Dismiss();
                        if (response.body() != null) {
                            boolean status = response.body().getStatus();
                            int getcode = response.body().getResponse_code();
                            if (getcode == 201) {
                                String get_resp_id = response.body().getResponce_id();
                                Show_Otp_dialog(get_resp_id);
                            } else if (getcode == 401) {
                                Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            } else if (getcode == 200) {
                                if (status) {
                                    Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    App_Controller.SharedPre_setDefaults(App_Utils.USER_ID, response.body().getResponse().getId(), Login.this);
                                    App_Controller.SharedPre_setDefaults(App_Utils.USER_NAME, response.body().getResponse().getName(), Login.this);
                                    App_Controller.SharedPre_setDefaults(App_Utils.USER_EMAIL, response.body().getResponse().getClass_name(), Login.this);
                                    App_Controller.SharedPre_setDefaults(App_Utils.USER_MOBILE, response.body().getResponse().getName(), Login.this);
                                    App_Controller.SharedPre_setDefaults(App_Utils.USER_COURSE_TYPE, response.body().getResponse().getName(), Login.this);
                                    App_Controller.SharedPre_setDefaults(App_Utils.USER_GENDER, response.body().getResponse().getName(), Login.this);
                                    App_Controller.SharedPre_setDefaults(App_Utils.USER_AGE, response.body().getResponse().getName(), Login.this);
                                    App_Controller.SharedPre_setDefaults(App_Utils.USER_SCHOOL, response.body().getResponse().getName(), Login.this);
                                    App_Controller.SharedPre_setDefaults(App_Utils.USER_CLASS, response.body().getResponse().getName(), Login.this);
                                    startActivity(new Intent(Login.this, Home.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                    finish();
                                } else {
                                    Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }


                        } else {
                            Toast.makeText(Login.this, getResources().getString(R.string.toast_something_wentrong), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Response_login> call, Throwable t) {
                        App_Controller.Progressbar_Dismiss();
                        Toast.makeText(Login.this, getResources().getString(R.string.toast_please_retry), Toast.LENGTH_SHORT).show();
                    }
                });

            } else {
                Toast.makeText(this, getResources().getString(R.string.toast_no_internet), Toast.LENGTH_SHORT).show();
            }
        }
    }


    public boolean Validation() {

        ed_emaill.setErrorEnabled(false);
        ed_passl.setErrorEnabled(false);

        if (ed_email.getText().toString().trim().isEmpty()) {
            ed_emaill.setError("Enter Email");
            ed_email.requestFocus();
            return false;
        } else if (!(ed_email.getText().toString().trim().matches(emailPattern))) {
            ed_emaill.setError("Invalid Email");
            ed_email.requestFocus();
            return false;
        } else if (ed_pass.getText().toString().trim().isEmpty()) {
            ed_passl.setError("Enter Password");
            ed_pass.requestFocus();
            return false;
        }
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_login:
                startActivity(new Intent(Login.this, Register.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login_menu, menu);
        return true;
    }*/

    public void hide_keybord(View view) {
        App_Controller.Hide_keyboard(view, Login.this);
    }

    private void Show_Otp_dialog(final String getid) {

        View dialogView = getLayoutInflater().inflate(R.layout.layout_regi_otp, null);
        dialog = new BottomSheetDialog(this,R.style.AppBottomSheetDialogTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dialogView);
        dialog.setCancelable(false);
        final TextView btn_submit = dialogView.findViewById(R.id.submit_button);
        final TextView btn_cancel = dialogView.findViewById(R.id.submit_cancel);
        btn_error = dialogView.findViewById(R.id.txt_error);
        otp_txt_layout = dialogView.findViewById(R.id.mobilel);
        otp_txt = dialogView.findViewById(R.id.mobile);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Validation_otp()) {

                    App_Controller.Hide_keyboard(view, Login.this);
                    HashMap<String, String> map = new HashMap<>();
                    map.put("id", "" + getid);
                    map.put("verification_otp", "" + otp_txt.getText().toString().trim());
                    Call_Otp_Api(map);
                }
            }
        });
        dialog.show();

    }

    private void Call_Otp_Api(HashMap<String, String> map) {
        if (App_Controller.isNetworkConnected(Login.this)) {
            // Then just use the following:
            App_Controller.Progressbar_Show(Login.this);
            APIInterface apiService = APIClient.getClient().create(APIInterface.class);
            Call<Response_register> call = apiService.verifyAccount(map);
            call.enqueue(new Callback<Response_register>() {
                @Override
                public void onResponse(Call<Response_register> call, Response<Response_register> response) {
                    App_Controller.Progressbar_Dismiss();
                    if (response.body() != null) {
                        boolean status = response.body().getStatus();
                        if (status) {
                            dialog.dismiss();
                            Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            btn_login.performClick();
                        } else {
                            btn_error.setText(response.body().getMessage());
                            Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(Login.this, getResources().getString(R.string.toast_something_wentrong), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Response_register> call, Throwable t) {
                    App_Controller.Progressbar_Dismiss();
                    Toast.makeText(Login.this, getResources().getString(R.string.toast_please_retry), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, getResources().getString(R.string.toast_no_internet), Toast.LENGTH_SHORT).show();
        }
    }


    public boolean Validation_otp() {

        otp_txt_layout.setErrorEnabled(false);
        if (otp_txt.getText().toString().trim().isEmpty()) {
            otp_txt_layout.setError("Enter OTP");
            otp_txt.requestFocus();
            return false;
        }
        return true;
    }

    public void register_button(View view) {
        startActivity(new Intent(Login.this,Register.class));
    }
}
