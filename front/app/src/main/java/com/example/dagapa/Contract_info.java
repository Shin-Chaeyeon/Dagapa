package com.example.dagapa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Contract_info extends AppCompatActivity {

    TextView lender;
    TextView borrower;
    TextView startDate;
    TextView dueDate;
    TextView goods;
    ImageView image;
    ImageView imageMoney;
    TextView imageInfo;

    TextView goodsment;
    TextView moneyment;

    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_info);

        lender = findViewById(R.id.lender_edit);
        borrower = findViewById(R.id.borrower_edit);
        startDate = findViewById(R.id.startdate_edit);
        dueDate = findViewById(R.id.duedate_edit);
        goods = findViewById(R.id.goods_edit);
        image = findViewById(R.id.imageView);
        imageInfo = findViewById(R.id.imageInfo);
        imageMoney = findViewById(R.id.money_img);

        goodsment = findViewById(R.id.goods_ment);
        moneyment = findViewById(R.id.money_ment);

        Intent intent = getIntent();
        Contract contract = (Contract) intent.getSerializableExtra("contractInfo");

        type = contract.getType();


        lender.setText(contract.getLender());
        borrower.setText(contract.getBorrower());
        startDate.setText(contract.getStartdate());
        dueDate.setText(contract.getDuedate());
        goods.setText(contract.getGoods());

        if(type==1){
            // 물품
            image.setVisibility(View.VISIBLE);
            imageInfo.setVisibility(View.VISIBLE);
            goodsment.setVisibility(View.VISIBLE);
            moneyment.setVisibility(View.GONE);
            imageMoney.setVisibility(View.GONE);
        }
        else{
            // 금전
            imageMoney.setVisibility(View.VISIBLE);
            image.setVisibility(View.GONE);
            imageInfo.setVisibility(View.GONE);
            moneyment.setVisibility(View.VISIBLE);
            goodsment.setVisibility(View.GONE);
        }


    }
}