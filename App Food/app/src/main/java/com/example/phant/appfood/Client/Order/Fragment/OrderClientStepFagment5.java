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

import com.example.phant.appfood.Client.Order.Adapter.OrderClientStepAdapter5;
import com.example.phant.appfood.Model.Order;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.FragmentStepOrder5Binding;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class OrderClientStepFagment5 extends Fragment {
    private FragmentStepOrder5Binding binding;
    private Order order;
    private OrderClientStepAdapter5 adapter5;

    public OrderClientStepFagment5(Order order) {
        this.order = order;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_step_order5, container, false);
        this.configView();
        return binding.getRoot();
    }

    void configView() {
        ArrayList<String> textGrandTotal = formatMoney(String.valueOf(order.getTotalMoney()));
        for (String value:textGrandTotal){
            binding.textViewGrandTotal.append(value);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        adapter5 = new OrderClientStepAdapter5(order.getFoodList());
        binding.recyclerView.setAdapter(adapter5);
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
