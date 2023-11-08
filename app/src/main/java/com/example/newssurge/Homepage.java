package com.example.newssurge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.newssurge.Models.CustomAdapter;
import com.example.newssurge.Models.NewsApiResponse;
import com.example.newssurge.Models.NewsHeadlines;
import com.example.newssurge.Models.OnFetchDataListener;
import com.example.newssurge.Models.RequestManager;
import com.example.newssurge.Models.SelectListener;

import java.util.List;

public class Homepage extends AppCompatActivity implements SelectListener, View.OnClickListener {

    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    ProgressDialog progressDialog;
    Button buttonBusiness, buttonEntertainment, buttonGeneral, buttonHealth, buttonScience, buttonSports, buttonTechnology;
   SearchView searchView;

   ImageView imageSearchBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);


        //Show an progress bar upon loading the screen
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Retrieving news article");
        progressDialog.show();

        //assign the view from XML to backend
        searchView = findViewById(R.id.search_view);
        searchView.setQueryHint(Html.fromHtml("<font color = #FADBE9>" + getResources().getString(R.string.hintSearchMess) + "</font>"));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("Retrieving news article of " + query);
                progressDialog.show();
                RequestManager requestManager = new RequestManager(Homepage.this);
                requestManager.getNewsHeadlines(listener, "general", query);
                return true;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        buttonBusiness = findViewById(R.id.btnBusiness);
        buttonBusiness.setOnClickListener(this);

        buttonEntertainment = findViewById(R.id.btnEntertainment);
        buttonEntertainment.setOnClickListener(this);

        buttonGeneral = findViewById(R.id.btnGeneral);
        buttonGeneral.setOnClickListener(this);

        buttonHealth = findViewById(R.id.btnHealth);
        buttonHealth.setOnClickListener(this);

        buttonScience = findViewById(R.id.btnScience);
        buttonScience.setOnClickListener(this);

        buttonSports = findViewById(R.id.btnSports);
        buttonSports.setOnClickListener(this);

        buttonTechnology = findViewById(R.id.btnTechnology);
        buttonTechnology.setOnClickListener(this);


        //initiate to get the data from Request Manager
        RequestManager requestManager = new RequestManager(this);
        requestManager.getNewsHeadlines(listener, "general", null);

    }

    private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            if (list.isEmpty()) {
                Toast.makeText(Homepage.this, "No data found", Toast.LENGTH_SHORT).show();
            } else {
                showNews(list);
                progressDialog.dismiss();
            }


        }

        @Override
        public void onError(String message) {
            Toast.makeText(Homepage.this, "An error occured.", Toast.LENGTH_SHORT).show();
        }
    };

    private void showNews(List<NewsHeadlines> list) {
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        customAdapter = new CustomAdapter(this, list, this);
        recyclerView.setAdapter(customAdapter);
    }

    @Override
    public void onNewsClick(NewsHeadlines headlines) {
        startActivity(new Intent(Homepage.this, DetailsActivity.class)
                .putExtra("data", headlines));
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String category = button.getText().toString();
        progressDialog.setTitle("Retrieving news article of " + category);
        progressDialog.show();
        RequestManager requestManager = new RequestManager(this);
        requestManager.getNewsHeadlines(listener, category, null);

    }
}