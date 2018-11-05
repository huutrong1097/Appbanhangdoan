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
        binding.textMoney.setText(String.valueOf(order.getTotalMoney()) + " VNĐ");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new OrderAdminCompleteAdapter(order.getFoodList());
        binding.recyclerView.setAdapter(adapter);

    }
}
