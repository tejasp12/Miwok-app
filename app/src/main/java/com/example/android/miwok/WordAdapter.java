package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    private int mcolorResourceID;
    public WordAdapter(Activity context, ArrayList<Word> words,int colorResourceID) {
        super(context, 0, words);
        mcolorResourceID = colorResourceID;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Word currentWord = getItem(position);
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.one);
        defaultTextView.setText(currentWord.getDefaultTranslation());
        TextView miwokTranslation = (TextView) listItemView.findViewById(R.id.lutti);
        miwokTranslation.setText(currentWord.getMiwokTranslation());
        ImageView wordImage = (ImageView)listItemView.findViewById(R.id.word_Image);
        if(currentWord.hasImage()){
            wordImage.setImageResource(currentWord.getImageResourceID());
            wordImage.setVisibility(View.VISIBLE);
        }else {
            wordImage.setVisibility(View.GONE);
        }
        //Set the theme color for the list item.
        View textContainer = listItemView.findViewById(R.id.text_container);
        //Find the color that the resource ID maps to.
        int color = ContextCompat.getColor(getContext(),mcolorResourceID);
        //Set the backgroud color of the text container view.
        textContainer.setBackgroundColor(color);
        return listItemView;
    }
}
