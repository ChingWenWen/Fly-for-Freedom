package com.example.flyforfreedom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //隱藏標題列
        setContentView(R.layout.activity_main);

        go = findViewById(R.id.go);
    }

    public void onClicktoChoose(View v){
        Intent intent = new Intent(this, ChooseAirplaneActivity.class);
        startActivity(intent);
    }
}