package com.mad.medlias.dao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by a2881dante on 11.08.2017.
 */

public class Word implements Parcelable{

    private int id;
    private String word;

    public Word() {}

    public Word(int id, String word) {
        this.id = id;
        this.word = word;
    }

    protected Word(Parcel in) {
        id = in.readInt();
        word = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(word);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Word> CREATOR = new Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
