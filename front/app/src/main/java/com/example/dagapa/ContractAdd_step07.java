package com.example.dagapa;

// step07은 화면에 뿌려주는 것임.

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class ContractAdd_step07 extends AppCompatActivity {


    // 데이터를 Activity 화면에 뿌려주기 위함.
    String contract_info_lender = "";
    String contract_info_borrower = "";
    int contract_info_type = -1; // 초기화
    String contract_info_goods = "";
    String contract_info_startdate = "";
    String contract_info_duedate = "";
    String contract_info_description = "";
    byte[] byteArray;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractadd_step07);

        Intent intent = getIntent();

        // 텍스트 받아오기
        Bundle bundle = intent.getExtras();
        contract_info_lender = bundle.getString("contract_info_lender");
        contract_info_borrower = bundle.getString("contract_info_borrower");
        contract_info_type = bundle.getInt("contract_info_type");
        contract_info_goods = bundle.getString("contract_info_goods");
        contract_info_startdate = bundle.getString("contract_info_startdate");
        contract_info_duedate = bundle.getString("contract_info_duedate");
        contract_info_description = bundle.getString("contract_info_description");

        // 이미지 받아오기
        //-> 마지막은 이미지를 출력해야 하기 때문에 byteArray를 다시 비트맵 형태로 변환하여 출력하자

        /// 이미지 널이면 출력하면 안됨
        byteArray = intent.getByteArrayExtra("image");
        if (byteArray != null){
            bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageBitmap(bitmap);
        }

        TextView lender_text = findViewById(R.id.lender_edit);
        TextView borrower_text = findViewById(R.id.borrower_edit);
        TextView goods_text = findViewById(R.id.goods_edit);
        TextView startdate_text = findViewById(R.id.startdate_edit);
        TextView duedate_text = findViewById(R.id.duedate_edit);
        TextView description_text = findViewById(R.id.description_edit);

        lender_text.setText(contract_info_lender);
        borrower_text.setText(contract_info_borrower);
        goods_text.setText(contract_info_goods);
        // startdate_text.setText(contract_info_startdate);
        startdate_text.setText("20" + contract_info_startdate.substring(0,8));
        duedate_text.setText("20" + contract_info_duedate.substring(0,8));

        // duedate_text.setText(contract_info_duedate);
        description_text.setText(contract_info_description);

        // button 누를 때, 밑에 try catch 값이 실행되도록 설정할 것.

        Button quit_button = findViewById(R.id.quit_button);
        quit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String start2 = contract_info_startdate;
                Log.d("test", start2);
                Log.d("test2", "20" + start2.substring(0,8));

            }
        });

        createNotificationChannel();
        Button complete_button = findViewById(R.id.complete_button);
        complete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 정보 전달 코드
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // Set header
                            Map<String, String> headers = new HashMap<>();
                            headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
                            HttpPostMultipart multipart = new HttpPostMultipart("http://192.168.100.197:8080/add_contract", "utf-8", headers);

                            // Add form field string data
                            multipart.addFormField("lender", contract_info_lender);
                            multipart.addFormField("borrower", contract_info_borrower);
                            multipart.addFormField("type", String.valueOf(contract_info_type)); // -> int 형이라 안됨.
                            multipart.addFormField("goods", contract_info_goods);
                            multipart.addFormField("startdate", "20" + contract_info_startdate.substring(0,8));
                            multipart.addFormField("duedate", "20" + contract_info_duedate.substring(0,8));
                            multipart.addFormField("description", contract_info_description);
                            multipart.addFormField("status", String.valueOf(1)); // -> int 형이라 안됨.

                            // 파일 보내기
                            if (contract_info_type == 1){ // 물건일 때만 이미지를 보내도록 하자.
                                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "image.jpg");
                                multipart.addFilePart("image", file);
                            }


                            String response = multipart.finish();
                            System.out.println(response);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();


                // 알람 울리는 코드
                Intent intent = new Intent(ContractAdd_step07.this, ReminderBroadcast.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(ContractAdd_step07.this, 0, intent, 0);

                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

                //값을 계산하기 위함.
                long timeAtButtonClick = System.currentTimeMillis(); // 현재 시간
                String start = contract_info_startdate;
                String end = contract_info_duedate;
                SimpleDateFormat fm = new SimpleDateFormat("yy-MM-dd HH:mm");
                try {
                    Date cal_startdate = fm.parse(start); // 계산 하기 위함.
                    Date cal_enddate = fm.parse(end); // 계산 하기 위함.
                    long cal_result = cal_enddate.getTime() - cal_startdate.getTime();

                    alarmManager.set(AlarmManager.RTC_WAKEUP,
                            timeAtButtonClick + cal_result,
                            pendingIntent); // 시현할 때, 코드 구현 -> 마감일 - 시작일을 1분으로 맞춰서 시현

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Intent intent2 = new Intent(getApplicationContext(), ContractAdd_step08.class);
                startActivity(intent2);
            }
        });

        // 취소 버튼은 메인 화면으로 돌아가도록.
        // 완료 버튼 시 데이터 전송 + '상대방 응답을 기다릴게요' 화면 전환
    }

    public class HttpPostMultipart {
        private final String boundary;
        private static final String LINE = "\r\n";
        private HttpURLConnection httpConn;
        private String charset;
        private OutputStream outputStream;
        private PrintWriter writer;

        /**
         * This constructor initializes a new HTTP POST request with content type
         * is set to multipart/form-data
         *
         * @param requestURL
         * @param charset
         * @param headers
         * @throws IOException
         */
        public HttpPostMultipart(String requestURL, String charset, Map<String, String> headers) throws IOException {
            this.charset = charset;
            boundary = UUID.randomUUID().toString();
            URL url = new URL(requestURL);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setUseCaches(false);
            httpConn.setDoOutput(true);    // indicates POST method
            httpConn.setDoInput(true);
            httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            if (headers != null && headers.size() > 0) {
                Iterator<String> it = headers.keySet().iterator();
                while (it.hasNext()) {
                    String key = it.next();
                    String value = headers.get(key);
                    httpConn.setRequestProperty(key, value);
                }
            }
            outputStream = httpConn.getOutputStream();
            writer = new PrintWriter(new OutputStreamWriter(outputStream, charset), true);
        }

        /**
         * Adds a form field to the request
         *
         * @param name  field name
         * @param value field value
         */
        public void addFormField(String name, String value) {
            writer.append("--" + boundary).append(LINE);
            writer.append("Content-Disposition: form-data; name=\"" + name + "\"").append(LINE);
            writer.append("Content-Type: text/plain; charset=" + charset).append(LINE);
            writer.append(LINE);
            writer.append(value).append(LINE);
            writer.flush();
        }

        /**
         * Adds a upload file section to the request
         *
         * @param fieldName
         * @param uploadFile
         * @throws IOException
         */
        public void addFilePart(String fieldName, File uploadFile)
                throws IOException {
            String fileName = uploadFile.getName();
            Log.d("test1", fileName);
            writer.append("--" + boundary).append(LINE);
            writer.append("Content-Disposition: form-data; name=\"" + fieldName + "\"; filename=\"" + fileName + "\"").append(LINE);
            writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName)).append(LINE);
            writer.append("Content-Transfer-Encoding: binary").append(LINE);
            writer.append(LINE);
            Log.d("test2", String.valueOf(writer));
            writer.flush();



            Log.d("test3", String.valueOf(uploadFile));
            FileInputStream inputStream = new FileInputStream(uploadFile);
            Log.d("test4", String.valueOf(inputStream));
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();
            writer.append(LINE);
            writer.flush();
        }
        /**
         * Completes the request and receives response from the server.
         *
         * @return String as response in case the server returned
         * status OK, otherwise an exception is thrown.
         * @throws IOException
         */
        public String finish() throws IOException {
            String response = "";
            writer.flush();
            writer.append("--" + boundary + "--").append(LINE);
            writer.close();

            // checks server's status code first
            int status = httpConn.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                ByteArrayOutputStream result = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length;
                while ((length = httpConn.getInputStream().read(buffer)) != -1) {
                    result.write(buffer, 0, length);
                }
                response = result.toString(this.charset);
                httpConn.disconnect();
            } else {
                throw new IOException("Server returned non-OK status: " + status);
            }
            return response;
        }
    }
    // 알람 Notification
    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "LamubiReminderChannel";
            String description = "Channel for Lumbit Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyLemubit", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}