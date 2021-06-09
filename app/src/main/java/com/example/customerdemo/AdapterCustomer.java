package com.example.customerdemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class AdapterCustomer extends BaseAdapter {
    private List<Customer> customerList;
    private Context context;
    public AdapterCustomer(List<Customer> customerList, Context context) {
        this.customerList = customerList;
        this.context = context;
    }
    @Override
    public int getCount() {
        return customerList.size();
    }

    @Override
    public Object getItem(int position) {
        return customerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return customerList.get(position).getId();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Customer customer=customerList.get(position);
        convertView= LayoutInflater.from(context).inflate(R.layout.activity_customer,parent,false);
        TextView tvName=convertView.findViewById(R.id.tvName);
        TextView tvEmail=convertView.findViewById(R.id.tvEmail);
        TextView tvId=convertView.findViewById(R.id.tvId);
        ImageButton btnEdit=convertView.findViewById(R.id.btnEdit);
        tvId.setText(String.valueOf(customer.getId()).toString());
        tvName.setText(customer.getName());
        tvEmail.setText(customer.getEmail());
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),AddCustomer.class);
                intent.putExtra("customer",customer);
                v.getContext().startActivity(intent);
            }
        });
        return convertView;
    }
}
