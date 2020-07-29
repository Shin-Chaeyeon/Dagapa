package com.example.dagapa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ContractAdd_step06 extends AppCompatActivity {

    String contract_info_lender = "";
    String contract_info_borrower = "";
    int contract_info_type = -1; // 초기화
    String contract_info_goods = "";
    String contract_info_startdate = "";
    String contract_info_duedate = "";
    String contract_info_description = "";
    byte[] byteArray;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractadd_step06);

        Intent intent = getIntent();

        // 텍스트 받아오기
        Bundle bundle = intent.getExtras();
        contract_info_lender = bundle.getString("contract_info_lender");
        contract_info_borrower = bundle.getString("contract_info_borrower");
        contract_info_type = bundle.getInt("contract_info_type");
        contract_info_goods = bundle.getString("contract_info_goods");
        contract_info_startdate = bundle.getString("contract_info_startdate");
        contract_info_duedate = bundle.getString("contract_info_duedate");

        // 이미지 받아오기
        byteArray = intent.getByteArrayExtra("image");

        final EditText description_edit = findViewById(R.id.description_edit);

        Button next_06 = findViewById(R.id.next_06);
        next_06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ContractAdd_step07.class);
                contract_info_description = description_edit.getText().toString();
                intent.putExtra("contract_info_lender", contract_info_lender);
                intent.putExtra("contract_info_borrower", contract_info_borrower);
                intent.putExtra("contract_info_type", contract_info_type);
                intent.putExtra("contract_info_goods", contract_info_goods);
                intent.putExtra("contract_info_startdate", contract_info_startdate);
                intent.putExtra("contract_info_duedate", contract_info_duedate);
                intent.putExtra("contract_info_description", contract_info_description);
                intent.putExtra("image", byteArray);

                startActivity(intent);
            }
        });

    }

}
