package com.mad.medlias.app;

import android.support.annotation.Nullable;

import com.mad.medlias.dao.Team;
import com.mad.medlias.dao.Word;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Random;

/**
 * Created by a2881dante on 27.07.2017.
 */

public class DataManager {

    public static String getRandomUniqElement(ArrayList<String> list){
        Random random = new Random();
        int randomInt = random.nextInt(list.size());
        String element = list.get(randomInt);
        list.remove(randomInt);
        return element;
    }

    @Nullable
    public static Word getRandomUniqWord(ArrayList<Word> list){
        Random random = new Random();
        if(list.size() > 0) {
            int randomInt = random.nextInt(list.size());
            Word element = list.get(randomInt);
            list.remove(randomInt);
            return element;
        }else{
            return null;
        }
    }

    public static ArrayList<Team> sortTeam(ArrayList<Team> teams){
        Object[] arrTeam = teams.toArray();
        for(int i=0; i<arrTeam.length; i++){
            for(int j=i; j< arrTeam.length; j++){
                if(((Team)arrTeam[i]).getCnt()
                        <((Team)arrTeam[j]).getCnt()){
                    Object temp = arrTeam[i];
                    arrTeam[i] = arrTeam[j];
                    arrTeam[j] = temp;
                }
            }
        }
        ArrayList<Team> winners = new ArrayList<>();
        for(int i=0; i<arrTeam.length; i++){
            winners.add((Team)arrTeam[i]);
        }
        return winners;
    }

}
