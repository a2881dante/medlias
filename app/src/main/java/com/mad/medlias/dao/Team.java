package com.mad.medlias.dao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by a2881dante on 27.07.2017.
 */

public class Team implements Parcelable{

    private int id;
    private String name;
    private int cnt;

    public Team() {
        this.id = 0;
        this.name = "";
        this.cnt = 0;
    }

    public Team(int id, String name) {
        this.id = id;
        this.name = name;
        this.cnt = 0;
    }

    public Team(int id, String name, int cnt) {
        this.id = id;
        this.name = name;
        this.cnt = cnt;
    }

    protected Team(Parcel in) {
        id = in.readInt();
        name = in.readString();
        cnt = in.readInt();
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(cnt);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
