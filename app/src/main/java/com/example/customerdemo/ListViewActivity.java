package com.example.customerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    String url="https://60ad9ac180a61f00173313a2.mockapi.io/customers";
    private List<Customer> customerList=new ArrayList<>();
    private AdapterCustomer adapterCustomer;
    private ListView listView;
    private Button btnThem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        btnThem=findViewById(R.id.buttonThem);

        GetApi(url);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer customer=new Customer();
                Intent intent=new Intent(ListViewActivity.this,AddCustomer.class);
                intent.putExtra("customer",customer);
                startActivity(intent);
            }
        });
    }
    private void GetApi(String url){
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    String name, email;
                    int id;
                    JSONArray jsonarray = new JSONArray(response.toString());
                    for(int i=0; i < jsonarray.length(); i++) {
                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        id   = Integer.parseInt(jsonobject.getString("id"));
                        name = jsonobject.getString("name");
                        email = jsonobject.getString("email");
                        customerList.add(new Customer(id, name, email));
                        listView=findViewById(R.id.lvCustomer);
                        adapterCustomer=new AdapterCustomer(customerList,ListViewActivity.this);
                        listView.setAdapter(adapterCustomer);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListViewActivity.this,
                        "Error make by API server!", Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}