package com.example.dagapa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    EditText id = null;
    EditText password = null;
    Button btn_login = null;

    String userID = null;
    String userName = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id = findViewById(R.id.id);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.login);

        btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String get_id = id.getText().toString();
                String get_pw = password.getText().toString();

                 Log.d("id info : " , get_id);
                 Log.d("pw info : ", get_pw);


                 //request 보내기
                makeRequest(get_id, get_pw);
            }
        });
    }

    public void makeRequest(final String typed_id, final String typed_pw) {
//         지환 연수원 ip
//        String url = "http://192.168.100.160:8080/sign_in";
//         채연 연수원 ip
//        String url = "http://192.168.100.197:8080/sign_in";
//         지환 집 ip
        String url = "http://192.168.123.106:8080/sign_in";



        JSONObject js = new JSONObject();
        try {
            js.put("id", typed_id);
            js.put("pw", typed_pw);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Make request for JSONObject
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, url, js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("POST", response.toString() + " i am queen");
                        processResponse(response.toString());

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

//                        Contract p = new Contract(userID, userName);
                        intent.putExtra("userID", userID);
                        intent.putExtra("userName", userName);
                        startActivityForResult(intent, 100);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("POST", "POST REST ERROR!");
            }
        }) {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        // Adding request to request queue
        Volley.newRequestQueue(this).add(jsonObjReq);


    }//end makeRequest method


    public void processResponse(String response) {
        Gson gson = new Gson();
//        User[] temp_array = gson.fromJson(response, User[].class);
        User temp_obj = gson.fromJson(response, User.class);
        userID = temp_obj.getId();
        userName = temp_obj.getName();
        Log.d("LOGIN", "@@@@@@@@@@@@@@@@@@@@ : "+ userID + " / " + userName);
    }//end processResponse method

}