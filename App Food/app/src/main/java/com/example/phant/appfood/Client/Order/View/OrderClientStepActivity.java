package com.example.phant.appfood.Client.Order.View;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.baoyachi.stepview.bean.StepBean;
import com.example.phant.appfood.Client.Order.Fragment.OrderClientStepFagment1;
import com.example.phant.appfood.Client.Order.Fragment.OrderClientStepFagment2;
import com.example.phant.appfood.Client.Order.Fragment.OrderClientStepFagment3;
import com.example.phant.appfood.Client.Order.Fragment.OrderClientStepFagment4;
import com.example.phant.appfood.Client.Order.Fragment.OrderClientStepFagment5;
import com.example.phant.appfood.Client.Order.Presenter.OrderClientStepPresenterImp;
import com.example.phant.appfood.Model.Food;
import com.example.phant.appfood.Model.Order;
import com.example.phant.appfood.Model.User;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.ActivityOrderClientStepBinding;

import java.util.ArrayList;
import java.util.List;

public class OrderClientStepActivity extends AppCompatActivity implements OrderClientStepView {
    private ActivityOrderClientStepBinding binding;
    private List<StepBean> beanList;
    private Intent intent;
    private List<Food> foodList;
    private User user;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private OrderClientStepFagment1 fagment1;
    private OrderClientStepFagment2 fagment2;
    private OrderClientStepFagment3 fagment3;
    private OrderClientStepFagment4 fagment4;
    private OrderClientStepFagment5 fagment5;
    private StepBean bean1;
    private StepBean bean2;
    private StepBean bean3;
    private StepBean bean4;
    private StepBean bean5;
    private OrderClientStepPresenterImp presenterIml;
    private Order order;
    private int type;
    private Order resultOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_client_step);
        this.resultData();
        this.configView();
    }

    void resultData() {
        intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        type = intent.getIntExtra("type",0);
        resultOrder = (Order) intent.getSerializableExtra("order");
        foodList = (List<Food>) intent.getSerializableExtra("listCart");

    }

    void configView() {
        presenterIml = new OrderClientStepPresenterImp(this, this);
        this.fragmentManager = getSupportFragmentManager();
        beanList = new ArrayList<>();
        bean1 = new StepBean("Preview", 0);
        bean2 = new StepBean("Shipping", -1);
        bean3 = new StepBean("Confirm", -1);
        bean4 = new StepBean("Delivering", -1);
        bean5 = new StepBean("Finish", -1);
        beanList.add(bean1);
        beanList.add(bean2);
        beanList.add(bean3);
        beanList.add(bean4);
        beanList.add(bean5);
        binding.stepView.setStepViewTexts(beanList)
                .setTextSize(12)
                .setStepsViewIndicatorCompletedLineColor(Color.parseColor("#FF868181"))
                .setStepsViewIndicatorUnCompletedLineColor(Color.parseColor("#FF868181"))
                .setStepViewComplectedTextColor(Color.parseColor("#FF868181"))
                .setStepViewUnComplectedTextColor(Color.parseColor("#FF868181"))
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(this, R.drawable.complted))
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(this, R.drawable.default_icon))
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(this, R.drawable.attention));
        if (type==0){
            fagment1 = new OrderClientStepFagment1(foodList);
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.containerStep, fagment1, "fagment1");
            transaction.commit();
            fagment1.onCallback(new OrderClientStepFagment1.CallbackStepFagment1() {
                @Override
                public void result(Order order) {
                    order.setIdCustomer(user.getIdUser());
                    beanList.get(0).setState(1);
                    beanList.get(1).setState(0);
                    binding.stepView.setStepViewTexts(beanList);
                    fagment2 = new OrderClientStepFagment2(order);
                    transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.containerStep, fagment2, "fagment2");
                    transaction.commit();
                    fagment2.onCallback(new OrderClientStepFagment2.CallbackStepFagment2() {
                        @Override
                        public void result(Order order) {
                            presenterIml.sendOrder(order);
                            beanList.get(1).setState(1);
                            beanList.get(2).setState(0);
                            binding.stepView.setStepViewTexts(beanList);
                            fagment3 = new OrderClientStepFagment3();
                            transaction = fragmentManager.beginTransaction();
                            transaction.replace(R.id.containerStep, fagment3, "fagment3");
                            transaction.commit();
                            presenterIml.getStatus(order);
                        }
                    });
                }
            });
        }else if (type==1){
            if (resultOrder.getStatus()==0){
                beanList.get(0).setState(1);
                beanList.get(1).setState(1);
                beanList.get(2).setState(0);
                binding.stepView.setStepViewTexts(beanList);
                fagment3 = new OrderClientStepFagment3();
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.containerStep, fagment3, "fagment3");
                transaction.commit();
                presenterIml.getStatus(resultOrder);
            }else if (resultOrder.getStatus()==1){
                beanList.get(0).setState(1);
                beanList.get(1).setState(1);
                beanList.get(2).setState(1);
                beanList.get(3).setState(0);
                binding.stepView.setStepViewTexts(beanList);
                fagment4 = new OrderClientStepFagment4(0);
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.containerStep, fagment4, "fagment4");
                transaction.commit();
                presenterIml.getStatus(resultOrder);
            }else if (resultOrder.getStatus()==2){
                beanList.get(0).setState(1);
                beanList.get(1).setState(1);
                beanList.get(2).setState(1);
                beanList.get(3).setState(1);
                beanList.get(4).setState(1);
                binding.stepView.setStepViewTexts(beanList);
                fagment5 = new OrderClientStepFagment5(resultOrder);
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.containerStep, fagment5, "fagment5");
                transaction.commit();
            }else if (resultOrder.getStatus()==-1){
                beanList.get(0).setState(1);
                beanList.get(1).setState(1);
                beanList.get(2).setState(1);
                beanList.get(3).setState(0);
                binding.stepView.setStepViewTexts(beanList);
                fagment4 = new OrderClientStepFagment4(1);
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.containerStep, fagment4, "fagment4");
                transaction.commit();
            }
        }
    }


    @Override
    public void resultDataDetailOrder(Order order) {
        this.order = order;
    }

    @Override
    public void resultChangeDetailOrder(Order order) {
        if (order.getKey().equals(this.order.getKey())) {
            if (order.getStatus() == 1) {
                beanList.get(2).setState(1);
                beanList.get(3).setState(0);
                binding.stepView.setStepViewTexts(beanList);
                fagment4 = new OrderClientStepFagment4(0);
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.containerStep, fagment4, "fagment4");
                transaction.commitAllowingStateLoss();
            } else if (order.getStatus() == 2) {
                beanList.get(2).setState(1);
                beanList.get(3).setState(1);
                beanList.get(4).setState(1);
                binding.stepView.setStepViewTexts(beanList);
                fagment5 = new OrderClientStepFagment5(order);
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.containerStep, fagment5, "fagment5");
                transaction.commitAllowingStateLoss();
            }else if (order.getStatus()==-1){
                beanList.get(2).setState(1);
                beanList.get(3).setState(0);
                binding.stepView.setStepViewTexts(beanList);
                fagment4 = new OrderClientStepFagment4(1);
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.containerStep, fagment4, "fagment4");
                transaction.commitAllowingStateLoss();
            }
        }
    }
}
