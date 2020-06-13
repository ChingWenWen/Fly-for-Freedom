package com.example.flyforfreedom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //隱藏標題列
        setContentView(R.layout.activity_result);

        TextView p_name = findViewById(R.id.p_name);
        TextView f_distance = findViewById(R.id.f_distance);
        TextView duration = findViewById(R.id.duration);


    }
    public void onClicktoChoose(View v){
        Intent intent = new Intent(this, ChooseAirplaneActivity.class);
        startActivity(intent);
    }
}