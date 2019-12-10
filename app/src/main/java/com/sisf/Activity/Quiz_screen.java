package com.sisf.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
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
import com.sisf.Pojo.Cons_submit_ans;
import com.sisf.Pojo.Response_Question;
import com.sisf.Pojo.Response_register;
import com.sisf.R;
import com.sisf.Utils.APIClient;
import com.sisf.Utils.APIInterface;
import com.sisf.Utils.App_Utils;

import java.lang.reflect.Array;
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
    List<Response_Question.Response> Questions_list = new ArrayList<>();
    List<Response_Question.Response.Question_Options> Options_list = new ArrayList<>();
    int Current_Question = 0;
    String TAG = "Quiz_screen";

    String Chapt_ID, Chapt_name, Chapt_timer,subject_id,subject_name;
    int Chapter_position;
    //Save All selected options Answer
    List<Cons_submit_ans> Answer_List = new ArrayList<>();
    View vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);
        ButterKnife.bind(this);

        subject_name=getIntent().getExtras().getString("subject_name");
        subject_id = getIntent().getExtras().getString("subject_id");
        Chapt_ID = getIntent().getExtras().getString("chapter_id");
        Chapt_name = getIntent().getExtras().getString("name");
        Chapt_timer = getIntent().getExtras().getString("timer");
        Chapter_position = getIntent().getExtras().getInt("position");

        getSupportActionBar().setTitle(Chapt_name);
        getSupportActionBar().setSubtitle(subject_name);
        Call_Question_Api(Chapt_ID);

        long milisec_time = getMilliFromDate(Chapt_timer);

        new CountDownTimer(milisec_time, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {
                ed_timer.setText("Timer: " + String.format("%d h:%d min: %d sec",
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                Show_Result();
                ed_timer.setText("done!");
            }
        }.start();


    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Complite this Quiz", Toast.LENGTH_SHORT).show();
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

        if (App_Controller.isNetworkConnected(Quiz_screen.this)) {
            App_Controller.Progressbar_Show(Quiz_screen.this);
            APIInterface apiService = APIClient.getClient().create(APIInterface.class);
            Call<Response_Question> call = apiService.getQuestions(chapt_ID);
            call.enqueue(new Callback<Response_Question>() {
                @Override
                public void onResponse(Call<Response_Question> call, Response<Response_Question> response) {
                    App_Controller.Progressbar_Dismiss();
                    if (response.body() != null) {
                        boolean status = response.body().getStatus();
                        if (status) {
                            Questions_list = response.body().getResponce();
                            Show_Question(Current_Question);
                        } else {
                            Toast.makeText(Quiz_screen.this, response.body().getError(), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(Quiz_screen.this, getResources().getString(R.string.toast_please_retry), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Response_Question> call, Throwable t) {
                    App_Controller.Progressbar_Dismiss();
                    Toast.makeText(Quiz_screen.this, getResources().getString(R.string.toast_please_retry), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, getResources().getString(R.string.toast_no_internet), Toast.LENGTH_SHORT).show();
        }
    }


    //Set question on view with current page
    //Set next ,prev button visiblity
    //if current page=1         so invisible prev button
    //if current page is last page so set next button on submit text
    private void Show_Question(final int current_question) {

        txt_question.setText(Questions_list.get(current_question).getQuestion());
        Options_list = Questions_list.get(current_question).getQuestionRows();
        List list = new ArrayList();
        for (int i = 0; i < Options_list.size(); i++) {
            list.add(Options_list.get(i).getObjective());
        }
        //  list_options.setAdapter(new ArrayAdapter<String>(this, android.R.layout.activity_list_item, list));
        list_options.setAdapter(new ArrayAdapter<String>(this, R.layout.layout_option, list));

        int curr_que = current_question + 1;
        txt_current_que.setText("Question: " + curr_que + "/" + Questions_list.size());


        list_options.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                view.setSelected(true);
                try {
                    int get_Question_ID = Integer.parseInt(Questions_list.get(current_question).getId().toString());
                    int get_Select_Options_ID = Integer.parseInt(Questions_list.get(current_question).getQuestionRows().get(i).getId());
                    //Check ans in correct or Incorrect
                    if (Questions_list.get(current_question).getQuestionRows().get(i).getCurrect_answer().equalsIgnoreCase("Correct")) {
                        if (Questions_list.size() != 0) {
                            //Check Answer in reselect in already selected andwer list
                            boolean Already_ans_select = true;
                            for (int k = 0; k < Answer_List.size(); k++) {
                                if (get_Question_ID == Answer_List.get(k).getQuest_ID()) {
                                    Already_ans_select = false;
                                    Answer_List.set(k, new Cons_submit_ans(Integer.parseInt(Chapt_ID), get_Question_ID, get_Select_Options_ID, true));
                                    break;
                                }
                            }

                            //if no answer in this answer list so add answer in list
                            if (Already_ans_select) {
                                Answer_List.add(new Cons_submit_ans(Integer.parseInt(Chapt_ID), get_Question_ID, get_Select_Options_ID, true));
                            }

                        } else {
                            Answer_List.add(new Cons_submit_ans(Integer.parseInt(Chapt_ID), get_Question_ID, get_Select_Options_ID, true));
                        }

                    } else {
                        if (Answer_List.size() != 0) {
                            //Check Answer in reselect in already selected andwer list
                            boolean Already_ans_select = true;
                            for (int k = 0; k < Answer_List.size(); k++) {
                                if (get_Question_ID == Answer_List.get(k).getQuest_ID()) {
                                    Already_ans_select = false;
                                    Answer_List.set(k, new Cons_submit_ans(Integer.parseInt(Chapt_ID), get_Question_ID, get_Select_Options_ID, false));
                                    break;
                                }
                            }
                            //if no answer in this answer list so add answer in list
                            if (Already_ans_select) {
                                Answer_List.add(new Cons_submit_ans(Integer.parseInt(Chapt_ID), get_Question_ID, get_Select_Options_ID, false));
                            }
                        } else {
                            Answer_List.add(new Cons_submit_ans(Integer.parseInt(Chapt_ID), get_Question_ID, get_Select_Options_ID, false));
                        }

                        //          Answer_List.add(new Cons_submit_ans(Integer.parseInt(Chapt_ID), get_Question_ID, get_Select_Options_ID, false));
                    }

                } catch (Exception e) {
                    Log.i(TAG, "List error" + e.toString());
                    e.printStackTrace();
                }

            }
        });


        //Set next ,prev button visiblity
        //if current page=1         so invisible prev button
        //if current page is last page so set next button on submit text
        if (curr_que == 1) {
            btn_prev.setVisibility(View.GONE);
            btn_next.setText("Next");
        } else if (curr_que == Questions_list.size()) {
            btn_next.setText("Submit");
            btn_prev.setVisibility(View.VISIBLE);
        } else {
            btn_next.setText("Next");
            btn_prev.setVisibility(View.VISIBLE);
        }


        //Auto select option if already selected options
        //Already select ans is highlight in listview
        if (Answer_List.size() != 0) {
            for (int k = 0; k < Answer_List.size(); k++) {
                int getQue_ID = Answer_List.get(k).getQuest_ID();
                int getAns_ID = Answer_List.get(k).getSelect_Ans();
                for (int l = 0; l < Questions_list.get(current_question).getQuestionRows().size(); l++) {
                    if (getQue_ID == Integer.valueOf(Questions_list.get(current_question).getId()) &&
                            getAns_ID == Integer.valueOf(Questions_list.get(current_question).getQuestionRows().get(l).getId())) {
                        list_options.requestFocusFromTouch();
                        list_options.setSelection(l);
                        break;
                    }
                }
            }

        }


    }


    //set click event when last question so next button on click --show result page
    public void button_next(View view) {
        int Check_last = Current_Question + 1;
        if (Check_last == Questions_list.size()) {

            Show_Result();
            // Call_Submit_Result_Api();
        } else {
            Current_Question = Current_Question + 1;
            Show_Question(Current_Question);
        }

    }

    private void Show_Result() {

        int Right_Answer = 0;

        HashMap<String, String> api_map = new HashMap<>();
        for (int x = 0; x < Answer_List.size(); x++) {
            Cons_submit_ans cons_submit_ans = new Cons_submit_ans();
            cons_submit_ans = Answer_List.get(x);
            api_map.put("question_id[" + x + "]", String.valueOf(cons_submit_ans.getQuest_ID()));
            api_map.put("question_row_id[" + x + "]", String.valueOf(cons_submit_ans.getSelect_Ans()));
            if (cons_submit_ans.isRight_Ans()) {
                Right_Answer = Right_Answer + 1;
            }
        }

        long per = ((Right_Answer * 100) / Questions_list.size());
        int Total_attemp = Answer_List.size();
        int Total_question = Questions_list.size();
        int Total_ans = Answer_List.size();
        api_map.put("total_right", "" + Right_Answer);
        api_map.put("student_id", "" + App_Controller.SharedPre_getDefaults(App_Utils.USER_ID, Quiz_screen.this));
        api_map.put("chapter_id", "" + Chapt_ID);
        api_map.put("total_question", "" + Total_question);
        api_map.put("total_attempts", "" + Total_attemp);
        api_map.put("percentage", "" + per);
        api_map.put("status", "");
        api_map.put("subject_id", "" + subject_id);
        Log.i(TAG, "ShowResult__   " + api_map);
        Call_Submit_Result_Api(api_map);
    }


    private void Call_Submit_Result_Api(final HashMap<String, String> api_map) {
        if (App_Controller.isNetworkConnected(Quiz_screen.this))
        {
            App_Controller.Progressbar_Show(Quiz_screen.this);
            APIInterface apiService = APIClient.getClient().create(APIInterface.class);
            Call<Response_register> call = apiService.SubmitAnswer(api_map);
            call.enqueue(new Callback<Response_register>() {
                @Override
                public void onResponse(Call<Response_register> call, Response<Response_register> response) {
                    App_Controller.Progressbar_Dismiss();
                    if (response.body() != null) {
                        boolean status = response.body().getStatus();
                        if (status) {
       //                     Toast.makeText(Quiz_screen.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Quiz_screen.this, Result_Screen.class);
                            intent.putExtra("total_right", "" + api_map.get("total_right"));
                            intent.putExtra("chapter_name", "" + Chapt_name);
                            intent.putExtra("total_question", "" + api_map.get("total_question"));
                            intent.putExtra("total_attempts", "" + api_map.get("total_attempts"));
                            intent.putExtra("percentage", "" + api_map.get("percentage"));
                            intent.putExtra("subject_name", "" + subject_name);
                            intent.putExtra("status", "" + api_map.get("status"));
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Quiz_screen.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(Quiz_screen.this, getResources().getString(R.string.toast_something_wentrong), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Response_register> call, Throwable t) {
                    App_Controller.Progressbar_Dismiss();
                }
            });
        }else{
            Toast.makeText(this, getResources().getString(R.string.toast_no_internet), Toast.LENGTH_SHORT).show();
        }

    }


    public void button_prev(View view) {
        if (Current_Question > 0) {
            Current_Question = Current_Question - 1;
            Show_Question(Current_Question);
            btn_next.setVisibility(View.VISIBLE);

        }

    }
}
