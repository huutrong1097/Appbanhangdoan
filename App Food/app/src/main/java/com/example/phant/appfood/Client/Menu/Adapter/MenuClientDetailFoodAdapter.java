package com.example.phant.appfood.Client.Menu.Adapter;

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
import com.example.phant.appfood.databinding.AdapterMenuBinding;

import java.util.List;

public class MenuClientDetailFoodAdapter extends RecyclerView.Adapter {
    private AdapterMenuBinding binding;
    private List<Food> listFood;

    public MenuClientDetailFoodAdapter(List<Food> listFood) {
        this.listFood = listFood;
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
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_menu, parent, false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Food food = listFood.get(position);
        binding.textTitle.setText(food.getName());
        binding.unitPrice.setText("Giá: " + food.getUnitPrice());
        Glide.with(holder.itemView).load(food.getLinkImage()).apply(RequestOptions.centerCropTransform()).into(binding.imageFood);
        binding.textSoLuong.setText("1");
    }

    @Override
    public int getItemCount() {
        return listFood.size();
    }
}
