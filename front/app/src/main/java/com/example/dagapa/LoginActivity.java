package com.example.dagapa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText id = null;
    EditText password = null;
    Button btn_login = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id = findViewById(R.id.id);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.login);

        btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String get_id = id.getText().toString();
                String get_pw = password.getText().toString();

                 Log.d("id info : " , get_id);
                 Log.d("pw info : ", get_pw);

                 Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                 Contract p = new Contract("jihwan", "1");
                 intent.putExtra("personInfo", p);
                 startActivityForResult(intent, 100);
            }
        });
    }
}