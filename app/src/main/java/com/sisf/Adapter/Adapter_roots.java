package com.sisf.Adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sisf.Activity.Roots_Details;
import com.sisf.Pojo.Cons_roots;
import com.sisf.R;

public class Adapter_roots extends RecyclerView.Adapter<Adapter_roots.ViewHolder>{

    private Cons_roots[] listdata;
    Context con;
    // RecyclerView recyclerView;
    public Adapter_roots(Cons_roots[] listdata,Context con) {
        this.con=con;
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.layout_roots_details, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Cons_roots myListData = listdata[position];
        holder.desc.setText(listdata[position].getDescription());
        holder.title.setText(listdata[position].getTitle());
        holder.name.setText(listdata[position].getName());

        if (holder.title.getText().toString().isEmpty())
        {
            holder.desc.setMaxLines(4);
            holder.title.setVisibility(View.GONE);

        }else{
            holder.desc.setMaxLines(3);
            holder.title.setVisibility(View.VISIBLE);
        }

        holder.imageView.setImageResource(listdata[position].getImgId());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)con).startActivity(new Intent(con.getApplicationContext(), Roots_Details.class).putExtra("position",position));
     //           Toast.makeText(view.getContext(),myListData.getName(),Toast.LENGTH_LONG).show();

            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView title,name,desc;
        public LinearLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.title = (TextView) itemView.findViewById(R.id.txt_title);
            this.name = (TextView) itemView.findViewById(R.id.txt_name);
            this.desc = (TextView) itemView.findViewById(R.id.txt_desc);
            relativeLayout = (LinearLayout)itemView.findViewById(R.id.linearlayout);
        }
    }
}
