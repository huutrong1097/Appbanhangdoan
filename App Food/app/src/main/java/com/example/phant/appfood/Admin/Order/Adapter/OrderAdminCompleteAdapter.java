package com.example.phant.appfood.Admin.Order.Adapter;

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
import com.example.phant.appfood.databinding.AdapterCartBinding;

import java.util.List;

public class OrderAdminCompleteAdapter extends RecyclerView.Adapter {
    private AdapterCartBinding binding;
    private List<Food> foodList;

    public OrderAdminCompleteAdapter(List<Food> foodList) {
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
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.adapter_cart,parent,false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Food food = foodList.get(position);
        Glide.with(holder.itemView).load(food.getLinkImage()).apply(RequestOptions.centerCropTransform()).into(binding.imageViewListGioHang);
        binding.textViewNameFood.setText(food.getName());
        binding.textViewUnitPrice.setText(food.getUnitPrice());

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }
}
