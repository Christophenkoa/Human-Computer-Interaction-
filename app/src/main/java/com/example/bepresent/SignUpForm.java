package com.example.bepresent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpForm extends AppCompatActivity {
    private EditText mTextEmail;
    private EditText mPassword;
    private EditText mUserName;
    private EditText mFullName;
    private EditText mConfirmPassword;
    private Button mRegister;
    private Button mLogin;
    BePresentOpenHelper db;
    private TextView mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        db = new BePresentOpenHelper(this);
        mTextEmail = findViewById(R.id.sign_up_email);
        mUserName = findViewById(R.id.sign_up_user_name);
        mFullName = findViewById(R.id.sign_up_full_name);
        mPassword = findViewById(R.id.sign_up_password);
        mConfirmPassword = findViewById(R.id.sign_up_confirm);
        mRegister = findViewById(R.id.register);
        mLogin = findViewById(R.id.login_from_sign_up);
        mLocation = findViewById(R.id.sign_up_location);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToRegister = new Intent(SignUpForm.this, LoginForm.class );
                startActivity(moveToRegister);
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = mUserName.getText().toString().trim();
                String fullName = mFullName.getText().toString().trim();
                String email = mTextEmail.getText().toString().trim();
                String pwd = mPassword.getText().toString().trim();
                String location = mLocation.getText().toString().trim();
                String cnf_pwd = mConfirmPassword.getText().toString().trim();

                if(pwd.equals(cnf_pwd)) {
                    long val = db.addUser(fullName, userName, email,pwd, location);
                    if(val >0) {
                        Toast.makeText(SignUpForm.this, "You have registered", Toast.
                                LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(SignUpForm.this,LoginForm.class);
                        startActivity(moveToLogin);

                    }else {
                        Toast.makeText(SignUpForm.this, "Registration Error", Toast.LENGTH_SHORT).show();
                    }


                }else {
                    Toast.makeText(SignUpForm.this, "Password is not matching", Toast.
                            LENGTH_SHORT).show();
                }
            }
        });
    }

}