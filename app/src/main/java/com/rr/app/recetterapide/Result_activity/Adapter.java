package com.rr.app.recetterapide.Result_activity;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.rr.app.recetterapide.R;

import java.util.HashMap;
import java.util.List;

public class Adapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> GroupData;
    private HashMap<String, List<String>> ChildData;

    public Adapter(Context context, List<String> GroupData, HashMap<String, List<String>> ChildData){
        this.context = context;
        this.GroupData = GroupData;
        this.ChildData = ChildData;
    }

    @Override
    public Object getChild(int i, int i1) {
        return ChildData.get(GroupData.get(i)).get(i1);
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public int getChildrenCount(int i) {
        return ChildData.get(this.GroupData.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return GroupData.get(i);
    }

    @Override
    public int getGroupCount() {
        return GroupData.size();
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        String headerTitle = (String) getGroup(i);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = view.findViewById(R.id.ListGroup);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {


        String childText = (String) getChild(i, i1);

        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView Child = view.findViewById(R.id.lListItem);
        Child.setText(childText);
        return view;
    }

}
