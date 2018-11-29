package com.example.phant.appfood.Admin.Order.Fragment;

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

import com.example.phant.appfood.Admin.Order.Adapter.OrderAdminCompleteAdapter;
import com.example.phant.appfood.Model.Order;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.FragmentOrderAdminCompleteBinding;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class OrderAdminDetailCompleteFragment extends Fragment {
    private FragmentOrderAdminCompleteBinding binding;
    private Order order;
    private OrderAdminCompleteAdapter adapter;

    public OrderAdminDetailCompleteFragment(Order order) {
        this.order = order;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_admin_complete, container, false);
        this.configView();
        return binding.getRoot();
    }

    void configView() {
        binding.textName.setText(order.getName());
        binding.textPhone.setText(order.getPhone());
        binding.textAddress.setText(order.getAddress());
        binding.textNote.setText(order.getNote());
        ArrayList<String> TotalMoneyNew = formatMoney(String.valueOf(order.getTotalMoney()));
        for (String value : TotalMoneyNew){
            binding.textMoney.append(value);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new OrderAdminCompleteAdapter(order.getFoodList());
        binding.recyclerView.setAdapter(adapter);

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
