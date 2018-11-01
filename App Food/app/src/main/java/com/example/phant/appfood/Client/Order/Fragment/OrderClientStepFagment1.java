package com.example.phant.appfood.Client.Order.Fragment;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phant.appfood.Client.Order.Adapter.OrderClientStepAdapter1;
import com.example.phant.appfood.Model.Food;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.FragmentStepOrder1Binding;

import java.util.List;

@SuppressLint("ValidFragment")
public class OrderClientStepFagment1 extends Fragment {
    private FragmentStepOrder1Binding binding;
    private OrderClientStepAdapter1 adapter1;
    private List<Food> foodList;
    private int subTotal=0;
    private int grandTotal =0;
    private int moneyShip = 3000;

    public OrderClientStepFagment1(List<Food> foodList) {
        this.foodList = foodList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_step_order1, container, false);
        this.configView();
        return binding.getRoot();
    }

    void configView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerViewStep1.setLayoutManager(linearLayoutManager);
        adapter1 = new OrderClientStepAdapter1(foodList);
        binding.recyclerViewStep1.setAdapter(adapter1);
        for (Food food : foodList){
            int i = Integer.parseInt(food.getUnitPrice().toString());
            subTotal = subTotal+i;
        }
        binding.textViewSubTotal.setText(subTotal+" VNĐ");
        binding.textViewShipping.setText(moneyShip+" VNĐ");
        grandTotal =subTotal+moneyShip;
        binding.textViewGrandTotal.setText(grandTotal+" VNĐ");
    }

}
