package com.kbds.front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URL;
import java.net.URLDecoder;

import javax.net.ssl.HttpsURLConnection;

import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginbtn = findViewById(R.id.signinbtn);
        EditText id = findViewById(R.id.idinput);
        EditText pw = findViewById(R.id.pwinput);
        User userToCheck = new User();
        userToCheck.setId(id.getText().toString());
        userToCheck.setPw(id.getText().toString());

        checkUser(userToCheck);
    }
    //https://ourcstory.tistory.com/51 보고하는중
    private void checkUser(User userToCheck) {

    }

}