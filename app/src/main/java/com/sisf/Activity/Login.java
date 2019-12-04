package com.sisf.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.sisf.R;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    public void Forgot_button(View view) {
        startActivity(new Intent(Login.this,Forgot_pwd.class));
    }

    public void login_button(View view) {
        if (Validation())
        {
            startActivity(new Intent(Login.this,Home.class));
            finish();
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
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
