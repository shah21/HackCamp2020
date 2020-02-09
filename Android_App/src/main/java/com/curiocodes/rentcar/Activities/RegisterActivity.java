package com.curiocodes.rentcar.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.curiocodes.rentcar.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEmail,mPass,mConfPass;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //init
        mEmail = findViewById(R.id.email);
        mPass = findViewById(R.id.pass);
        mConfPass = findViewById(R.id.conf_pass);
        progressBar = findViewById(R.id.my_progressBar);

        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString();
                final String pass = mPass.getText().toString();
                final String confPass = mConfPass.getText().toString();

                if (email.isEmpty() && pass.isEmpty() && confPass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Check fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.equals(confPass)) {
                        progressBar.setVisibility(View.VISIBLE);
                        //new SendPostRequest().execute(email,pass);
                        FirebaseAuth mAuth = FirebaseAuth.getInstance();
                        mAuth.createUserWithEmailAndPassword(email, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        });
                    } else {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(), "Passwords are not identical", Toast.LENGTH_SHORT);
                    }
                }
            }
        });

        findViewById(R.id.go_to_log).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }

}
