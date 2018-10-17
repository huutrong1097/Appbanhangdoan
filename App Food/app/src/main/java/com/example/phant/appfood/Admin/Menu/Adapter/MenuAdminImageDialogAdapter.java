package com.example.phant.appfood.Admin.Menu.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.AdapterDialogGetImageBinding;

import java.util.List;

public class MenuAdminImageDialogAdapter extends RecyclerView.Adapter {
    private List<String> listImage;
    private AdapterDialogGetImageBinding binding;

    public MenuAdminImageDialogAdapter(List<String> listImage) {
        this.listImage = listImage;
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
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_dialog_get_image,parent,false);

        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listImage.size();
    }
}
