package com.example.phant.appfood.Admin.Menu.Dialog;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import com.example.phant.appfood.Model.Food;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.DialogAddFoodBinding;

public class AddFoodFragmentDialog extends DialogFragment {
    public interface Callback {
        void resultFood(Food food,String typeFood);
    }

    private DialogAddFoodBinding binding;
    private Callback callback;
    private FragmentManager fragmentManager;

    public void onCallback(Callback callback) {
        this.callback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_add_food, container, false);
        this.getDialog().setTitle("Thêm món ăn");
        this.configView();
        this.listenEvent();
        return binding.getRoot();
    }

    void configView() {
        this.fragmentManager = getActivity().getSupportFragmentManager();
    }

    void listenEvent() {
        binding.buttonGetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()
                        + "/myFolder/");
                intent1.setDataAndType(uri, "image/jpeg");
                startActivityForResult(Intent.createChooser(intent1, "Open folder"), 123);*/
                MenuAdminGetImageDialog getImageDialog = new MenuAdminGetImageDialog();
                getImageDialog.onCallback(new MenuAdminGetImageDialog.Callback() {
                    @Override
                    public void result(String urlImage) {
                        binding.textUrl.setText(urlImage);
                        //  Log.e("urlImage",urlImage);
                    }
                });
                getImageDialog.show(fragmentManager, null);

            }
        });
        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback == null) return;
                callback.resultFood(new Food(null, binding.editName.getText().toString(),
                        binding.editDonGia.getText().toString(), binding.textUrl.getText().toString()),
                        binding.textNhomFood.getText().toString());
                dismiss();
            }
        });
        binding.buttonLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getActivity().getBaseContext(), v);
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.popup_group_food, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.monGoi:
                                binding.textNhomFood.setText("Gỏi");
                                break;
                            case R.id.monCuon:
                                binding.textNhomFood.setText("Cuốn");
                                break;
                            case R.id.monCom:
                                binding.textNhomFood.setText("Cơm");
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

}
