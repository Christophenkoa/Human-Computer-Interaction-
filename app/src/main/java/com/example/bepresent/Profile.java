package com.example.bepresent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {
    BePresentOpenHelper db;
    private TextView mFullName;
    private TextView mUserName;
    private TextView mUserEmail;
    private TextView mLocation;
    private FrameLayout mBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);

        db = new BePresentOpenHelper(this);

        mFullName = findViewById(R.id.profile_full_name);
        mUserName = findViewById(R.id.profile_user_name);
        mUserEmail = findViewById(R.id.profile_email);
        mLocation = findViewById(R.id.profil_location);
        mBackButton = findViewById(R.id.bounded_rounded);

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this , MainActivity1.class));
            }
        });

        setValue();
    }

    private void setValue() {
        mFullName.setText((String) MainActivity1.mAccount.getFullName());
        mUserName.setText((String) MainActivity1.mAccount.getUserName());
        mUserEmail.setText((String) MainActivity1.mAccount.getEmail());
        mLocation.setText((String) MainActivity1.mAccount.getLocation());
    }
}