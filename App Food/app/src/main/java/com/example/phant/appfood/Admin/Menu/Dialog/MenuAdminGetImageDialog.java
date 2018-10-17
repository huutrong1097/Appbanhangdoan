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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.DialogGetImageBinding;

import java.util.ArrayList;
import java.util.List;

public class MenuAdminGetImageDialog extends DialogFragment {
    public interface Callback{
        void result(String urlImage);
    }
    private Callback callback;
    public void onCallback(Callback callback){
        this.callback = callback;
    }
    private DialogGetImageBinding binding;
    private List<String> listImage;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_get_image,container,false);
this.readImage();

        return binding.getRoot();
    }

    void readImage(){
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ContentResolver contentResolver = getContext().getContentResolver();
        Cursor cursor = contentResolver.query(uri,null,MediaStore.Files.FileColumns.DATA + " like '%.jpg'", null, null);
        this.listImage = new ArrayList<>();
        if(cursor==null)return;
        while (cursor.moveToNext()){
            int address = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA);
            this.listImage.add(cursor.getString(address));
        }
        cursor.close();
    }

}
