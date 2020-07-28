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

    FloatingActionButton fab = null;

    static RequestQueue requestQueue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv_info);
        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);

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
        MyAsyncTask myAsyncTask = new MyAsyncTask(recyclerView, got_ID, context);
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
//            Bundle bundle = intent.getExtras();
//            Contract p = (Contract) bundle.getSerializable("personInfo");

            got_ID = intent.getExtras().getString("userID");
            got_NAME = intent.getExtras().getString("userName");
            Log.d("INTENT", "LOGIN에서 받은 DATA : " + got_ID + " / " + got_NAME);

//            if(intent != null){
//                got_name = p.goods.toString();
//                got_mobile = p.duedate.toString();
//                Log.d("transfered data : ", p.goods + " , " + p.duedate);
//            }
        }
    }//end processIntent method

    // AsyncTask 성공하면 아래 onStart와 onResume은 지워도 됨.
//    @Override
//    protected void onStart() {
//        super.onStart();
//        makeRequest(got_mobile);
//    }
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        // recycler view call
//        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(layoutManager);
//
////        for(Contract c : myContracts) Log.d("result", c.duedate+" ");
//
//        ContractAdapter adapter = new ContractAdapter();
//        adapter.addItem(new Contract("김민수", "010-1000-1000"));
//        adapter.addItem(new Contract("김하늘", "010-2000-2000"));
//        adapter.addItem(new Contract("홍길동", "010-3000-3000"));
//
//        recyclerView.setAdapter(adapter);
//        // end recycler view
//    }
//

//
//    public void makeRequest(String userno) {
////         지환 연수원 ip
////        String url = "http://192.168.100.160:8080/my_contracts/" + userno;
////         채연 연수원 ip
////        String url = "http://192.168.100.197:8080/my_contracts/" + userno;
////         지환 집 ip
//        String url = "http://192.168.123.106:8080/my_contracts/" + userno;
//
//        StringRequest request = new StringRequest(
//                Request.Method.GET,
//                url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        println("응답 -> " + response);
//                        processResponse(response);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        println("에러 -> " + error.getMessage());
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params = new HashMap<String,String>();
//                return params;
//            }
//        };
//        request.setShouldCache(false);
//        requestQueue.add(request);
//        println("요청 보냄.");
//    }//end makeRequest method
//
//    public void println(String data) {
//        result.append(data + "\n");
//    }
//
//    public void processResponse(String response) {
//        println("\n[processResponse function area] ");
//        Gson gson = new Gson();
//        Contract[] temp_array = gson.fromJson(response, Contract[].class);
////        List<Contract> myContracts = Arrays.asList(temp_array);
//        myContracts = Arrays.asList(temp_array);
//        for(Contract c : myContracts) println(c.duedate+" ");
//    }//end processResponse method

}//end MainActivity



