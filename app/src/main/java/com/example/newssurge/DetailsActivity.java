package com.example.newssurge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newssurge.Models.NewsHeadlines;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    NewsHeadlines headlines;
    TextView textViewTitle,textViewAuthor,textViewTime,textViewDetails,textViewContent;
    ImageView imageViewNewsImage,imageViewBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        overridePendingTransition(R.anim.slide_in_left,0);



        textViewTitle=findViewById(R.id.text_details_title);
        textViewAuthor=findViewById(R.id.text_details_author);
        textViewTime=findViewById(R.id.text_details_time);
        textViewDetails=findViewById(R.id.text_details_bodydetails);
        textViewContent=findViewById(R.id.text_details_content);
        imageViewNewsImage=findViewById(R.id.imageDetailsnews);
        imageViewBackBtn=findViewById(R.id.backBtn);
        imageViewBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailsActivity.this,Homepage.class));
            }
        });

        //received the data from prev activity
        headlines= (NewsHeadlines) getIntent().getSerializableExtra("data");

        textViewTitle.setText(headlines.getTitle());
        textViewAuthor.setText(headlines.getAuthor());
        textViewTime.setText(headlines.getPublishedAt());
        textViewDetails.setText(headlines.getDescription());
        textViewContent.setText(headlines.getContent());
        Picasso.get().load(headlines.getUrlToImage()).into(imageViewNewsImage);
    }


}