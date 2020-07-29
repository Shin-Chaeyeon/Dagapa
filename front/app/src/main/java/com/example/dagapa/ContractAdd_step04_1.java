package com.example.dagapa;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class ContractAdd_step04_1 extends AppCompatActivity implements View.OnClickListener{

    // intent 전달 값
    String contract_info_lender = "";
    String contract_info_borrower = "";
    int contract_info_type = -1; // 초기화
    String contract_info_goods = "";

    // 카메라 입력 변수 초기화
    File file;
    final String TAG = getClass().getSimpleName();
    ImageView imageView;
    Button cameraBtn;
    final static int TAKE_PICTURE = 1;

    Bitmap bitmap;
    ByteArrayOutputStream stream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractadd_step04_1);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        contract_info_lender = bundle.getString("contract_info_lender");
        contract_info_borrower = bundle.getString("contract_info_borrower");
        contract_info_type = bundle.getInt("contract_info_type");

        final EditText goods_edit = findViewById(R.id.goods_edit);

        //레이아웃과 변수 연결
        imageView = findViewById(R.id.imageview);
        cameraBtn = findViewById(R.id.camera_button);

        // 카메라 버튼에 리스터 추가
        cameraBtn.setOnClickListener((View.OnClickListener) this);

        // 6.0 마쉬멜로우 이상일 경우에는 권한 체크 후 권한 요청
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED ) {
                Log.d(TAG, "권한 설정 완료");
            } else {
                Log.d(TAG, "권한 설정 요청");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        Button next_04_1 = findViewById(R.id.next_04_1);
        next_04_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ContractAdd_step05.class);

                // 텍스트 이미지 저장
                contract_info_goods = goods_edit.getText().toString();

                // Bimtap 이미지를 바이트 타입으로 변환 -> 이미지 크기를 줄여주자.
                if (bitmap != null){
                    float scale = (float) (1024/(float)bitmap.getWidth());
                    int image_w = (int) (bitmap.getWidth() * scale);
                    int image_h = (int) (bitmap.getHeight() * scale);
                    Bitmap resize = Bitmap.createScaledBitmap(bitmap, image_w, image_h, true);
                    resize.compress(Bitmap.CompressFormat.JPEG,100,stream);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                    byte[] byteArray = stream.toByteArray();
                    intent.putExtra("image", byteArray);
                }

                intent.putExtra("contract_info_lender", contract_info_lender);
                intent.putExtra("contract_info_borrower", contract_info_borrower);
                intent.putExtra("contract_info_type",contract_info_type);
                intent.putExtra("contract_info_goods", contract_info_goods);

                startActivity(intent);
            }
        });

    }
    // 권한 요청
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult");
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED ) {
            Log.d(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
        }
    }
    // 버튼 onClick리스터 처리부분
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.camera_button:
                // 카메라 앱을 여는 소스
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, TAKE_PICTURE);
                break;
        }
    }
    // 카메라로 촬영한 영상을 가져오는 부분
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == RESULT_OK && intent.hasExtra("data")) {
                    stream = new ByteArrayOutputStream();
                    bitmap = (Bitmap) intent.getExtras().get("data");
                    if (bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    }
                    saveTempBitmap(bitmap);
        }
        break;
        }
    }

    // Bitmap 형태로 이미지 저장 코드
    public void saveTempBitmap(Bitmap bitmap) {
        if (isExternalStorageWritable()) { // 쓸 수 있음.
            saveImage(bitmap);
        }else{
            //prompt the user or do something
        }
    }

    private void saveImage(Bitmap finalBitmap) {

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "image.jpg");

        // if (file.exists()) file.delete ();
        if (file.exists()) file.delete();
        try {
            //FileOutputStream out = new FileOutputStream(file);
            FileOutputStream out = new FileOutputStream(file);
            Log.d("test15", String.valueOf(out));
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }



}
