package com.dehbideveloper.simplecustomercrudjavaandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_add, btn_viewAll;
    EditText et_name, et_age;
    Switch sw_active;
    ListView lv_customers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = (Button)  findViewById(R.id.btn_add);
        btn_viewAll =(Button) findViewById(R.id.btn_viewAll);
        et_name = (EditText)    findViewById(R.id.et_customerName);
        et_age = (EditText) findViewById(R.id.et_customerAge);
        sw_active = (Switch) findViewById(R.id.sw_active);
        lv_customers = (ListView)findViewById(R.id.lv_customers);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Toast.makeText(MainActivity.this, et_name.getText().toString(), Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Toast.makeText(MainActivity.this, "Error: " + ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Toast.makeText(MainActivity.this, et_name.getText().toString(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, et_age.getText().toString(), Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Toast.makeText(MainActivity.this, "Error: " + ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}