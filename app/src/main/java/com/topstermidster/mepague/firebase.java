package com.topstermidster.mepague;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class firebase {


    public void createUser(String login, String email, String password) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, String> user = new HashMap<>();
        user.put("Login", login);
        user.put("Password",  password);
        user.put("Email", email);
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Sucesso", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Erro", "Error adding document", e);
                    }
                });
    }

}
