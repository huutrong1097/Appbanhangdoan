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
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.AdapterTypeFoodBinding;

import java.util.List;

public class MenuAdminFoodAdapter extends RecyclerView.Adapter {
    public interface Callback {
        void reuslt(String type);
    }

    private Callback callback;

    public void onCallback(Callback callback) {
        this.callback = callback;
    }


    private List<String> listTypeFood;
    private AdapterTypeFoodBinding binding;

    public MenuAdminFoodAdapter(List<String> listTypeFood) {
        this.listTypeFood = listTypeFood;
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
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_type_food, parent, false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final String type = listTypeFood.get(position);
        if (type.equals("Gỏi")) {
            Glide.with(holder.itemView).load(R.drawable.typegoi).apply(RequestOptions.centerCropTransform()).into(binding.imageTypeFood);
            binding.textNameType.setText("Gỏi");
        }
        if (type.equals("Cuốn")) {
            Glide.with(holder.itemView).load(R.drawable.typecuon).apply(RequestOptions.centerCropTransform()).into(binding.imageTypeFood);
            binding.textNameType.setText("Cuốn");
        }
        if (type.equals("Cơm")) {
            Glide.with(holder.itemView).load(R.drawable.typecom).apply(RequestOptions.centerCropTransform()).into(binding.imageTypeFood);
            binding.textNameType.setText("Cơm");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback == null) return;
                callback.reuslt(type);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTypeFood.size();
    }
}
