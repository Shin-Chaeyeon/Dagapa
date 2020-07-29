package com.example.dagapa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ContractAdd_step03 extends AppCompatActivity {

    String contract_info_lender = "";
    String contract_info_borrower = "";
    int contract_info_type = -1; // 초기화

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractadd_step03);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        contract_info_lender = bundle.getString("contract_info_lender");
        contract_info_borrower = bundle.getString("contract_info_borrower");

        Button goods_button = findViewById(R.id.goods_button);
        goods_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contract_info_type = 1;
                Intent intent = new Intent(getApplicationContext(), ContractAdd_step04_1.class);
                intent.putExtra("contract_info_lender", contract_info_lender);
                intent.putExtra("contract_info_borrower", contract_info_borrower);
                intent.putExtra("contract_info_type",contract_info_type);

                startActivity(intent);
            }
        });

        Button money_button = findViewById(R.id.money_button);
        money_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contract_info_type = 2;
                Intent intent = new Intent(getApplicationContext(), ContractAdd_step04_2.class);
                intent.putExtra("contract_info_lender", contract_info_lender);
                intent.putExtra("contract_info_borrower", contract_info_borrower);
                intent.putExtra("contract_info_type",contract_info_type);

                startActivity(intent);
            }
        });
}
}


