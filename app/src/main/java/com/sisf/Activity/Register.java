package com.sisf.Activity;

import android.os.Handler;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sisf.App_Controller;
import com.sisf.R;
import com.sisf.Utils.APIClient;
import com.sisf.Utils.APIInterface;
import com.sisf.Pojo.Response_register;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    ArrayList<String> language;
    ArrayList<String> all_class;

    @BindView(R.id.sp_course)
    Spinner spinner;
    @BindView(R.id.sp_class)
    Spinner spinner_class;
    @BindView(R.id.layout)
    LinearLayout main_layout;
    @BindView(R.id.txt_namel)
    TextInputLayout ed_namel;
    @BindView(R.id.txt_passwordl)
    TextInputLayout ed_passl;
    @BindView(R.id.txt_passwordcl)
    TextInputLayout ed_passcl;
    @BindView(R.id.txt_schooll)
    TextInputLayout ed_schooll;
    @BindView(R.id.txt_emaill)
    TextInputLayout ed_emaill;
    @BindView(R.id.txt_agel)
    TextInputLayout ed_agel;
    @BindView(R.id.success_img)
    ImageView img_successfull;
    @BindView(R.id.mobilel)
    TextInputLayout ed_mobilel;
    String TAG = "Register";
    @BindView(R.id.txt_school)
    TextInputEditText ed_school;
    @BindView(R.id.txt_password)
    TextInputEditText ed_pass;
    @BindView(R.id.txt_passwordc)
    TextInputEditText ed_passc;
    @BindView(R.id.txt_name)
    TextInputEditText ed_name;
    @BindView(R.id.txt_email)
    TextInputEditText ed_email;
    @BindView(R.id.txt_age)
    TextInputEditText ed_age;
    @BindView(R.id.mobile)
    TextInputEditText ed_mobile;
    @BindView(R.id.rb_male)
    RadioButton btn_male;
    @BindView(R.id.rb_female)
    RadioButton btn_female;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    BottomSheetDialog dialog;
    TextInputEditText otp_txt;
    TextInputLayout otp_txt_layout;
    TextView btn_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Set_Data_Spinner();

    }

    private void Set_Data_Spinner() {

        language = new ArrayList<String>();
        language.add("ACCA");
        language.add("ACCA PRO");
        language.add("iGRAD in Finance");
        spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.layout_text_course, language));

        all_class = new ArrayList<String>();
        all_class.add("1 Class");
        all_class.add("2 Class");
        all_class.add("3 Class");
        all_class.add("4 Class");
        all_class.add("5 Class");
        all_class.add("6 Class");
        all_class.add("7 Class");
        all_class.add("8 Class");
        all_class.add("9 Class");
        all_class.add("10 Class");
        all_class.add("11 Class");
        all_class.add("12 Class");
        spinner_class.setAdapter(new ArrayAdapter<String>(this, R.layout.layout_text_course, all_class));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    //Call Register submit button
    //Param#----name,
    // //Param#----gender,
    // //Param#----submited_from,
    // //Param#----course_type,
    // //Param#----password,
    // //Param#----class,
    // //Param#----school,
    // //Param#----mobile,
    // //Param#----age,
    // //Param#----email
    //get true=====if successfully register
    //get false=====if already register email id
    //get false=====if get any apy error
    public void Submit_button(View view) {

        //Check validation all inputs
        if (Validation()) {

            if (App_Controller.isNetworkConnected(Register.this)) {
                // Then just use the following:
                App_Controller.Hide_keyboard(view, Register.this);

                String Gender = "Male";
                if (btn_female.isChecked()) {
                    Gender = "Female";
                }

                App_Controller.Progressbar_Show(Register.this);

                HashMap<String, String> map = new HashMap<>();
                map.put("name", ed_name.getText().toString().trim());
                map.put("gender", Gender);
                map.put("submitted_from", "mobile");
                map.put("course_type", spinner.getSelectedItem().toString());
                map.put("password", ed_pass.getText().toString().trim());
                map.put("class", spinner_class.getSelectedItem().toString());
                map.put("school", ed_school.getText().toString().trim());
                map.put("mobile", ed_mobile.getText().toString().trim());
                map.put("age", ed_age.getText().toString().trim());
                map.put("email", ed_email.getText().toString().trim());

                APIInterface apiService = APIClient.getClient().create(APIInterface.class);
                Call<Response_register> call = apiService.register(map);
                call.enqueue(new Callback<Response_register>() {
                    @Override
                    public void onResponse(Call<Response_register> call, Response<Response_register> response) {
                        App_Controller.Progressbar_Dismiss();
                        if (response.body() != null) {
                            boolean status = response.body().getStatus();
                            if (status) {
                                //           Toast.makeText(Register.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                String getid = response.body().getResponce();
                                Show_Otp_dialog(getid);
                               /* img_successfull.setVisibility(View.VISIBLE);
                                Handler handler=new Handler();
                                Runnable runnable=new Runnable() {
                                    @Override
                                    public void run() {
                                        finish();
                                    }
                                };
                                handler.postDelayed(runnable,3000);*/

                            } else {
                                Toast.makeText(Register.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(Register.this, getResources().getString(R.string.toast_something_wentrong), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Response_register> call, Throwable t) {
                        App_Controller.Progressbar_Dismiss();
                        Toast.makeText(Register.this, getResources().getString(R.string.toast_please_retry), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, getResources().getString(R.string.toast_no_internet), Toast.LENGTH_SHORT).show();
            }


        /*AccountManager accountManager = AccountManager.get(Register.this);
        Account account = getAccount(accountManager);
        Log.i(TAG,"All Email====  " +account);*/

        }
    }

    private void Show_Otp_dialog(final String getid) {

        View dialogView = getLayoutInflater().inflate(R.layout.layout_regi_otp, null);
        dialog = new BottomSheetDialog(this);
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

                    App_Controller.Hide_keyboard(view, Register.this);
                    HashMap<String,String>map=new HashMap<>();
                    map.put("id",""+getid);
                    map.put("verification_otp",""+otp_txt.getText().toString().trim());
                    Call_Otp_Api(map);
                }
            }
        });
        dialog.show();

    }

    private void Call_Otp_Api(HashMap<String, String> map) {
        if (App_Controller.isNetworkConnected(Register.this)) {
            // Then just use the following:
            App_Controller.Progressbar_Show(Register.this);
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
                            //           Toast.makeText(Register.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                img_successfull.setVisibility(View.VISIBLE);
                                Handler handler=new Handler();
                                Runnable runnable=new Runnable() {
                                    @Override
                                    public void run() {
                                        finish();
                                    }
                                };
                                handler.postDelayed(runnable,3000);

                        } else {

                            btn_error.setText(response.body().getMessage());
                            Toast.makeText(Register.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(Register.this, getResources().getString(R.string.toast_something_wentrong), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Response_register> call, Throwable t) {
                    App_Controller.Progressbar_Dismiss();
                    Toast.makeText(Register.this, getResources().getString(R.string.toast_please_retry), Toast.LENGTH_SHORT).show();
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


   /* private static Account getAccount(AccountManager accountManager) {
        Account[] accounts = accountManager.getAccounts();
    //    Account[] accounts = accountManager.getAccountsByType("com.google");
        Account account;
        if (accounts.length > 0) {
            account = accounts[0];
        } else {
            account = null;
        }
        return account;
    }*/


    //Valiation
    //name,age,email,school,password
    //In check Empty validation,pattern
    public boolean Validation() {

        ed_namel.setErrorEnabled(false);
        ed_agel.setErrorEnabled(false);
        ed_emaill.setErrorEnabled(false);
        ed_mobilel.setErrorEnabled(false);
        ed_schooll.setErrorEnabled(false);
        ed_passl.setErrorEnabled(false);
        ed_passcl.setErrorEnabled(false);

        if (ed_name.getText().toString().trim().isEmpty()) {
            ed_namel.setError("Enter Name");
            ed_name.requestFocus();
            return false;
        } else if (ed_email.getText().toString().trim().isEmpty()) {
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
        } else if (ed_pass.getText().toString().trim().length() < 5) {
            ed_passl.setError("Enter Password minimum 6 digits");
            ed_pass.requestFocus();
            return false;
        } else if (ed_passc.getText().toString().trim().isEmpty()) {
            ed_passcl.setError("Enter Confirm Password");
            ed_passc.requestFocus();
            return false;
        } else if (!ed_pass.getText().toString().trim().equalsIgnoreCase(ed_passc.getText().toString().trim())) {
            ed_passcl.setError("Password not match");
            ed_passc.requestFocus();
            return false;
        } else if (ed_age.getText().toString().trim().isEmpty()) {
            ed_agel.setError("Enter Age");
            ed_age.requestFocus();
            return false;
        } else if (ed_mobile.getText().toString().trim().isEmpty()) {
            ed_mobilel.setError("Enter Mobile no");
            ed_mobile.requestFocus();
            return false;
        } else if (ed_mobile.getText().toString().trim().length() != 10) {
            ed_mobilel.setError("Invalid Mobile no");
            ed_mobile.requestFocus();
            return false;
        } else if (ed_school.getText().toString().trim().isEmpty()) {
            ed_schooll.setError("Enter School name");
            ed_school.requestFocus();
            return false;
        }
        return true;
    }


}
