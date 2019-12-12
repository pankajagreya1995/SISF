package com.sisf.Activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sisf.Adapter.Adapter_home_quiz;
import com.sisf.Adapter.Adapter_roots;
import com.sisf.App_Controller;
import com.sisf.Pojo.Cons_quiz_home;
import com.sisf.Pojo.Cons_roots;
import com.sisf.Pojo.Response_Chapter_list;
import com.sisf.Pojo.Response_register;
import com.sisf.R;
import com.sisf.Utils.APIClient;
import com.sisf.Utils.APIInterface;
import com.sisf.Utils.App_Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sisf.Utils.App_Utils.Chapter_List_Info;

public class Home_Quiz extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__quiz);

        ButterKnife.bind(this);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setHasFixedSize(true);

        Call_Chapter_Api();

    }

    private void Call_Chapter_Api() {

            if (App_Controller.isNetworkConnected(Home_Quiz.this))
            {
                App_Controller.Progressbar_Show(Home_Quiz.this);

                APIInterface apiService = APIClient.getClient().create(APIInterface.class);
                Call<Response_Chapter_list> call = apiService.getSubject();
                call.enqueue(new Callback<Response_Chapter_list>() {
                    @Override
                    public void onResponse(Call<Response_Chapter_list> call, Response<Response_Chapter_list> response) {
                        App_Controller.Progressbar_Dismiss();
                        if (response.body()!=null)
                        {
                            boolean status=response.body().getStatus();
                            if (status)
                            {
                                Chapter_List_Info=new ArrayList<>();
                                Chapter_List_Info.clear();
                                for (int i=0;i<response.body().getResponce().size();i++)
                                {
                                    Chapter_List_Info.add(new Cons_quiz_home(response.body().getResponce().get(i).getId(),
                                            response.body().getResponce().get(i).getName()));
                                }

                                Adapter_home_quiz adapter = new Adapter_home_quiz(Chapter_List_Info,Home_Quiz.this);
                                recyclerView.setAdapter(adapter);

                            }else{
                                Toast.makeText(Home_Quiz.this, response.body().getError(), Toast.LENGTH_SHORT).show();
                            }

                        }else
                        {
                            Toast.makeText(Home_Quiz.this, getResources().getString(R.string.toast_please_retry), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Response_Chapter_list> call, Throwable t) {
                        App_Controller.Progressbar_Dismiss();
                        Toast.makeText(Home_Quiz.this, getResources().getString(R.string.toast_please_retry), Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                Toast.makeText(this, getResources().getString(R.string.toast_no_internet), Toast.LENGTH_SHORT).show();
            }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
