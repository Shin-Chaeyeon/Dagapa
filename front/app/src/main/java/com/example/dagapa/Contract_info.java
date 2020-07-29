package com.example.dagapa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Contract_info extends AppCompatActivity {

    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_info);

        info = findViewById(R.id.info);

        Intent intent = getIntent();
        Contract contract = (Contract) intent.getSerializableExtra("contractInfo");
        info.setText(contract.toString());

    }
}