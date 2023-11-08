package com.example.newssurge.Models;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.newssurge.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {
    Context context;

    //configure the retrofit
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public interface CallNewsApi {
        @GET("top-headlines")
        Call<NewsApiResponse> callHeadlines(
                @Query("country") String country,
                @Query("category") String category,
                @Query("q") String query,
                @Query("apiKey") String api_key

        );
    }
  public void getNewsHeadlines(OnFetchDataListener onFetchDataListener,String category,String query){
      CallNewsApi callNewsApi=retrofit.create(CallNewsApi.class);
      Call<NewsApiResponse> call=callNewsApi.callHeadlines("us",category,query, context.getString(R.string.api_key));
      try{
            call.enqueue(new Callback<NewsApiResponse>() {
                @Override
                public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context, "Error response", Toast.LENGTH_SHORT).show();
                }
                else{
                    onFetchDataListener.onFetchData(response.body().getArticles(),response.message());
                }
                }

                @Override
                public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                   // Log.d("TAG", "onFailure: the error is "+t.getMessage());
                    onFetchDataListener.onError("Request Field");
                }
            });
      }
      catch (Exception e){
          Log.e("TAG", "getNewsHeadlines: error",e );
      }
  }

    public RequestManager(Context context) {
        this.context = context;
    }


}
