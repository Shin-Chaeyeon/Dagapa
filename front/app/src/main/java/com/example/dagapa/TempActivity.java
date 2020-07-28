package com.example.dagapa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class TempActivity extends AppCompatActivity {

    String got_ID;
    String got_NAME;

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        textView = findViewById(R.id.tempTV);

        Intent intent = getIntent();
        processIntent(intent);

    }

    private void processIntent(Intent intent) {
        if(intent !=null){
            got_ID = intent.getExtras().getString("userID");
            got_NAME = intent.getExtras().getString("userName");
            textView.setText(got_ID + " , " + got_NAME);
            Log.d("INTENT", "LOGIN에서 받은 DATA : " + got_ID + " / " + got_NAME);
        }
    }//end processIntent method
}