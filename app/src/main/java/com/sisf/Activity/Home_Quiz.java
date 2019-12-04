package com.sisf.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sisf.Adapter.Adapter_home_quiz;
import com.sisf.Adapter.Adapter_roots;
import com.sisf.Pojo.Cons_quiz_home;
import com.sisf.Pojo.Cons_roots;
import com.sisf.R;

public class Home_Quiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__quiz);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Cons_quiz_home[] chapter_list = new Cons_quiz_home[] {
                new Cons_quiz_home("Chapter 1","Manish Kapoor","Qualifications: Company Secretary, Masters of Business Administration (Finance), Masters of Commerce, Certified Management Accountant. Mr...."),
                new Cons_quiz_home("Managing Director","Shivin Nalwaya","Qualifications: Chartered Accountant. Mr. Shivin Nalwaya, designated as the Managing Director of SISF and also the Head of Operations as well..."),
                new Cons_quiz_home("","Shreya Jain","Qualifications: Chartered Accountant, Bachelors of Commerce. Miss Shreya Jain is an aspiring young talent in the field of tutoring and is one..."),
                new Cons_quiz_home("","Mayank Sharma","Qualifications: Company Secretary, Bachelors of Commerce, Masters of Commerce, NET-IFR. Mr.Mayank Sharma is a qualified Company secretary decorated..."),
                new Cons_quiz_home("","Suraj Bohra LW","Qualifications: Company Secretary, Bachelors of Commerce, Bachelor of Legislative Law, Master of Law Mr.Suraj Bohra is a practicing lawyer..."),
                new Cons_quiz_home("","Girish Samdani","Qualifications: Bachelor of Business Management, Master of Commerce (Banking And Business Economy), Masters of Business Administrations (Finance..."),
        };

        Adapter_home_quiz adapter = new Adapter_home_quiz(chapter_list,Home_Quiz.this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
