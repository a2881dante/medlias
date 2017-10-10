package com.mad.medlias.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mad.medlias.R;
import com.mad.medlias.dao.Team;

import java.util.ArrayList;

/**
 * Created by a2881dante on 12.08.2017.
 */

public class WinnerAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Team> teams;

    public WinnerAdapter(Context context, ArrayList<Team> teams) {
        this.context = context;
        this.teams = teams;
        this.inflater = (LayoutInflater)context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return teams.size();
    }

    @Override
    public Object getItem(int i) {
        return teams.get(i);
    }

    private Team getTeam(int i){
        return teams.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = inflater.inflate(R.layout.item_winner, viewGroup, false);
        }
        ((TextView)view.findViewById(R.id.iw_place)).setText(Integer.toString(i+1));
        ((TextView)view.findViewById(R.id.iw_name)).setText(getTeam(i).getName());
        ((TextView)view.findViewById(R.id.iw_count)).setText(Integer
                .toString(getTeam(i).getCnt()));
        return view;
    }

}
