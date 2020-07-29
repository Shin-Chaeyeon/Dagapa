package com.example.dagapa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ContractAdd_step04_2 extends AppCompatActivity {

    String contract_info_lender = "";
    String contract_info_borrower = "";
    int contract_info_type = -1; // 초기화
    String contract_info_goods = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractadd_step04_2);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        contract_info_lender = bundle.getString("contract_info_lender");
        contract_info_borrower = bundle.getString("contract_info_borrower");
        contract_info_type = bundle.getInt("contract_info_type");

        final EditText goods_edit = findViewById(R.id.goods_edit);

        Button next_04_2 = findViewById(R.id.next_04_2);
        next_04_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ContractAdd_step05.class);
                contract_info_goods = goods_edit.getText().toString();
                intent.putExtra("contract_info_lender", contract_info_lender);
                intent.putExtra("contract_info_borrower", contract_info_borrower);
                intent.putExtra("contract_info_type", contract_info_type);
                intent.putExtra("contract_info_goods", contract_info_goods);
                // 음성 파일 넘어가는 코드 만들기 intent.putExtra("");
                startActivity(intent);
            }
        });
    }
}
