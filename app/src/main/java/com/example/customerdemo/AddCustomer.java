package com.example.customerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddCustomer extends AppCompatActivity {
    private Customer customer;
    private TextView tvName,tvEmail,tvId;
    String url="https://60ad9ac180a61f00173313a2.mockapi.io/customers";
    private Button btnSave;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        tvName=findViewById(R.id.tvName_add);
        tvEmail=findViewById(R.id.tvEmail_add);
        tvId=findViewById(R.id.tvId_add);
        btnSave=findViewById(R.id.btnSave);

        Intent intent=getIntent();
        customer= (Customer) intent.getSerializableExtra("customer");
        tvId.setText(String.valueOf(customer.getId()));
        tvName.setText(customer.getName());
        tvEmail.setText(customer.getEmail());
        id= Integer.parseInt(tvId.getText().toString());

        if(id!=0){
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PutApi(url);
                    Intent intent=new Intent(AddCustomer.this,ListViewActivity.class);
                    startActivity(intent);
                }
            });
        }
        if(id==0){
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PostApi(url);
                    Intent intent=new Intent(AddCustomer.this,ListViewActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
    private void PutApi(String url){
        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT, url + '/' + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(AddCustomer.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddCustomer.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("name", tvName.getText().toString());
                params.put("email", tvEmail.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void PostApi(String url){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(AddCustomer.this, "Successfully", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddCustomer.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String>
                        params = new HashMap<>();
                params.put("name", tvName.getText().toString());
                params.put("email", tvEmail.getText().toString());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}