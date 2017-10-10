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
 * Created by a2881dante on 10.08.2017.
 */

public class PointBoardAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<Team> teams;

    public PointBoardAdapter(Context context, ArrayList<Team> teams){
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

    public Team getTeam(int i){
        return (Team)getItem(i);
    }

    @Override
    public long getItemId(int i) {
        return getTeam(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = inflater.inflate(R.layout.item_point_board, viewGroup, false);
        }
        ((TextView)view.findViewById(R.id.itb_team_name)).setText(getTeam(i).getName());
        ((TextView)view.findViewById(R.id.itb_team_point))
                .setText(Integer.toString(getTeam(i).getCnt()));
        return view;
    }
}
