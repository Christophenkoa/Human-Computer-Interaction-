package com.example.bepresent;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.core.content.ContextCompat.startActivity;

public class EventsAdapter extends RecyclerView.Adapter {

    private TextView mTextView;
    private Example mPosts;
    private int mLength;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item , parent , false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((EventViewHolder) holder).bindView(position);

    }

    @Override
    public int getItemCount() {
        return 100; //try to change it later
    }

    private class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mItemTitle;
        private TextView mItemDescription;
        private TextView mItemUrl;

        public EventViewHolder(@NonNull View itemView) {

            super(itemView);

            mItemDescription = (TextView) itemView.findViewById(R.id.description_item);
            mItemTitle = (TextView) itemView.findViewById(R.id.title);
            mItemUrl = (TextView) itemView.findViewById(R.id.url_item);
            mItemUrl.setOnClickListener(this);
        }


        public void bindView (final int position) {


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
            Call<Example> call = jsonPlaceHolderApi.getExamples();

            call.enqueue(new Callback<Example>() {
                @Override
                public void onResponse(Call<Example> call, Response<Example> response) {
                    if(!response.isSuccessful()) {
                        mTextView.setText("code : "+response.code());
                        return;
                    }


                    mPosts = response.body();
                    mLength = mPosts.getContent().size();

                    mItemTitle.setText(mPosts.getContent().get(position).getName());
                    mItemDescription.setText(mPosts.getContent().get(position).getDescription());
                    mItemUrl.setText(mPosts.getContent().get(position).getUrl());

                }

                @Override
                public void onFailure(Call<Example> call, Throwable t) {
                    return;

                }
            });
        }

        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(String.valueOf(mItemUrl)));
            startActivity(view.getContext() , intent,null);
        }
    }
}
