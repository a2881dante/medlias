package com.mad.medlias.dao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by a2881dante on 31.07.2017.
 */

public class GamePreferences implements Parcelable{

    public static final int TIME_MIN = 30;
    public static final int ROUNDS_MIN = 1;

    private int timePref;
    private int roundsPref;
    private int levelPref;
    private int round;

    public GamePreferences() {}

    public GamePreferences(int timePref, int roundsPref, int levelPref) {
        this.timePref = timePref;
        this.roundsPref = roundsPref;
        this.levelPref = levelPref;
        this.round = 1;
    }


    protected GamePreferences(Parcel in) {
        timePref = in.readInt();
        roundsPref = in.readInt();
        levelPref = in.readInt();
        round = in.readInt();
    }

    public int getTimePref() {
        return timePref;
    }

    public void setTimePref(int timePref) {
        this.timePref = timePref;
    }

    public int getRoundsPref() {
        return roundsPref;
    }

    public void setRoundsPref(int roundsPref) {
        this.roundsPref = roundsPref;
    }

    public int getLevelPref() {
        return levelPref;
    }

    public void setLevelPref(int levelPref) {
        this.levelPref = levelPref;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(timePref);
        dest.writeInt(roundsPref);
        dest.writeInt(levelPref);
        dest.writeInt(round);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GamePreferences> CREATOR = new Creator<GamePreferences>() {
        @Override
        public GamePreferences createFromParcel(Parcel in) {
            return new GamePreferences(in);
        }

        @Override
        public GamePreferences[] newArray(int size) {
            return new GamePreferences[size];
        }
    };
}
