package com.example.phant.appfood.Admin.Menu.Model;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.phant.appfood.Admin.Menu.Presenter.MenuAdminPresenter;
import com.example.phant.appfood.Model.Food;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.Calendar;

public class MenuAdminModel {
    private Context context;
    private MenuAdminPresenter presenter;
    private DatabaseReference databaseReference;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private Food food;

    public MenuAdminModel(Context context, MenuAdminPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        this.firebaseStorage = FirebaseStorage.getInstance("gs://app-food-8f3fb.appspot.com");
        this.storageReference = firebaseStorage.getReference();
    }

    public void upLoadFood(final Food food1, final String typeFood) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                final StorageReference reference = storageReference.child("images/image" + calendar.getTimeInMillis() + ".png");
                Uri uri = Uri.fromFile(new File(food1.getLinkImage()));
                UploadTask uploadTask = reference.putFile(uri);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("loi load anh",e.getMessage());
                        presenter.addFoodFailure("Lỗi không thể load ảnh: "+e.getMessage());
                    }
                });
                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()){
                            throw task.getException();
                        }
                        return reference.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()){
                            Uri downloadUri= task.getResult();
                            food1.setLinkImage(String.valueOf(downloadUri));
                        }else {
                            Log.e("error url image ", String.valueOf(task.getException()));
                        }
                        food = food1;
                        databaseReference.child("food/"+typeFood).push().setValue(food);
                        presenter.addFoodSuccess();
                    }
                });

            }
        }).start();
    }
}
