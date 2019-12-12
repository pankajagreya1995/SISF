package com.sisf.Activity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sisf.App_Controller;
import com.sisf.R;
import com.sisf.Utils.App_Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Result_Screen extends AppCompatActivity {

    @BindView(R.id.result_name)
    TextView ed_result_name;

    @BindView(R.id.result_right_que)
    TextView ed_result_right_que;

    @BindView(R.id.result_total_que)
    TextView ed_result_total_que;

    @BindView(R.id.progressbar)
    ProgressBar progress;

    @BindView(R.id.total_attemp)
    TextView ed_total_attemp;

    @BindView(R.id.result_per)
    TextView ed_result_per;
    int pStatus = 0;
    private Handler handler = new Handler();
    String percentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result__screen);
        ButterKnife.bind(this);
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
        getSupportActionBar().setTitle("Show Result");

        try{
            String total_right=getIntent().getExtras().getString("total_right");
            ed_result_right_que.setText(total_right+" Que.");
            String chapter_name=getIntent().getExtras().getString("chapter_name");
            ed_result_name.setText(chapter_name);

            String subject_name=getIntent().getExtras().getString("subject_name");
            getSupportActionBar().setSubtitle(subject_name);

            String total_question=getIntent().getExtras().getString("total_question");
            ed_result_total_que.setText(total_question+" Que.");

            String total_attempts=getIntent().getExtras().getString("total_attempts");
            ed_total_attemp.setText(total_attempts+" Que.");

            percentage=getIntent().getExtras().getString("percentage");
            ed_result_per.setText(percentage+"%");
           }
        catch (Exception e){e.printStackTrace();}

        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.circular);
        progress.setProgress(0);   // Main Progress
        progress.setSecondaryProgress(100); // Secondary Progress
        progress.setMax(100); // Maximum Progress
        progress.setProgressDrawable(drawable);
        progress.setProgress(Integer.parseInt(percentage));
      /*  ObjectAnimator animation = ObjectAnimator.ofInt(mProgress, "progress", 0, 100);
        animation.setDuration(50000);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();*/

       /* new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (pStatus < 100) {
                    pStatus += 1;

                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            progress.setProgress(pStatus);
                            ed_result_per.setText(pStatus + "%");

                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        // Just to display the progress slowly
                        Thread.sleep(16); //thread will take approx 3 seconds to finish
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();*/


    }


    public void btn_finish(View view) {
        finish();
    }
}
