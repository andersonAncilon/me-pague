package com.topstermidster.mepague;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class firebase {
    private FirebaseAuth mAuth;
    public void createUser(String login, String password) {
        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(login, password)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Criar", "createUserWithEmail:success");

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Criar", "createUserWithEmail:failure", task.getException());
                        }

                        // ...
                    }
                });
    }

}
