package com.example.flyforfreedom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class detail extends AppCompatActivity {

    EditText sum,ticket_money;
    int p_sum = 0,money = 0,vip = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide(); //隱藏標題列
        sum = (EditText)findViewById(R.id.person);
        ticket_money = (EditText)findViewById(R.id.ticket);


    }
    public void show(View v){
        TextView txv = (TextView)findViewById(R.id.totalMoney);
        p_sum = Integer.parseInt(sum.getText().toString());
        money = Integer.parseInt(ticket_money.getText().toString());



        vip = 0;

        RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup);
        switch(rg.getCheckedRadioButtonId()){
            case R.id.yes:
                vip = 1;
                break;
        }

        money = total(p_sum,money,vip);

        txv.setText(Integer.toString(money));
    }
    public int total(int p_sum,int money,int vip){

        if ((p_sum %2)==1){
            money = (int)(money*0.9)*p_sum;
        }
        else{
            money = (int)(money*0.8)*p_sum;
        }

        if(vip==1){
            if (money>50000){
                money = money-50000;
            }
            else {
                money = 0;
            }

        }
        return money;
    }

    public void onClicktoChoose(View v){
        Intent intent = new Intent(this, ChooseAirplaneActivity.class);
        startActivity(intent);
    }

}