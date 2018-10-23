package com.example.phant.appfood.Client.Menu.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.phant.appfood.Model.Food;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.AdapterMenuBinding;

import java.util.ArrayList;
import java.util.List;

public class MenuClientDetailFoodAdapter extends RecyclerView.Adapter {
    public interface CallbackClientDetailFoodAdapter{
        void removeFood(Food foods);
        void themFood(Food food);
    }

    private CallbackClientDetailFoodAdapter callbackClientDetailFoodAdapter;
    public void onCallback(CallbackClientDetailFoodAdapter callbackClientDetailFoodAdapter){
        this.callbackClientDetailFoodAdapter = callbackClientDetailFoodAdapter;
    }
    private AdapterMenuBinding binding;
    private List<Food> listFood;
    private List<Integer> checkFood = new ArrayList<>();

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
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        final Food food = listFood.get(position);
        boolean flat = false;
        for (int i : checkFood) {
            if (i == position) {
                flat = !flat;
            }
        }
        binding.checkBoxAdd.setChecked(flat);
        binding.textTitle.setText(food.getName());
        binding.unitPrice.setText("Giá: " + food.getUnitPrice() + "VNĐ");
        Glide.with(holder.itemView).load(food.getLinkImage()).apply(RequestOptions.centerCropTransform()).into(binding.imageFood);
        binding.checkBoxAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                if (checkBox.isChecked()) {
                    checkFood.add(holder.getAdapterPosition());
                    if (callbackClientDetailFoodAdapter==null)return;
                    callbackClientDetailFoodAdapter.themFood(listFood.get(holder.getAdapterPosition()));
                } else {
                    checkFood.remove(holder.getAdapterPosition());
                    if (callbackClientDetailFoodAdapter==null)return;
                    callbackClientDetailFoodAdapter.removeFood(listFood.get(holder.getAdapterPosition()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFood.size();
    }

}
