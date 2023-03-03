package com.dehbideveloper.simplecustomercrudjavaandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_add, btn_viewAll;
    EditText et_name, et_age;
    Switch sw_active;
    ListView lv_customers;
    
    DataBaseHelper dataBaseHelper;

    

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

        dataBaseHelper = new DataBaseHelper(MainActivity.this);
        showCustomerOnListView();

        btn_add.setOnClickListener(new View.OnClickListener() {
            CustomerModel customerModel;
            @Override
            public void onClick(View view) {
                try {
                    customerModel = new CustomerModel(-1, et_name.getText().toString(), Integer.parseInt(et_age.getText().toString()), sw_active.isChecked());
                }catch (Exception ex){
                    Toast.makeText(MainActivity.this, "Error: " + ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    customerModel = new CustomerModel(-1, "error", 0, false);
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

                boolean success = dataBaseHelper.addOne(customerModel);
                Toast.makeText(MainActivity.this, "success: " + success, Toast.LENGTH_SHORT).show();

                showCustomerOnListView();

            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                    List<CustomerModel> everyone = dataBaseHelper.getEveryone();

                    showCustomerOnListView();

                }catch (Exception ex){
                    Toast.makeText(MainActivity.this, "Error: " + ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        lv_customers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerModel clickedCustomer = (CustomerModel) parent.getItemAtPosition(position);
                dataBaseHelper.delteOne(clickedCustomer);
                showCustomerOnListView();
                Toast.makeText(MainActivity.this, "Deleted " + clickedCustomer.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void showCustomerOnListView() {
        ArrayAdapter<CustomerModel> customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
        lv_customers.setAdapter(customerArrayAdapter);
    }


}