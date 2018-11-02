package com.example.phant.appfood.Client.Order.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.phant.appfood.Model.Food;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.AdapterStepFragment1Binding;

import java.util.List;

public class OrderClientStepAdapter1 extends RecyclerView.Adapter {
    private List<Food> foodList;
    private AdapterStepFragment1Binding binding;

    public OrderClientStepAdapter1(List<Food> foodList) {
        this.foodList = foodList;
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
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.adapter_step_fragment_1,parent,false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Food food = foodList.get(position);
        Glide.with(holder.itemView).load(food.getLinkImage()).apply(RequestOptions.centerCropTransform()).into(binding.layoutStep1.imageViewListGioHang);
        binding.layoutStep1.textViewNameFood.setText(food.getName());
        binding.layoutStep1.textViewUnitPrice.setText(food.getUnitPrice()+"VNĐ");
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }
}