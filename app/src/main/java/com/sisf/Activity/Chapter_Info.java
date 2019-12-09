package com.sisf.Activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sisf.R;
import com.sisf.Utils.App_Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Chapter_Info extends AppCompatActivity {

    @BindView(R.id.txt_desc)
    TextView ed_desc;

    @BindView(R.id.txt_ques_size)
    TextView ed_question_size;

    @BindView(R.id.txt_time)
    TextView ed_timer;
    String Chapt_ID,Chapt_name;
    int item_position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter__info);
        ButterKnife.bind(this);

        //  title.setText("hiii");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Chapt_ID=getIntent().getExtras().getString("position");
        Chapt_name=getIntent().getExtras().getString("name");
        item_position=getIntent().getExtras().getInt("item_position");
        getSupportActionBar().setTitle(Chapt_name);
        ed_desc.setText(App_Utils.Chapter_List_Info.get(item_position).getDescription());
        ed_question_size.setText("Total no of questions: "+App_Utils.Chapter_List_Info.get(item_position).getNo_of_question());
        ed_timer.setText("Time: "+App_Utils.Chapter_List_Info.get(item_position).getTiming());

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void Quiz_start(View view) {
        startActivity(new Intent(Chapter_Info.this, Quiz_screen.class).
                putExtra("item_position",item_position).
                putExtra("chapter_id",Chapt_ID).
                putExtra("name",Chapt_name).
                putExtra("timer",App_Utils.Chapter_List_Info.get(item_position).getTiming()));
        finish();
    }
}
