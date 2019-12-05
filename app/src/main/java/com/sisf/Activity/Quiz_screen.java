package com.sisf.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sisf.App_Controller;
import com.sisf.Pojo.Response_Question;
import com.sisf.Pojo.Response_register;
import com.sisf.R;
import com.sisf.Utils.APIClient;
import com.sisf.Utils.APIInterface;
import com.sisf.Utils.App_Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

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
    @BindView(R.id.txt_timer)
    TextView ed_timer;
    List<Response_Question.Response> Questions_list=new ArrayList<>();
    List<Response_Question.Response.Question_Options> Options_list=new ArrayList<>();
    int Current_Question=0;
    String TAG ="Quiz_screen";
    //Save All selected options Answer
    HashMap<String,String> select_answer_List=new HashMap<>();
    String Chapt_ID,Chapt_name,Chapt_timer;
    int Chapter_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);
        ButterKnife.bind(this);
        Chapt_ID=getIntent().getExtras().getString("chapter_id");
        Chapt_name=getIntent().getExtras().getString("name");
        Chapt_timer=getIntent().getExtras().getString("timer");
        Chapter_position=getIntent().getExtras().getInt("position");
       // getSupportActionBar().setDisplayShowHomeEnabled(true);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Chapt_name);
        Call_Question_Api(Chapt_ID);

        long milisec_time=getMilliFromDate(Chapt_timer);

        new CountDownTimer(milisec_time, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {
                ed_timer.setText("Timer: "+String.format("%d h:%d min: %d sec",
                        TimeUnit.MILLISECONDS.toHours( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)  - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }
            public void onFinish() {
                ed_timer.setText("done!");
            }
        }.start();
       ;

    }

    public long getMilliFromDate(String dateFormat) {

        String[] tokens = dateFormat.split(":");
        int secondsToMs = Integer.parseInt(tokens[2]) * 1000;
        int minutesToMs = Integer.parseInt(tokens[1]) * 60000;
        int hoursToMs = Integer.parseInt(tokens[0]) * 3600000;
        long timeInMilliseconds = secondsToMs + minutesToMs + hoursToMs;
        return timeInMilliseconds;
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
    private void Show_Question(final int current_question) {

        txt_question.setText(Questions_list.get(current_question).getQuestion());
        Options_list=Questions_list.get(current_question).getQuestionRows();
        List list=new ArrayList();
        for (int i=0;i<Options_list.size();i++)
        {
            list.add(Options_list.get(i).getObjective());
        }
        //  list_options.setAdapter(new ArrayAdapter<String>(this, android.R.layout.activity_list_item, list));
        list_options.setAdapter(new ArrayAdapter<String>(this, R.layout.layout_option, list));

        int curr_que=current_question+1;
        txt_current_que.setText("Question: "+curr_que+"/"+Questions_list.size());


        list_options.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        list_options.setItemChecked(2, true);


        list_options.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.setSelected(true);
                String get_Question_ID=Questions_list.get(current_question).getId().toString();
                String get_Select_Options_ID=Questions_list.get(current_question).getQuestionRows().get(i).getId();
                select_answer_List.put(get_Question_ID,get_Select_Options_ID);
            }
        });

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

        Check_Already_Answer(Questions_list.get(current_question).getId(),current_question);
    }

    private void Check_Already_Answer(String Ques_ID, int current_question) {

        String check_ans=select_answer_List.get(Ques_ID);
        Log.i(TAG,"Select Options Answer---  "+check_ans);
        if (check_ans!=null)
        {
            for(int i=0;i<Questions_list.get(current_question).getQuestionRows().size();i++)
            {
                String optionsid=Questions_list.get(current_question).getQuestionRows().get(i).getId();
                if (check_ans.equalsIgnoreCase(optionsid))
                {   try{
                    //list_options.getAdapter().getView(i, null, null).performClick();

                    /*list_options.setSelection(i);
                    list_options.getSelectedView().setSelected(true);*/
                }catch (Exception e){
                    Log.i(TAG,"Select listview Error :  "+e.toString());
                    e.printStackTrace();}

                    break;
                }
            }
        }

    }

    /*@Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
*/

    //set click event when last question so next button on click --show result page
    public void button_next(View view) {
        int Check_last=Current_Question+1;
        if (Check_last==Questions_list.size())
        {
            Log.i(TAG,"Show result   "+select_answer_List);
            Show_Result();
          //  Call_Submit_Result_Api();
            startActivity(new Intent(Quiz_screen.this,Result_Screen.class));
            finish();
        }else
        {
            Current_Question=Current_Question+1;
            Show_Question(Current_Question);
        }

    }

    private void Show_Result() {

       /* for(int i=0;i<Questions_list.size();i++)
        {
            String get_answer=Questions_list.get(i).getId();
            Log.i(TAG,"Select Options Answer---  "+get_answer);
            if (get_answer!=null)
            {
                for(int j=0;j<Questions_list.get(i).getQuestionRows().size();j++)
                {
                    String optionsid=Questions_list.get(current_question).getQuestionRows().get(j).getId();
                    if (check_ans.equalsIgnoreCase(optionsid))
                    {   try{
                        //list_options.getAdapter().getView(i, null, null).performClick();

                    *//*list_options.setSelection(i);
                    list_options.getSelectedView().setSelected(true);*//*
                    }catch (Exception e){
                        Log.i(TAG,"Select listview Error :  "+e.toString());
                        e.printStackTrace();}

                        break;
                    }
                }
            }
        }*/

    }

    private void Call_Submit_Result_Api() {

        String attemp_size= String.valueOf(select_answer_List.size());

        select_answer_List.put("student_id", App_Controller.SharedPre_getDefaults(App_Utils.USER_ID,Quiz_screen.this));
        select_answer_List.put("chapter_id",""+Chapt_ID);
        select_answer_List.put("total_question",""+Questions_list.size());
        select_answer_List.put("total_attempts",""+attemp_size);

        select_answer_List.put("total_right","");
        select_answer_List.put("percentage","");
        select_answer_List.put("status","");
        select_answer_List.put("student_id","");
        select_answer_List.put("student_id","");

        APIInterface apiService = APIClient.getClient().create(APIInterface.class);
        Call<Response_register> call = apiService.SubmitAnswer(select_answer_List);

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
