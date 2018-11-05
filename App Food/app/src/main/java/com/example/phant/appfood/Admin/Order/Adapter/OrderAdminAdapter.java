package com.example.phant.appfood.Admin.Order.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phant.appfood.Model.Order;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.AdapterOrderAdminBinding;

import java.util.List;

public class OrderAdminAdapter extends RecyclerView.Adapter {
    public interface CallbackOrderAdminAdapter {
        void result(Order order);
    }

    private CallbackOrderAdminAdapter callbackOrderAdminAdapter;

    public void onCallback(CallbackOrderAdminAdapter callbackOrderAdminAdapter) {
        this.callbackOrderAdminAdapter = callbackOrderAdminAdapter;
    }

    private List<Order> orderList;
    private AdapterOrderAdminBinding binding;

    public OrderAdminAdapter(List<Order> orderList) {
        this.orderList = orderList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_order_admin, parent, false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Order order = orderList.get(position);
        binding.textViewEmail.setText(order.getName());
        binding.textViewDate.setText(order.getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callbackOrderAdminAdapter == null) return;
                callbackOrderAdminAdapter.result(order);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
