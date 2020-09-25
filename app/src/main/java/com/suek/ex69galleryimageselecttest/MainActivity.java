package com.suek.ex69galleryimageselecttest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv= findViewById(R.id.iv);
    }

    //Intent 는 전혀 다른 앱을 실행시킬때도 사용할 수 있음
    //버튼을 누르면
    public void clickFAB(View view) {
        //Gallery app 실행하는 인텐트
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");

        //선택된 이미지 결과를 가지고 돌아오도록..
        startActivityForResult(intent, 10);
    }




    //startActivityForResult()로 실행한 Intent 가 돌아오면
    //자동으로 실행되는 메소드
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {    //Intent data-> 돌아온 Intent
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 10:
                //이미지를 선책했다면..cancel 하지 않았다면
                if(resultCode != RESULT_CANCELED){     //이미지를 선택하지않고 돌아올수도잇음
                    //돌아온 인텐트 객체에게 선택된 이미지의 Uri(경로) 를 달라고
                    Uri uri= data.getData();
                    if(uri==null) Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();  //if(uri==null)-> 혹시잘못되서 이미지를 불러오지 못할수도있으니까
                    else Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show();      //uri.toString() -> 경로 정보를 줌(uri: 진짜 주소가 아니라 물품정보임.)

                    Glide.with(this).load(uri).into(iv);     //이미지 불러오기기
                }
                break;
       }
    }
}
