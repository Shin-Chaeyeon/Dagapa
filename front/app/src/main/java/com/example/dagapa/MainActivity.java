package com.example.dagapa;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.dagapa.MainActivity.requestQueue;
import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity {


    TextView textView = null;
    String got_ID = null;
    String got_NAME = null;

    RecyclerView recyclerView = null;
    Context context = null;

    TextView lendView = null;
    TextView borrowView = null;

    FloatingActionButton fab = null;

    static RequestQueue requestQueue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv_info);
        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);

        lendView = findViewById(R.id.lend_view);
        borrowView = findViewById(R.id.borrow_view);

        context = getApplicationContext();

        //intent 데이터 수신
        /*
         * 로그인할때 넘어온 정보를 다음 화면에 띄워줌 (intent)
         * */
        Intent intent = getIntent();
        processIntent(intent);
        textView.setText("ID : " + got_ID + " / NAME : " + got_NAME);


        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        //여기서 AsyncTask 호출하기
        MyAsyncTask myAsyncTask = new MyAsyncTask(recyclerView, got_ID, context, lendView, borrowView);
        myAsyncTask.execute();

        fab.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "토스트 버튼 눌려따리", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), TempActivity.class);

                intent.putExtra("userID", got_ID);
                intent.putExtra("userName", got_NAME);
                startActivityForResult(intent, 100);
            }
        }));


    }

    private void processIntent(Intent intent) {
        if(intent !=null){
            got_ID = intent.getExtras().getString("userID");
            got_NAME = intent.getExtras().getString("userName");
            Log.d("INTENT", "LOGIN에서 받은 DATA : " + got_ID + " / " + got_NAME);
        }
    }//end processIntent method


}//end MainActivity



