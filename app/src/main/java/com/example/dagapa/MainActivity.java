package com.example.dagapa;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.dagapa.MainActivity.requestQueue;
import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity {

    EditText editText = null;
    TextView textView = null;
    TextView result = null;
    String got_name = null;
    String got_mobile = null;
    Button button = null;

    static RequestQueue requestQueue = null;

    // 아래 지울것.
    List<Contract> myContracts = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv_info);
        result = findViewById(R.id.result);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);


        //intent 데이터 수신
        /*
         * 로그인할때 넘어온 정보를 다음 화면에 띄워줌 (intent)
         * */
        Intent intent = getIntent();
        processIntent(intent);
        textView.setText(got_name + " / 유저번호 :  " + got_mobile);





//        // 버튼 이벤트
//        button.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                makeRequest(got_mobile);
//            }
//        });

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        //여기서 AsyncTask 호출하기

    }

    // AsyncTask 성공하면 아래 onStart와 onResume은 지워도 됨.
    @Override
    protected void onStart() {
        super.onStart();
        makeRequest(got_mobile);
    }


    @Override
    protected void onResume() {
        super.onResume();

        // recycler view call
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

//        for(Contract c : myContracts) Log.d("result", c.duedate+" ");

        ContractAdapter adapter = new ContractAdapter();
        adapter.addItem(new Contract("김민수", "010-1000-1000"));
        adapter.addItem(new Contract("김하늘", "010-2000-2000"));
        adapter.addItem(new Contract("홍길동", "010-3000-3000"));

        recyclerView.setAdapter(adapter);
        // end recycler view
    }

    private void processIntent(Intent intent) {
        if(intent !=null){
            Bundle bundle = intent.getExtras();
            Contract p = (Contract) bundle.getSerializable("personInfo");
            if(intent != null){
                got_name = p.goods.toString();
                got_mobile = p.duedate.toString();
                Log.d("transfered data : ", p.goods + " , " + p.duedate);
            }
        }
    }//end processIntent method

    public void makeRequest(String userno) {
//        String url = "http://192.168.100.197:8080/my_contracts/" + editText.getText().toString();
//        String url = "http://192.168.100.197:8080/my_contracts/1";
//        String url = "http://192.168.100.160:8080/my_contracts/" + userno;


        // 채연 ip
        String url = "http://192.168.100.197:8080/my_contracts/" + userno;

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        println("응답 -> " + response);
                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 -> " + error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                return params;
            }
        };
        request.setShouldCache(false);
        requestQueue.add(request);
        println("요청 보냄.");
    }//end makeRequest method

    public void println(String data) {
        result.append(data + "\n");
    }

    public void processResponse(String response) {
        println("\n[processResponse function area] ");
        Gson gson = new Gson();
        Contract[] temp_array = gson.fromJson(response, Contract[].class);
//        List<Contract> myContracts = Arrays.asList(temp_array);
        myContracts = Arrays.asList(temp_array);
        for(Contract c : myContracts) println(c.duedate+" ");
    }//end processResponse method

}//end MainActivity



class MyAsyncTask extends AsyncTask<String, String, String>{
    List<Contract> myContracts = null;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    // doInBackground에서 REST 요청하고 JSON을 파싱한 후에 ,
    // adapter로 recyclerview 처리하는 곳
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }


    // REST 요청하고 처리하는 과정
    @Override
    protected String doInBackground(String... strings) {
        Log.d("doIn", strings[0]);
//        makeRequest(strings[0]);
        return null;
    }



    public void makeRequest(String userno) {
//        String url = "http://192.168.100.197:8080/my_contracts/" + editText.getText().toString();
//        String url = "http://192.168.100.197:8080/my_contracts/1";

//        지환 연수원 ip
//        String url = "http://192.168.100.160:8080/my_contracts/" + userno;

        //채연 ip
        String url = "http://192.168.100.197:8080/my_contracts/" + userno;

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        println("응답 -> " + response);
                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 -> " + error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                return params;
            }
        };
        request.setShouldCache(false);
        requestQueue.add(request);
        println("요청 보냄.");
    }//end makeRequest method

    public void processResponse(String response) {
        println("\n[processResponse function area] ");
        Gson gson = new Gson();
        Contract[] temp_array = gson.fromJson(response, Contract[].class);
//        List<Contract> myContracts = Arrays.asList(temp_array);
        myContracts = Arrays.asList(temp_array);
        for(Contract c : myContracts) println(c.duedate+" ");
    }//end processResponse method

}