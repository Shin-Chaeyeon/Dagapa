package com.example.dagapa;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ContractAdd_step05 extends AppCompatActivity {

    String contract_info_lender = "";
    String contract_info_borrower = "";
    int contract_info_type = -1; // 초기화
    String contract_info_goods = "";
    String contract_info_startdate;
    String contract_info_duedate;
    byte[] byteArray;
    //Bitmap bitmap;

    // 계약 날짜 입력 초기화
    EditText date_time2_in;
    EditText date_time_in;
    EditText item_in;
    Date start_date = new Date();
    Date end_date = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractadd_step05);

        Intent intent = getIntent();

        // 텍스트 받아오기
        Bundle bundle = intent.getExtras();
        contract_info_lender = bundle.getString("contract_info_lender");
        contract_info_borrower = bundle.getString("contract_info_borrower");
        contract_info_type = bundle.getInt("contract_info_type");
        contract_info_goods = bundle.getString("contract_info_goods");

        // 이미지 받아오기
        byteArray = intent.getByteArrayExtra("image");

        // 계약 날짜 실행구간
        date_time_in = findViewById(R.id.date_time_input);
        date_time_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog(date_time_in);
            }
        });

        date_time2_in=findViewById(R.id.date_time_input2);
        date_time2_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog2(date_time2_in);
            }
        });

        Button next_05 =findViewById(R.id.next_05);
        next_05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contract_info_startdate = date_time_in.getText().toString();
                contract_info_duedate = date_time2_in.getText().toString();

                if ((contract_info_startdate == null || contract_info_startdate.length() == 0) ||
                        (contract_info_duedate == null || contract_info_duedate.length() == 0)){
                    Toast.makeText(getApplicationContext(), "날짜를 모두 기입하세요!", Toast.LENGTH_SHORT).show();
                } else{
                    Intent intent = new Intent(getApplicationContext(), ContractAdd_step06.class);
                    intent.putExtra("contract_info_lender", contract_info_lender);
                    intent.putExtra("contract_info_borrower", contract_info_borrower);
                    intent.putExtra("contract_info_type",contract_info_type);
                    intent.putExtra("contract_info_goods", contract_info_goods);
                    intent.putExtra("contract_info_startdate", contract_info_startdate);
                    intent.putExtra("contract_info_duedate", contract_info_duedate);
                    intent.putExtra("image", byteArray);

                    startActivity(intent);
                }
                }

        });

    }
    // 계약 날짜 메서드 시작
    private void showDateTimeDialog(final EditText date_time_in){
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
                start_date = calendar.getTime();
                date_time_in.setText(simpleDateFormat.format(start_date));


//                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                        calendar.set(Calendar.MINUTE, minute);
//
//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
//
//                        start_date = calendar.getTime();
//
//                        date_time_in.setText(simpleDateFormat.format(start_date));
//                    }
//                };
//                new TimePickerDialog(ContractAdd_step05.this, timeSetListener,calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
//
            }
        };

        new DatePickerDialog(ContractAdd_step05.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
    private void showDateTimeDialog2(final EditText date_time2_in){
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
                end_date = calendar.getTime();
                date_time2_in.setText(simpleDateFormat.format(end_date));


//                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                        calendar.set(Calendar.MINUTE, minute);
//
//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
//
//                        end_date = calendar.getTime();
//
//                        date_time2_in.setText(simpleDateFormat.format(end_date));
//                    }
//                };
//                new TimePickerDialog(ContractAdd_step05.this, timeSetListener,calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
//
            }
        };
        new DatePickerDialog(ContractAdd_step05.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
    // 계약 날짜 메서드 종료
}
