package com.example.dagapa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ContractAdd_step01 extends AppCompatActivity {

    String contract_info_lender = "";
    String contract_info_borrower = "";
    //step08에서 MainActivity로 userID를 보내기 위한 전역 변수
    public static String contract_info_me ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractadd_step01);


        Intent intent = getIntent();
        contract_info_me = intent.getExtras().getString("userID");

        Button lender_button = findViewById(R.id.lender_button);
        lender_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contract_info_lender = contract_info_me;
                Intent intent = new Intent(getApplicationContext(), ContractAdd_step02.class);
                intent.putExtra("contract_info_lender", contract_info_lender);
                intent.putExtra("contract_info_borrower", contract_info_borrower);
                startActivity(intent);
            }
        });

        Button borrow_button = findViewById(R.id.borrow_button);
        borrow_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contract_info_borrower = contract_info_me;
                Intent intent = new Intent(getApplicationContext(), ContractAdd_step02.class);
                intent.putExtra("contract_info_lender", contract_info_lender);
                intent.putExtra("contract_info_borrower", contract_info_borrower);
                startActivity(intent);
            }
        });
    }
}