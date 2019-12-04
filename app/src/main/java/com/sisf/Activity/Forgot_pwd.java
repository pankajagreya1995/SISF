package com.sisf.Activity;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.sisf.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Forgot_pwd extends AppCompatActivity {
    @BindView(R.id.mobile)
    TextInputEditText ed_mobile;
    TextInputEditText otp_txt,otp_pwd,otp_confirm_pwd;
    TextInputLayout otp_txt_layout,otp_pass_layout,otp_confirm_pwd_layout;
    @BindView(R.id.mobilel)
    TextInputLayout ed_mobilel;

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
        if (Validation())
        {
            View dialogView = getLayoutInflater().inflate(R.layout.layout_otp, null);
            final BottomSheetDialog dialog = new BottomSheetDialog(this);
            dialog.setContentView(dialogView);
            dialog.setCancelable(false);
            final TextView btn_submit=dialogView.findViewById(R.id.submit_button);

            otp_txt_layout =dialogView.findViewById(R.id.mobilel);
            otp_txt=dialogView.findViewById(R.id.mobile);

            otp_pass_layout =dialogView.findViewById(R.id.txt_passwordl);
            otp_pwd=dialogView.findViewById(R.id.txt_password);

            otp_confirm_pwd_layout =dialogView.findViewById(R.id.txt_passwordcl);
            otp_confirm_pwd=dialogView.findViewById(R.id.txt_passwordc);
            otp_pass_layout.setVisibility(View.GONE);
            otp_confirm_pwd_layout.setVisibility(View.GONE);


            btn_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (Validation_otp() && (!btn_submit.getText().toString().trim().equalsIgnoreCase(getResources().getString(R.string.submit_otp_txt))))
                    {
                        //enter otp successfull right
                        otp_txt_layout.setVisibility(View.GONE);
                        otp_pass_layout.setVisibility(View.VISIBLE);
                        otp_confirm_pwd_layout.setVisibility(View.VISIBLE);
                        btn_submit.setText(getResources().getString(R.string.submit_otp_txt));
                    }else if (Validation_password()){
                        dialog.dismiss();
                        finish();
                        Toast.makeText(Forgot_pwd.this, "Successfully reset your password", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            dialog.show();
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

    public boolean Validation_otp()
    {

        otp_txt_layout.setErrorEnabled(false);

        if (otp_txt.getText().toString().trim().isEmpty()) {
            otp_txt_layout.setError("Enter OTP");
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
            ed_mobilel.setError("Enter Mobile no");
            ed_mobile.requestFocus();
            return false;
        }else if (ed_mobile.getText().toString().trim().length()!=10)
        {
            ed_mobilel.setError("Invalid Mobile no");
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
