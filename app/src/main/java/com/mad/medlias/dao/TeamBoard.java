package com.mad.medlias.dao;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by a2881dante on 30.07.2017.
 */

public class TeamBoard implements Parcelable {

    private ArrayList<Team> teams;
    private int turn;

    public TeamBoard(ArrayList<Team> teams){
        this.teams = teams;
        this.turn = 0;
    }

    private TeamBoard(Parcel in){
        teams = in.readArrayList(getClass().getClassLoader());
        turn = in.readInt();
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public int getSize(){
        return teams.size();
    }

    public Team getTeam(int i){
        return teams.get(i);
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public boolean nextTurn(){
        turn++;
        if(turn == getSize()){
            turn = 0;
            return true;
        }
        return false;
    }

    public static final Creator<TeamBoard> CREATOR = new Creator<TeamBoard>() {
        @Override
        public TeamBoard createFromParcel(Parcel in) {
            return new TeamBoard(in);
        }

        @Override
        public TeamBoard[] newArray(int size) {
            return new TeamBoard[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(teams);
        parcel.writeInt(turn);
        /*parcel.writeInt(teams.size());
        for (int j = 0; j < teams.size(); j++) {
            parcel.writeParcelable(teams.get(i), Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
        }*/
    }

}
