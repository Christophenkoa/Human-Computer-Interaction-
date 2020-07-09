package com.example.bepresent;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    static final String headLine = "sources?apiKey=1a852f9f31304f4499c57402dfe07fd0";

    @GET(headLine)
    Call<Example> getExamples();
}
