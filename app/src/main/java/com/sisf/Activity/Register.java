package com.sisf.Activity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sisf.R;
import com.sisf.Utils.APIClient;
import com.sisf.Utils.APIInterface;
import com.sisf.Utils.Response_register;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {
    ArrayList<String> language;
    ArrayList<String> all_class;
    Spinner spinner;
    Spinner spinner_class;
    @BindView(R.id.layout)
    LinearLayout main_layout;
    @BindView(R.id.txt_namel)
    TextInputLayout ed_namel;
    @BindView(R.id.txt_schooll)
    TextInputLayout ed_schooll;
    @BindView(R.id.txt_emaill) TextInputLayout ed_emaill;
    @BindView(R.id.txt_agel) TextInputLayout ed_agel;
    @BindView(R.id.success_img)
    ImageView img_successfull;
    @BindView(R.id.mobilel) TextInputLayout ed_mobilel;
    String TAG ="Register";
    @BindView(R.id.txt_school) TextInputEditText ed_school;
    @BindView(R.id.txt_name) TextInputEditText ed_name;
    @BindView(R.id.txt_email) TextInputEditText ed_email;
    @BindView(R.id.txt_age) TextInputEditText ed_age;
    @BindView(R.id.mobile) TextInputEditText ed_mobile;
    @BindView(R.id.rb_male)  RadioButton btn_male;
    @BindView(R.id.rb_female)  RadioButton btn_female;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        spinner=findViewById(R.id.sp_course);
        spinner_class=findViewById(R.id.sp_class);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Set_coursetype_nav();
        Set_class_type();
    }

    private void Set_class_type() {
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
        spinner_class.setAdapter(new ArrayAdapter<String>(this,R.layout.layout_text_course,all_class));
        spinner_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void Set_coursetype_nav() {

        language = new ArrayList<String>();
        language.add("ACCA");
        language.add("ACCA PRO");
        language.add("iGRAD in Finance");
        spinner.setAdapter(new ArrayAdapter<String>(this,R.layout.layout_text_course,language));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void Submit_button(View view) {

        //Check validation all inputs
        if (Validation())
        {
            // Then just use the following:
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(main_layout.getWindowToken(), 0);

            String Gender="Male";
            if (btn_female.isChecked())
            {
                Gender="Female";
            }

            progressDialog=new ProgressDialog(Register.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Please wait...");
            progressDialog.show();

            HashMap<String,String>map=new HashMap<>();
            map.put("name",ed_name.getText().toString().trim());
            map.put("gender",Gender);
            map.put("course_type",spinner.getSelectedItem().toString());
            map.put("class",spinner_class.getSelectedItem().toString());
            map.put("school",ed_school.getText().toString().trim());
            map.put("mobile",ed_mobile.getText().toString().trim());
            map.put("age",ed_age.getText().toString().trim());
            map.put("email",ed_email.getText().toString().trim());

            APIInterface apiService = APIClient.getClient().create(APIInterface.class);
            Call<Response_register> call = apiService.register(map);
            call.enqueue(new Callback<Response_register>() {
                @Override
                public void onResponse(Call<Response_register> call, Response<Response_register> response) {
                    progressDialog.dismiss();
                    if (response.body()!=null)
                    {
                        boolean status=response.body().getStatus();
                        if (status)
                        {
                            Toast.makeText(Register.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            img_successfull.setVisibility(View.VISIBLE);
                            Handler handler=new Handler();
                            Runnable runnable=new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            };
                            handler.postDelayed(runnable,3000);

                        }else{
                            Toast.makeText(Register.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }else
                        {
                            Toast.makeText(Register.this, "Try again.", Toast.LENGTH_SHORT).show();
                        }
                }

                @Override
                public void onFailure(Call<Response_register> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(Register.this, "Please retry", Toast.LENGTH_SHORT).show();
                }
            });

        /*AccountManager accountManager = AccountManager.get(Register.this);
        Account account = getAccount(accountManager);
        Log.i(TAG,"All Email====  " +account);*/

        }
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

    public boolean Validation()
    {

        ed_namel.setErrorEnabled(false);
        ed_agel.setErrorEnabled(false);
        ed_emaill.setErrorEnabled(false);
        ed_mobilel.setErrorEnabled(false);
        ed_schooll.setErrorEnabled(false);

        if (ed_name.getText().toString().trim().isEmpty())
        {
            ed_namel.setError("Enter Name");
            ed_name.requestFocus();
            return false;
        }else if (ed_email.getText().toString().trim().isEmpty())
        {
            ed_emaill.setError("Enter Email");
            ed_email.requestFocus();
            return false;
        }else if (!(ed_email.getText().toString().trim().matches(emailPattern)))
        {
            ed_emaill.setError("Invalid Email");
            ed_email.requestFocus();
            return false;
        }else if (ed_age.getText().toString().trim().isEmpty())
        {
            ed_agel.setError("Enter Age");
           ed_age.requestFocus();
            return false;
        }else if (ed_mobile.getText().toString().trim().isEmpty())
        {
            ed_mobilel.setError("Enter Mobile no");
           ed_mobile.requestFocus();
            return false;
        }else if (ed_mobile.getText().toString().trim().length()!=10)
        {
            ed_mobilel.setError("Invalid Mobile no");
            ed_mobile.requestFocus();
            return false;
        }else if (ed_school.getText().toString().trim().isEmpty())
        {
            ed_schooll.setError("Enter School name");
            ed_school.requestFocus();
            return false;
        }
        return true;
    }


}
