package com.example.phant.appfood.Client.Order.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.phant.appfood.Model.Order;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.AdapterOrderBinding;

import java.util.List;

public class OrderClientAdapter extends RecyclerView.Adapter {
    public interface CallbackOrderClientAdapter {
        void result(Order order);
    }

    private CallbackOrderClientAdapter callbackOrderClientAdapter;

    public void onCallback(CallbackOrderClientAdapter callbackOrderClientAdapter) {
        this.callbackOrderClientAdapter = callbackOrderClientAdapter;
    }

    private AdapterOrderBinding binding;
    private List<Order> list;

    public OrderClientAdapter(List<Order> list) {
        this.list = list;
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
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_order, parent, false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Order order = list.get(position);
        binding.textTitle.setText(order.getDate());
        switch (order.getStatus()){
            case 0:
                Glide.with(holder.itemView).load(R.drawable.ic_warning).into(binding.imageViewStatus);
                break;
            case -1:
                Glide.with(holder.itemView).load(R.drawable.ic_error).into(binding.imageViewStatus);
                break;
            case 1:
                Glide.with(holder.itemView).load(R.drawable.ic_ship).into(binding.imageViewStatus);
                break;
            case 2:
                Glide.with(holder.itemView).load(R.drawable.ic_finish).into(binding.imageViewStatus);
                break;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callbackOrderClientAdapter == null) return;
                callbackOrderClientAdapter.result(order);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
