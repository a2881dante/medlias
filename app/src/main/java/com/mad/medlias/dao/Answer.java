package com.mad.medlias.dao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by a2881dante on 11.08.2017.
 */

public class Answer implements Parcelable {

    private String name;
    private boolean isTrue;

    public Answer() {}

    public Answer(String name, boolean isTrue) {
        this.name = name;
        this.isTrue = isTrue;
    }

    protected Answer(Parcel in) {
        name = in.readString();
        isTrue = in.readByte() != 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }

    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeByte((byte) (isTrue ? 1 : 0));
    }
}
