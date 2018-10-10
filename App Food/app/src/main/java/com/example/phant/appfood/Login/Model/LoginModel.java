package com.example.phant.appfood.Login.Model;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.phant.appfood.Login.Presenter.LoginPresenter;
import com.example.phant.appfood.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class LoginModel {
    private Context context;
    private LoginPresenter presenter;
    private FirebaseAuth firebaseAuth;

    public LoginModel(Context context, FirebaseAuth firebaseAuth, LoginPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
        this.firebaseAuth = firebaseAuth;
    }

    public void loginFirebase(String email, String pass) {
        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    presenter.loginSuccess(new User(firebaseUser.getUid(), firebaseUser.getEmail()));
                } else {
                    presenter.loginFailure(String.valueOf(task.getException()));
                }
            }
        });
    }
}
