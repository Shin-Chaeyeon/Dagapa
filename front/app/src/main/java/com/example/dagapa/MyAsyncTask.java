package com.example.dagapa;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.dagapa.MainActivity.requestQueue;

public class MyAsyncTask extends AsyncTask<String, String, String> {
    List<Contract> myContracts = new ArrayList<>();

    TextView result; // REST로 받아온 JSON 데이터를 표시하기 위한 textview.
    RecyclerView recyclerView; // Json을 객체화하고 그것을 기반으로 리사이클러뷰를 표현하기 위함.

    String userID;
    Context context;
    //화면에 띄워줄 것 있으면 여기서 생성자로 받아올 것.
    public MyAsyncTask(RecyclerView recyclerView, String userID, Context context){
        this.recyclerView = recyclerView;
        this.userID = userID;
        this.context= context;
    }

    //----------------------------------------------------------------------------
    @Override
    protected String doInBackground(String... strings) {

        return null; }
    @Override
    protected void onProgressUpdate(String... values) { super.onProgressUpdate(values); }
    @Override
    protected void onCancelled() { super.onCancelled(); }
    //----------------------------------------------------------------------------

    // REST 요청하고 처리하는 과정
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("[onPreExecute]", "■■■■■■■■■■■■■■■■■■■■ onPreExecute 진입");
        Log.d("[onPreExecute]", "■■■■■■■■■■■■■■■■■■■■ userID : " + userID);
        makeRequest(userID);
        Log.d("[onPreExecute]", "■■■■■■■■■■■■■■■■■■■■ onPreExecute makeRequest함수 진행 완료");
        try {
            Thread.sleep(1500);
            Log.d("[onPreExecute]", "■■■■■■■■■■■■■■■■■■■■ onPreExecute 3초 SLEEP 진행 완료");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    // onPreExecute에서 REST 요청하고 JSON을 파싱한 후에 ,
    // adapter로 recyclerview 처리하는 곳
    @Override
    protected void onPostExecute(String s) {
        Log.d("[onPostExecute]", "■■■■■■■■■■■■■■■■■■■■ onPostExecute 단계 진입");

        LinearLayoutManager layoutManager = new LinearLayoutManager( context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        for(Contract c : myContracts) {
            Log.d("[onPostExecute]", "객체 toString: " + c.toString());
        }
        Log.d("[onPostExecute]", "■■■■■■■■■■■■■■■■■■■■ 받은 JSON 객체 크기 : "+myContracts.size());

        ContractAdapter adapter = new ContractAdapter();
        for(Contract c : myContracts){
            adapter.addItem(c, userID);
        }
        recyclerView.setAdapter(adapter);

        super.onPostExecute(s);
    }


    public void makeRequest(String userID) {
//         지환 연수원 ip
//        String url = "http://192.168.100.160:8080/my_contracts/" + userID;
//         채연 연수원 ip
//        String url = "http://192.168.100.197:8080/my_contracts/" + userID;
//         지환 집 ip
        String url = "http://192.168.123.106:8080/my_contracts/" + userID;

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        println("응답 -> " + response);
                        Log.d("GOTGOT" , response);
                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        println("에러 -> " + error.getMessage());
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
    }//end makeRequest method

    public void processResponse(String response) {
        Log.d("Response String", response);
        Gson gson = new Gson();
        Contract[] temp_array = gson.fromJson(response, Contract[].class);
        myContracts = Arrays.asList(temp_array);
        Log.d("[processResponse]", "■■■■■■■■■■■■■■■■■■■■"+myContracts.size());
    }//end processResponse method
}
