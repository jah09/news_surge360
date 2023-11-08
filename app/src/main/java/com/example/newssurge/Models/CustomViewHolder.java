package com.example.newssurge.Models;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newssurge.R;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    TextView textViewTitle,textViewSource;
    ImageView imageViewHeadlineImage;
    CardView cardView;


    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        //assign from the XML to backend
        textViewTitle=itemView.findViewById(R.id.text_title);
        textViewSource=itemView.findViewById(R.id.text_source);
        imageViewHeadlineImage=itemView.findViewById(R.id.image_headline);
        cardView=itemView.findViewById(R.id.main_container);


        Animation animation= AnimationUtils.loadAnimation(cardView.getContext(), R.anim.fade_in);
        itemView.startAnimation(animation);
    }

}
