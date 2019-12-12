package com.sisf.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.sisf.R;

public class Roots_Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roots__details);

        int Select_position=getIntent().getIntExtra("position",0);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TextView txt_desc=findViewById(R.id.txt_desc);
        TextView txt_title_desc=findViewById(R.id.txt_title_desc);
        ImageView img=findViewById(R.id.imageView);
        TextView title_txt=findViewById(R.id.title);
        if (Select_position==0)
        {
            String title="Qualifications:";
            String title_desc="Company Secretary, Masters of Business Administration (Finance), Masters of Commerce, Certified Management Accountant.";
            String manish_desc="Mr. Manish Kapoor is a trustee of Srajansheel Education Society and also the Founder and Chairman of Srajan International School of Finance (SISF). He is the pioneer of ACCA in Udaipur, having introduced the course at a local level, back in the year 2015. He has acquired a tutoring experience of more than 2 decades and is dedicated to share his part of knowledge to as many students as possible. During this tenure of 20 years he has successfully lightened a path for aspiring accounting professionals. He is currently teaching Financial Accounting (FA), Financial Reporting (FR) along with Strategic Business Reporting (SBR) papers in ACCA.  Mr.Manish Kapoor is decorated with high end Accounting qualifications like, CMA, CS,MBA(F), M.com  that makes him an ideal professional to train candidates in the above-mentioned subjects and qualification.";
            title_txt.setText("Manish Kapoor");

            txt_title_desc.setText(title_desc);
            txt_desc.setText(Html.fromHtml(manish_desc),TextView.BufferType.SPANNABLE);
            img.setImageDrawable(getResources().getDrawable(R.drawable.manish_kapoor_square));
        }else if (Select_position==1)
        {
            String title="Qualifications:";
            String title_desc="Chartered Accountant.";
            String shivin_desc="Mr. Shivin Nalwaya, designated as the Managing Director of SISF and also the Head of Operations as well as Head of Academics Department. An integral part of SISF tutor team, has excelled in the field of teaching Management Accounting (MA), Performance Management (PM), Financial Management (FM). With a tutoring experience of more than 3 years in the above mentioned subjects with exceptional pass rates, Mr.Shivin Nalwaya is one of the major pillars of Srajansheel Education Society. Moreover, he also tutors Advanced Financial Management (AFM) and Advanced Performance Management (APM) to our senior students. With professional accounting qualification of CA, Mr.Shivin Nalwaya is an appropriate choice to tutor students in the above-mentioned subjects.";
            title_txt.setText("Shivin Nalwaya");
            txt_title_desc.setText(title_desc);
            txt_desc.setText(Html.fromHtml(shivin_desc),TextView.BufferType.SPANNABLE);
            img.setImageDrawable(getResources().getDrawable(R.drawable.shivin_nalwaya_s));
        }else if (Select_position==2)
        {
            String title="Qualifications:";
            String title_desc="Chartered Accountant, Bachelors of Commerce.";
            String shreya_desc="Miss Shreya Jain is an aspiring young talent in the field of tutoring and is one of our latest additions to our faculty. Being a practicing consultant in International Trade, her industrial exposure adds value to her teachings. She is a young enthusiastic tutor, determined to guide our ACCA students with practical expertise in subjects like Accountant in Business (AB) and Financial Accounting (FA). Her qualifications of CA and B.com (Hons.), enables her to teach her fellow students in the subjects of her expertise.";
            title_txt.setText("Shreya Jain");
            txt_desc.setText(Html.fromHtml(shreya_desc));
            txt_title_desc.setText(title_desc);
            img.setImageDrawable(getResources().getDrawable(R.drawable.shreya_jain_square));
        }else if (Select_position==3)
        {
            String title="Qualifications:";
            String title_desc="Company Secretary, Bachelors of Commerce, Masters of Commerce, NET-IFR.";
            String mayank_desc="Mr.Mayank Sharma is a qualified Company secretary decorated with years of expertise in International Taxation. His extraordinary contribution in tutoring UK variant of Taxation (TX) makes him one of the most valuable assets of SISF. His tenure of 2 years with SISF has benefited many students and will continue to do the same. With appropriate accounting qualifications like CS, B.com, M.com, NET-IRF, Mr.Mayank is an ideal candidate to guide our students in the respective field of Taxation.";
            title_txt.setText("Mayank Sharma");
            txt_title_desc.setText(title_desc);
            txt_desc.setText(Html.fromHtml(mayank_desc));
            img.setImageDrawable(getResources().getDrawable(R.drawable.mayank));
        }else if (Select_position==4)
        {
            String title="Qualifications:";
            String title_desc="Company Secretary, Bachelors of Commerce, Bachelor of Legislative Law, Master of Law";
            String suraj_desc="Mr.Suraj Bohra is a practicing lawyer and a qualified Company Secretary.  His expertise in the field of commercial law adds value to our organization. His association with SISF for past 2 years and his valuable guidance has benefitted our students. Suraj Bohra tutors our students for Global Variant of Corporate and Business Law (LW). His outstanding resume that quotes him as CS, B.com, LLB, and LLM pursuing elects him as a viable tutor for subjects like Corporate and Business Law (LW).";
            title_txt.setText("Suraj Bohra LW");
            txt_desc.setText(Html.fromHtml(suraj_desc));
            txt_title_desc.setText(title_desc);
            img.setImageDrawable(getResources().getDrawable(R.drawable.suraj));
        }else if (Select_position==5)
        {
            String title="Qualifications:";
            String title_desc="Bachelor of Business Management, Master of Commerce (Banking And Business Economy), Masters of Business Administrations (Finance & Human Resource), Doctor of Philosophy (Ph.d Banking & Business Economy)";
            String girish_desc="Dr.Girish Samdani , decorated with a decade of experience in the teaching arena. His expertise in Economics as well as Business studies makes him a valuable member of our professional tutor team. Dr.Girish is a full time tutor for Accountant in Business (AB) and Professional Ethics. With the above mentioned qualifications Dr. Girish is an appropriate candidate to undertake classes for above mentioned subjects.";
            title_txt.setText("Girish Samdani");
            txt_title_desc.setText(title_desc);
            txt_desc.setText(Html.fromHtml(girish_desc));
            img.setImageDrawable(getResources().getDrawable(R.drawable.girish));
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
