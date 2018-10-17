package com.example.phant.appfood.Admin.Menu.Dialog;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phant.appfood.Model.Food;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.DialogAddFoodBinding;

public class AddFoodFragmentDialog extends DialogFragment {
    public interface Callback {
        void resultFood(Food food);
    }

    private DialogAddFoodBinding binding;
    private Callback callback;

    public void onCallback(Callback callback) {
        this.callback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_add_food, container, false);
        this.getDialog().setTitle("Thêm món ăn");
        this.listenEvent();
        return binding.getRoot();
    }

    void listenEvent() {
        binding.buttonGetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()
                        + "/myFolder/");
                intent1.setDataAndType(uri, "image/jpeg");
                startActivityForResult(Intent.createChooser(intent1, "Open folder"), 123);
            }
        });
        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback == null) return;
                callback.resultFood(new Food(null, binding.editName.getText().toString(),
                        binding.editDonGia.getText().toString(), binding.textUrl.getText().toString()));
                dismiss();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {
            String image = data.getDataString();
            binding.textUrl.setText(image);
        }
    }
}
