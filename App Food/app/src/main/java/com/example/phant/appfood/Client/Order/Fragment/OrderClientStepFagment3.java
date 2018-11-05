package com.example.phant.appfood.Client.Order.Fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.FragmentStepOrder3Binding;

public class OrderClientStepFagment3 extends Fragment  {
    private FragmentStepOrder3Binding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_step_order3,container,false);
        return binding.getRoot();
    }
}
