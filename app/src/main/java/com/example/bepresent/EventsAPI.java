package com.example.bepresent;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class EventsAPI {
    private static final String url = "https://newsapi.org/v2/";
    private static final String headLine = "sources?apiKey=1a852f9f31304f4499c57402dfe07fd0";

    public static PostService postService= null;

    public static PostService getService() {
        if(postService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            postService = retrofit.create(PostService.class);
        }
        return postService;
    }

    public interface PostService {

        @GET(headLine)
        Call<Example> getExamples();
    }
}
