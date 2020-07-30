package com.example.dagapa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Contract_info extends AppCompatActivity {

    TextView lender;
    TextView borrower;
    TextView startDate;
    TextView dueDate;
    TextView goods;
    ImageView image;
    ImageView imageMoney;
    TextView imageInfo;
    TextView description_edit;


    TextView goodsment;
    TextView moneyment;

    Button accept_btn;
    Button cancel_btn;
    Button end_btn;
    Button complete_btn;

    int type;
    String userID;

    Contract contract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_info);

        lender = findViewById(R.id.lender_edit);
        borrower = findViewById(R.id.borrower_edit);
        startDate = findViewById(R.id.startdate_edit);
        dueDate = findViewById(R.id.duedate_edit);
        goods = findViewById(R.id.goods_edit);
        description_edit = findViewById(R.id.description_edit);

        image = findViewById(R.id.imageView);
        imageInfo = findViewById(R.id.imageInfo);
        imageMoney = findViewById(R.id.money_img);

        goodsment = findViewById(R.id.goods_ment);
        moneyment = findViewById(R.id.money_ment);

        accept_btn = findViewById(R.id.accept_btn);
        cancel_btn = findViewById(R.id.cancel_btn);
        end_btn = findViewById(R.id.end_btn);
        complete_btn = findViewById(R.id.complete_button);



        Intent intent = getIntent();
        contract = (Contract) intent.getSerializableExtra("contractInfo");
        userID = (String) intent.getSerializableExtra("userID");

        type = contract.getType();


        lender.setText(contract.getLender());
        borrower.setText(contract.getBorrower());
        startDate.setText(contract.getStartdate());
        dueDate.setText(contract.getDuedate());
        goods.setText(contract.getGoods());
        description_edit.setText(contract.getDescription());

        Log.d("아이템클릭■■■■■■■■", contract.toString());

        if(type==1){
            // 물품
            String imgURL = "";
            if(contract.getImageurl() != null){
                imgURL = contract.getImageurl();
            }
            Glide.with(this).load(imgURL).into(image);
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

        int status = contract.getStatus();

        accept_btn.setVisibility(View.GONE);
        cancel_btn.setVisibility(View.GONE);
        end_btn.setVisibility(View.GONE);

        if(status==1){

            if(userID.equals(contract.creator)){
                cancel_btn.setVisibility(View.VISIBLE);
                cancel_btn.setText("취  소");
            }
            else{
                accept_btn.setVisibility(View.VISIBLE);
                accept_btn.setText("수  락");

                cancel_btn.setVisibility(View.VISIBLE);
                cancel_btn.setText("거  절");
            }
        }
        else if(status == 2){
            end_btn.setVisibility(View.VISIBLE);
        }
        else if(status == 3){
            cancel_btn.setVisibility(View.VISIBLE);
            cancel_btn.setText("삭  제");
        }

        // 수락 버튼
        accept_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "수락하였습니다.", Toast.LENGTH_LONG);
                makeGetRequest(contract.getContractno());
            }
        });

        // 취소-거절-삭제 버튼
        cancel_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });

        // 완료 버튼
        end_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });

        // 대기화면 버튼
        complete_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
            }
        });

    }//end onCreate method


    public void makeGetRequest(int contract_no){
        String contractno = Integer.toString(contract_no);
        String url = "http://192.168.100.197:8080/accept_contract/"+contractno;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("GET 수락", "성공하였따리");
//                Toast.makeText(getApplicationContext(), "수락 성공", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("userID", userID);
                Toast.makeText(getApplicationContext(), "계약을 수락하였습니다.", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("GET 수락", "실 패 ");
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);
    }// end makeGetRequest method

}