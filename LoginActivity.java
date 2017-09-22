package com.example.bittu.firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    Button buttonSignin;
    FirebaseAuth mAuth;
    EditText editTextEmail,editTextPassword;
    TextView textViewsignup;
    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!= null)
        {
            finish();
            startActivity(new Intent(LoginActivity.this,SecondOne.class));
        }
        buttonSignin = (Button) findViewById(R.id.buttonSignin);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewsignup = (TextView) findViewById(R.id.textViewsignup);

        buttonSignin.setOnClickListener(this);
        textViewsignup.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);

    }
    public void userlogin()
    {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(LoginActivity.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(LoginActivity.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("please wait ........");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful())
                        {
                            //start login Activity
                            finish();
                            startActivity(new Intent(LoginActivity.this,SecondOne.class));
                            Toast.makeText(LoginActivity.this,"WELCOME: " ,Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view)
    {
        if(view== buttonSignin)
        {
            userlogin();
        }
        if(view==textViewsignup)
        {
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }



    }
}
