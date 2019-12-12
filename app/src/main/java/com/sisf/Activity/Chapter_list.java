package com.sisf.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.sisf.Adapter.Adapter_Chapter_list;
import com.sisf.Adapter.Adapter_home_quiz;
import com.sisf.App_Controller;
import com.sisf.Pojo.Cons_quiz_home;
import com.sisf.Pojo.Response_Chapter_list;
import com.sisf.R;
import com.sisf.Utils.APIClient;
import com.sisf.Utils.APIInterface;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sisf.Utils.App_Utils.Chapter_List_Info;

public class Chapter_list extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    String Subject_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_list);
        ButterKnife.bind(this);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

       String Subject_id=getIntent().getExtras().getString("subject_id");
       Subject_name=getIntent().getExtras().getString("subject_name");
     //  getSupportActionBar().setTitle(Subject_name);
        TextView title=findViewById(R.id.txttitle);
        title.setText(Subject_name);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setHasFixedSize(true);

        Call_Chapter_Api(Subject_id);

    }

    private void Call_Chapter_Api(String subject_id) {

        if (App_Controller.isNetworkConnected(Chapter_list.this))
        {
            App_Controller.Progressbar_Show(Chapter_list.this);

            APIInterface apiService = APIClient.getClient().create(APIInterface.class);
            Call<Response_Chapter_list> call = apiService.getChapter(subject_id);
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
                                        response.body().getResponce().get(i).getName(),
                                        response.body().getResponce().get(i).getDescription(),
                                        response.body().getResponce().get(i).getNo_of_question(),
                                        response.body().getResponce().get(i).gettiming(),response.body().getResponce().get(i).getSubject_id(),Subject_name));
                            }

                            Adapter_Chapter_list adapter = new Adapter_Chapter_list(Chapter_List_Info,Chapter_list.this);
                            recyclerView.setAdapter(adapter);

                        }else{
                            Toast.makeText(Chapter_list.this, response.body().getError(), Toast.LENGTH_SHORT).show();
                        }

                    }else
                    {
                        Toast.makeText(Chapter_list.this, getResources().getString(R.string.toast_please_retry), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Response_Chapter_list> call, Throwable t) {
                    App_Controller.Progressbar_Dismiss();
                    Toast.makeText(Chapter_list.this, getResources().getString(R.string.toast_please_retry), Toast.LENGTH_SHORT).show();
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
