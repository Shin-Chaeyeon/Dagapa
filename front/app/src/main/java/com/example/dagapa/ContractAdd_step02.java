package com.example.dagapa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ContractAdd_step02 extends AppCompatActivity {

    String contract_info_lender = "";
    String contract_info_borrower = "";
    String contract_info_other_person = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractadd_step02);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        contract_info_lender = bundle.getString("contract_info_lender");
        contract_info_borrower = bundle.getString("contract_info_borrower");

        final EditText target_person = findViewById(R.id.target_person);

        Button next_02 = findViewById(R.id.next_02);
        next_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contract_info_other_person = target_person.getText().toString();
                if (contract_info_lender.equals("")) {  // 임대인이 null이면 other_person이 임대인이 되면 된다.
                    contract_info_lender = contract_info_other_person;
                } else {
                    contract_info_borrower = contract_info_other_person;
                }

                Intent intent = new Intent(getApplicationContext(), ContractAdd_step03.class);
                intent.putExtra("contract_info_lender", contract_info_lender);
                intent.putExtra("contract_info_borrower", contract_info_borrower);

                startActivity(intent);
            }
        });
    }
}

