package com.sisf.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sisf.Activity.Quiz_screen;
import com.sisf.Pojo.Cons_quiz_home;
import com.sisf.R;

public class Adapter_home_quiz extends RecyclerView.Adapter<Adapter_home_quiz.ViewHolder> {
    private Cons_quiz_home[] list_chapter;
    private Context context;

    public Adapter_home_quiz(Cons_quiz_home[] list_chapter, Context context) {
        this.list_chapter = list_chapter;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View v=layoutInflater.inflate(R.layout.layout_adapter_quiz_home,viewGroup,false);
        Adapter_home_quiz.ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.main_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Quiz_screen.class).putExtra("position",i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout main_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            main_layout=itemView.findViewById(R.id.layout);
        }
    }

}
