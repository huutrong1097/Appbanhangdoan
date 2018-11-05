package com.example.phant.appfood.Client.Order.Fragment;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.FragmentStepOrder4Binding;

@SuppressLint("ValidFragment")
public class OrderClientStepFagment4 extends Fragment  {
    private FragmentStepOrder4Binding binding;
    private int chapNhanOrHuy =0;

    public OrderClientStepFagment4(int chapNhanOrHuy) {
        this.chapNhanOrHuy = chapNhanOrHuy;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_step_order4,container,false);
        this.configView();
        return binding.getRoot();
    }

    private void configView() {
        if (chapNhanOrHuy==0)return;
        else binding.textView24.setText("Đơn hàng của bạn không được chấp nhận!");
    }


}
