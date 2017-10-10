package com.mad.medlias.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mad.medlias.R;
import com.mad.medlias.dao.Answer;

import java.util.ArrayList;

/**
 * Created by a2881dante on 11.08.2017.
 */

public class EditAnswerAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Answer> answers;

    public EditAnswerAdapter(Context context, ArrayList<Answer> answers) {
        this.context = context;
        this.answers = answers;
        this.inflater = (LayoutInflater)context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return answers.size();
    }

    @Override
    public Object getItem(int i) {
        return answers.get(i);
    }

    public Answer getAnswer(int i){
        return (Answer)getItem(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int pos = i;
        if(view == null){
            view = inflater.inflate(R.layout.item_edit_answer, viewGroup, false);
        }
        ((TextView)view.findViewById(R.id.iea_word)).setText(getAnswer(i).getName());
        int resImg;
        if(getAnswer(i).isTrue()){
            resImg = R.drawable.ic_check_box;
        }else{
            resImg = R.drawable.ic_check_box_outline_blank;
        }
        ((ImageView)view.findViewById(R.id.iea_check)).setImageResource(resImg);
        view.findViewById(R.id.iea_check)
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int resImg;
                if(getAnswer(pos).isTrue()){
                    getAnswer(pos).setTrue(false);
                    resImg = R.drawable.ic_check_box_outline_blank;
                }else{
                    getAnswer(pos).setTrue(true);
                    resImg = R.drawable.ic_check_box;
                }
                ((ImageView)view).setImageResource(resImg);
            }
        });
        return view;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

}
