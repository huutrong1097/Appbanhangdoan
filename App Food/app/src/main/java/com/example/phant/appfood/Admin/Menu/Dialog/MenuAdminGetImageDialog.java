package com.example.phant.appfood.Admin.Menu.Dialog;

import android.content.ContentResolver;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phant.appfood.Admin.Menu.Adapter.MenuAdminImageDialogAdapter;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.DialogGetImageBinding;

import java.util.ArrayList;
import java.util.List;

public class MenuAdminGetImageDialog extends DialogFragment {
    public interface Callback {
        void result(String urlImage);
    }

    private Callback callback;

    public void onCallback(Callback callback) {
        this.callback = callback;
    }

    private DialogGetImageBinding binding;
    private List<String> listImage;
    private MenuAdminImageDialogAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_get_image, container, false);
        this.configView();
        this.readImage();
        this.listenResult();
        return binding.getRoot();
    }

    void configView() {
        this.getDialog().setTitle("Hình ảnh trong điện thoại");
        this.listImage = new ArrayList<>();
    }

    void readImage() {
        // Uri uri = MediaStore.Files.getContentUri("external");
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ContentResolver contentResolver = getContext().getContentResolver();
        Cursor cursor = contentResolver.query(uri, null, MediaStore.Files.FileColumns.DATA + " like '%.jpg'", null, null);
        if (cursor == null) return;
        while (cursor.moveToNext()) {
            int address = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA);
            listImage.add(cursor.getString(address));
        }
        cursor.close();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerViewDialog.setLayoutManager(linearLayoutManager);
        adapter = new MenuAdminImageDialogAdapter(listImage);
        binding.recyclerViewDialog.setAdapter(adapter);
    }

    void listenResult(){
        this.adapter.onCallbackGetImage(new MenuAdminImageDialogAdapter.CallbackAdapter() {
            @Override
            public void resultUrlImage(String url) {
                if (callback==null)return;
                callback.result(url);
                dismiss();
            }
        });
    }

}
