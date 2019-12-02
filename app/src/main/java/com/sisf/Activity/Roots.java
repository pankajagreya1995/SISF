package com.sisf.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sisf.Adapter.Adapter_roots;
import com.sisf.Pojo.Cons_roots;
import com.sisf.R;

public class Roots extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roots);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Cons_roots[] myListData = new Cons_roots[] {
                new Cons_roots("Founder and Chairman","Manish Kapoor","Qualifications: Company Secretary, Masters of Business Administration (Finance), Masters of Commerce, Certified Management Accountant. Mr....", R.drawable.manish_kapoor_square),
                new Cons_roots("Managing Director","Shivin Nalwaya","Qualifications: Chartered Accountant. Mr. Shivin Nalwaya, designated as the Managing Director of SISF and also the Head of Operations as well...", R.drawable.shivin_nalwaya_s),
                new Cons_roots("","Shreya Jain","Qualifications: Chartered Accountant, Bachelors of Commerce. Miss Shreya Jain is an aspiring young talent in the field of tutoring and is one...", R.drawable.shreya_jain_square),
                new Cons_roots("","Mayank Sharma","Qualifications: Company Secretary, Bachelors of Commerce, Masters of Commerce, NET-IFR. Mr.Mayank Sharma is a qualified Company secretary decorated...", R.drawable.mayank),
                new Cons_roots("","Suraj Bohra LW","Qualifications: Company Secretary, Bachelors of Commerce, Bachelor of Legislative Law, Master of Law Mr.Suraj Bohra is a practicing lawyer...", R.drawable.suraj),
                new Cons_roots("","Girish Samdani","Qualifications: Bachelor of Business Management, Master of Commerce (Banking And Business Economy), Masters of Business Administrations (Finance...", R.drawable.girish),
        };

        Adapter_roots adapter = new Adapter_roots(myListData,Roots.this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
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
