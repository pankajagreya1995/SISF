package com.sisf.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sisf.Adapter.Adapter_home_quiz;
import com.sisf.App_Controller;
import com.sisf.Pojo.Cons_quiz_home;
import com.sisf.Pojo.Response_Chapter_list;
import com.sisf.Pojo.Response_Question;
import com.sisf.R;
import com.sisf.Utils.APIClient;
import com.sisf.Utils.APIInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Quiz_screen extends AppCompatActivity {
    @BindView(R.id.txt_question)
    TextView txt_question;
    @BindView(R.id.options_list)
    ListView list_options;
    @BindView(R.id.txt_current_que)
    TextView txt_current_que;
    @BindView(R.id.btn_prev)
    TextView btn_prev;
    @BindView(R.id.btn_next)
    TextView btn_next;
    List<Response_Question.Response> Questions_list=new ArrayList<>();
    List<Response_Question.Response.Question_Options> Options_list=new ArrayList<>();
    int Current_Question=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);
        ButterKnife.bind(this);
        String Chapt_ID=getIntent().getExtras().getString("position");
        String Chapt_name=getIntent().getExtras().getString("name");

        Toast.makeText(this, "Position"+Chapt_ID, Toast.LENGTH_SHORT).show();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Chapt_name);
        Call_Question_Api(Chapt_ID);

    }


    //Call getQuestion Api
    //Param----ChapterID
    //get All Question with all options
    private void Call_Question_Api(String chapt_ID) {

        if (App_Controller.isNetworkConnected(Quiz_screen.this))
        {
            App_Controller.Progressbar_Show(Quiz_screen.this);
            APIInterface apiService = APIClient.getClient().create(APIInterface.class);
            Call<Response_Question> call = apiService.getQuestions(chapt_ID);
            call.enqueue(new Callback<Response_Question>() {
                @Override
                public void onResponse(Call<Response_Question> call, Response<Response_Question> response) {
                    App_Controller.Progressbar_Dismiss();
                    if (response.body()!=null)
                    {
                        boolean status=response.body().getStatus();
                        if (status)
                        {
                            Questions_list=response.body().getResponce();
                            Show_Question(Current_Question);
                        }else{
                            Toast.makeText(Quiz_screen.this, response.body().getError(), Toast.LENGTH_SHORT).show();
                        }

                    }else
                    {
                        Toast.makeText(Quiz_screen.this, getResources().getString(R.string.toast_please_retry), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Response_Question> call, Throwable t) {
                    App_Controller.Progressbar_Dismiss();
                    Toast.makeText(Quiz_screen.this, getResources().getString(R.string.toast_please_retry), Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(this, getResources().getString(R.string.toast_no_internet), Toast.LENGTH_SHORT).show();
        }
    }


    //Set question on view with current page
    //Set next ,prev button visiblity
    //if current page=1         so invisible prev button
    //if current page is last page so set next button on submit text
    private void Show_Question(int current_question) {

        txt_question.setText(Questions_list.get(current_question).getQuestion());
        Options_list=Questions_list.get(current_question).getQuestionRows();
        List list=new ArrayList();
        for (int i=0;i<Options_list.size();i++)
        {
            list.add(Options_list.get(i).getObjective());
        }
        list_options.setAdapter(new ArrayAdapter<String>(this, R.layout.layout_option, list));
        int curr_que=current_question+1;
        txt_current_que.setText("Question: "+curr_que+"/"+Questions_list.size());

        //Set next ,prev button visiblity
        //if current page=1         so invisible prev button
        //if current page is last page so set next button on submit text
        if(curr_que==1)
        {
            btn_prev.setVisibility(View.GONE);
            btn_next.setText("Next");
        }else if(curr_que==Questions_list.size())
        {
            btn_next.setText("Submit");
            btn_prev.setVisibility(View.VISIBLE);
        }
        else{
            btn_next.setText("Next");
            btn_prev.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    //set click event when last question so next button on click --show result page
    public void button_next(View view) {
        int Check_last=Current_Question+1;
        if (Check_last==Questions_list.size())
        {
            startActivity(new Intent(Quiz_screen.this,Result_Screen.class));
            finish();
        }else
        {
            Current_Question=Current_Question+1;
            Show_Question(Current_Question);
        }

    }


    public void button_prev(View view) {
        if (Current_Question>0)
        {
            Current_Question=Current_Question-1;
            Show_Question(Current_Question);
            btn_next.setVisibility(View.VISIBLE);

        }

    }
}
