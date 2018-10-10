package com.example.phant.appfood.Register.Model;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterModel {
    private FirebaseAuth xacThuc;

   Context context;

    public RegisterModel(FirebaseAuth xacThuc, Context context) {
        this.xacThuc = xacThuc;
        this.context = context;
    }

    public User taoTaiKhoan(String email, String pass){
        final User user1 = new User();
        xacThuc.createUserWithEmailAndPassword(email,pass).addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = xacThuc.getCurrentUser();
                    user1.setIdUser(user.getUid());
                    user1.setEmail(user.getEmail());
                }else {
                    Log.e("loi", String.valueOf(task.getException()));
                }
            }
        });
        return user1;
    }
}
