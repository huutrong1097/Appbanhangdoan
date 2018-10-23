package com.example.phant.appfood.Client.Menu.Fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phant.appfood.Client.Menu.Adapter.MenuClientDetailFoodAdapter;
import com.example.phant.appfood.Model.Food;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.FragmentDetailFoodAdminBinding;

import java.util.ArrayList;
import java.util.List;

public class MenuClientDetailFoodFragment extends Fragment {
    public interface CallbackClientDetailFoodFragment {
        void themFoodOrder(Food food);

        void xoaFoodOrder(Food food);
    }

    private CallbackClientDetailFoodFragment callbackClientDetailFoodFragment;

    public void onCallback(CallbackClientDetailFoodFragment callbackClientDetailFoodFragment) {
        this.callbackClientDetailFoodFragment = callbackClientDetailFoodFragment;
    }

    private FragmentDetailFoodAdminBinding binding;
    private List<Food> foodList;
    private MenuClientDetailFoodAdapter adapter;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_food_admin, container, false);
        this.configView();
        this.listenResult();
        return binding.getRoot();
    }

    void configView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerViewFragmentFood.setLayoutManager(linearLayoutManager);
        foodList = new ArrayList<>();
        adapter = new MenuClientDetailFoodAdapter(foodList);
        binding.recyclerViewFragmentFood.setAdapter(adapter);
    }

    void listenResult() {
        adapter.onCallback(new MenuClientDetailFoodAdapter.CallbackClientDetailFoodAdapter() {
            @Override
            public void removeFood(Food foods) {
                if (callbackClientDetailFoodFragment == null) return;
                callbackClientDetailFoodFragment.xoaFoodOrder(foods);
            }

            @Override
            public void themFood(Food food) {
if (callbackClientDetailFoodFragment==null)return;
callbackClientDetailFoodFragment.themFoodOrder(food);
            }
        });
    }

    public void setListFood(List<Food> listFood) {
        foodList.addAll(listFood);
        adapter.notifyDataSetChanged();
    }
}
