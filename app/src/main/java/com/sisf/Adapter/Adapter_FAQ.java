package com.sisf.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.sisf.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Adapter_FAQ extends BaseExpandableListAdapter {
    Context con;
    List<String>list_title;
    HashMap<String,List<String>> list_details;

    public Adapter_FAQ(Context con, List<String> list_title, HashMap<String, List<String>> list_details) {
        this.con = con;
        this.list_title = list_title;
        this.list_details = list_details;
    }


    @Override
    public int getGroupCount() {
        return list_title.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return list_details.get(this.list_title.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return list_title.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return list_details.get(this.list_title.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int listPosition, boolean b, View convertView, ViewGroup viewGroup) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.con.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.layout_list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public View getChildView(int listPosition, int expandedListPosition, boolean b, View convertView, ViewGroup viewGroup) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.con
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.layout_list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
