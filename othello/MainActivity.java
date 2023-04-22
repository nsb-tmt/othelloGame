package com.example.othellover2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //オセロコントローラーをインスタンス化して、ゲームを開始
        OthelloController oc = new OthelloController(this);
    }
}