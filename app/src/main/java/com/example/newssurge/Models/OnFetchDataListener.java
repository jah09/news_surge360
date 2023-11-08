package com.example.newssurge.Models;

import com.example.newssurge.Models.NewsHeadlines;

import java.util.List;

public interface OnFetchDataListener <NewsApiResponse>{
    void onFetchData(List<NewsHeadlines> list,String message);
    void onError(String message);
}
