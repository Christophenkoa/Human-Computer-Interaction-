package com.example.bepresent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginForm extends AppCompatActivity {
    private EditText mTextEmail;
    private EditText mTextPassword;
    private Button mLogin;
    private Button mRegister;
    BePresentOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        db = new BePresentOpenHelper(this);

        mLogin = findViewById(R.id.login);
        mTextEmail = findViewById(R.id.login_email);
        mTextPassword = findViewById(R.id.login_password);
        mRegister = findViewById(R.id.register);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToRegister = new Intent(LoginForm.this, SignUpForm.class );
                startActivity(moveToRegister);
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mTextEmail.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                Boolean res = db.checkUser(email,pwd);
                if(res == true) {
                    Toast.makeText(LoginForm.this, "Successfully logged In", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginForm.this, MainActivity.class));
                }else {
                    Toast.makeText(LoginForm.this, "Login Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}