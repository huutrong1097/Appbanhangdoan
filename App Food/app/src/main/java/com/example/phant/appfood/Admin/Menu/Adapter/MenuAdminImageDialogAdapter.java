package com.example.phant.appfood.Admin.Menu.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.AdapterDialogGetImageBinding;

import java.util.List;

public class MenuAdminImageDialogAdapter extends RecyclerView.Adapter {
    public interface CallbackAdapter{
        void resultUrlImage(String url);
    }
    private CallbackAdapter callbackAdapter;
    public void onCallbackGetImage (CallbackAdapter callbackAdapter){
        this.callbackAdapter=callbackAdapter;
    }
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
        final String image = listImage.get(position);
        Glide.with(holder.itemView).load(image).into(binding.imageDialogGetImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (callbackAdapter==null)return;
              callbackAdapter.resultUrlImage(image);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listImage.size();
    }
}
