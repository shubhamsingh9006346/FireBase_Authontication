package com.example.bittu.firebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SecondOne extends AppCompatActivity
{
    Button button;
    TextView textViewUserEmail;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListner;

    @Override
    protected void onStart()
    {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListner);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_one);
        button = (Button) findViewById(R.id.logoutbtn);
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        mAuth = FirebaseAuth.getInstance();

        mAuthListner = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                if(firebaseAuth.getCurrentUser() == null)
                {
                    startActivity(new Intent(SecondOne.this,MainActivity.class));
                }
            }
        };
        textViewUserEmail.setText("welcome: "+ textViewUserEmail.getId());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            mAuth.signOut();
            }
        });
    }
}
