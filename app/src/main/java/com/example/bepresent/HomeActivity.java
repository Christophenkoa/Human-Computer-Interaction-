package com.example.bepresent;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {
    private TextView mTextView;
    public Example mPosts = null;
    public List<String> mContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTextView = findViewById(R.id.text_view_result);
        bindData();
       // getData();
    }

    public void bindData() {
        mContents = null;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call <Example> call = jsonPlaceHolderApi.getExamples();

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(!response.isSuccessful()) {
                    mTextView.setText("code : "+response.code());
                    return;
                }


                mPosts = response.body();


                if (mPosts != null) {
                    for(Content article : mPosts.getContent()) {
                        String content = "";
                        content = "Text : " + article.getDescription() +"\n\n";
                        mTextView.append(content);
                        mContents.add(content);
                    }
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
                return;

            }
        });
    }

    private void getData() {
        final Call<Example> example = EventsAPI.getService().getExamples();
        example.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(HomeActivity.this, "ERRRRRR", Toast.LENGTH_SHORT).show();

                    return;
                }
                String text = "" + response.body().getContent().size();
                mTextView.setText(text);

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }
}