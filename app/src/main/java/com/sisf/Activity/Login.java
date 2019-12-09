package com.sisf.Activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sisf.App_Controller;
import com.sisf.Pojo.Response_login;
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

    @BindView(R.id.txt_emaill)   TextInputLayout ed_emaill;
    @BindView(R.id.txt_email)    TextInputEditText ed_email;

    @BindView(R.id.txt_passwordl) TextInputLayout ed_passl;
    @BindView(R.id.txt_password) TextInputEditText ed_pass;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
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
        startActivity(new Intent(Login.this,Forgot_pwd.class));
    }



    //on click login button
    public void login_button(View view) {
        if (Validation())
        {
            if (App_Controller.isNetworkConnected(Login.this))
            {
                App_Controller.Progressbar_Show(Login.this);
                HashMap<String,String> map=new HashMap<>();
                map.put("email",ed_email.getText().toString().trim());
                map.put("password",ed_pass.getText().toString().trim());
                APIInterface apiService = APIClient.getClient().create(APIInterface.class);
                Call<Response_login> call = apiService.login(map);
                call.enqueue(new Callback<Response_login>() {
                    @Override
                    public void onResponse(Call<Response_login> call, Response<Response_login> response) {
                        App_Controller.Progressbar_Dismiss();
                        if (response.body()!=null)
                        {
                            boolean status=response.body().getStatus();
                            if (status)
                            {
                                Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                App_Controller.SharedPre_setDefaults(App_Utils.USER_ID,response.body().getResponse().getId(),Login.this);
                                App_Controller.SharedPre_setDefaults(App_Utils.USER_NAME,response.body().getResponse().getName(),Login.this);
                                App_Controller.SharedPre_setDefaults(App_Utils.USER_EMAIL,response.body().getResponse().getClass_name(),Login.this);
                                App_Controller.SharedPre_setDefaults(App_Utils.USER_MOBILE,response.body().getResponse().getName(),Login.this);
                                App_Controller.SharedPre_setDefaults(App_Utils.USER_COURSE_TYPE,response.body().getResponse().getName(),Login.this);
                                App_Controller.SharedPre_setDefaults(App_Utils.USER_GENDER,response.body().getResponse().getName(),Login.this);
                                App_Controller.SharedPre_setDefaults(App_Utils.USER_AGE,response.body().getResponse().getName(),Login.this);
                                App_Controller.SharedPre_setDefaults(App_Utils.USER_SCHOOL,response.body().getResponse().getName(),Login.this);
                                App_Controller.SharedPre_setDefaults(App_Utils.USER_CLASS,response.body().getResponse().getName(),Login.this);
                                startActivity(new Intent(Login.this,Home.class));
                                finish();

                            }else{
                                Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }else
                        {
                            Toast.makeText(Login.this, getResources().getString(R.string.toast_something_wentrong), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Response_login> call, Throwable t) {
                        App_Controller.Progressbar_Dismiss();
                        Toast.makeText(Login.this, getResources().getString(R.string.toast_please_retry), Toast.LENGTH_SHORT).show();
                    }
                });

            }else
                {
                    Toast.makeText(this, getResources().getString(R.string.toast_no_internet), Toast.LENGTH_SHORT).show();
                }
        }
    }


    public boolean Validation()
    {

        ed_emaill.setErrorEnabled(false);
        ed_passl.setErrorEnabled(false);

        if (ed_email.getText().toString().trim().isEmpty())
        {
            ed_emaill.setError("Enter Email");
            ed_email.requestFocus();
            return false;
        }else if (!(ed_email.getText().toString().trim().matches(emailPattern)))
        {
            ed_emaill.setError("Invalid Email");
            ed_email.requestFocus();
            return false;
        }else if (ed_pass.getText().toString().trim().isEmpty())
        {
            ed_passl.setError("Enter Password");
            ed_pass.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id)
        {
            case R.id.action_login:
                startActivity(new Intent(Login.this,Register.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login_menu,menu);
        return true;
    }

    public void hide_keybord(View view) {
        App_Controller.Hide_keyboard(view,Login.this);
    }
}
