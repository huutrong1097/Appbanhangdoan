package com.example.phant.appfood.Admin.Menu.Adapter;

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
import com.example.phant.appfood.databinding.AdapterAdminDetailFoodFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class MenuAdminDetailFoodFragmentAdapter extends RecyclerView.Adapter {
    private List<Food> foodList;
    private AdapterAdminDetailFoodFragmentBinding binding;


    public MenuAdminDetailFoodFragmentAdapter(List<Food> foodList) {
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
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_admin_detail_food_fragment, parent, false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Food food = foodList.get(position);
        Glide.with(holder.itemView).load(food.getLinkImage()).apply(RequestOptions.centerCropTransform()).into(binding.imageView);
        binding.textViewName.setText("Tên: "+food.getName());
        ArrayList<String> unitPriceNew = formatMoney(food.getUnitPrice());
        for (String value: unitPriceNew ) {
            binding.textViewGia.append(value);
        }
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public ArrayList<String> formatMoney (String chuoi){
        ArrayList<String> arrayList = new ArrayList<>();
        int flat = 0;
        for (int i = chuoi.length();i>0;i--){
            if (flat==3){
                arrayList.add(0,".");
                arrayList.add(0, String.valueOf(chuoi.charAt(i-1)));
                flat=1;
            }else {
                arrayList.add(0, String.valueOf(chuoi.charAt(i-1)));
                flat++;
            }
        }
        return arrayList;
    }
}
