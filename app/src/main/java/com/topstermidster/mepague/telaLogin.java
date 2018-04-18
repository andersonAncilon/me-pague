package com.topstermidster.mepague;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class telaLogin extends AppCompatActivity {


    private EditText login;
    private EditText password;
    firebase fire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        login = (EditText) findViewById(R.id.edtTextLogin);
        password = (EditText) findViewById(R.id.edtTextPass);
        fire = new firebase();
    }

    public void createUser (View view) {
        Intent intent = new Intent(getApplicationContext(), telaCadastro.class);
        startActivity(intent);
    }

}
