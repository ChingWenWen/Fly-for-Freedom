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

        TextView p_name = (TextView) findViewById(R.id.p_name);
        TextView f_distance = (TextView)findViewById(R.id.f_distance);
        TextView duration = (TextView)findViewById(R.id.duration);

        String name = getIntent().getStringExtra("name");
        p_name.setText(name);
        int speed = Integer.parseInt(getIntent().getStringExtra("speed"));
        int distance = Integer.parseInt(getIntent().getStringExtra("distanceResult"));
        f_distance.setText(String.valueOf(distance));
        int km_h = distance/speed;
        duration.setText(String.valueOf(km_h));


    }
    public void onClicktoChoose(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}