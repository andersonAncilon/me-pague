package com.topstermidster.mepague;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class telaLogin extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText login;
    private EditText password;
    private ProgressBar spinner;

    firebase fire;
    public SharedPreferences sharedPreferences = null;
    public SharedPreferences.Editor editor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        login = (EditText) findViewById(R.id.edtTextLogin);
        password = (EditText) findViewById(R.id.edtTextPass);
        spinner = (ProgressBar) findViewById(R.id.progresso) ;
        spinner.setVisibility(View.GONE);

        fire = new firebase();
        mAuth = FirebaseAuth.getInstance();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String login=(mSharedPreference.getString("login", "Default_Value"));
        String password=(mSharedPreference.getString("password", "Default_Value"));

        this.login.setText("");
        this.password.setText("");

        if(login != "Default_Value") {
            this.login.setText(login);
            this.password.setText(password);
        }
    }


    public void doLogin (View view) {

        spinner.setVisibility(View.VISIBLE);
        final Intent intent = new Intent(getApplicationContext(), telaPrincipal.class);
        mAuth.signInWithEmailAndPassword(login.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (!task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Login ou senha incorretos",  Toast.LENGTH_LONG).show();
                    Log.w("AUTH", "Falha ao efetuar o Login:", task.getException());
                    spinner.setVisibility(View.GONE);
                }else{
                    Log.d("AUTH", "Login Efetuado com sucesso!!!");
                    spinner.setVisibility(View.GONE);
                    startActivity(intent);
                }
            }
        });
    }

    public void saveData (View view) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("login", login.getText().toString());
        editor.putString("password", password.getText().toString());
        editor.commit();
    }


    public void createUser (View view) {
        Intent intent = new Intent(getApplicationContext(), telaCadastro.class);
        startActivity(intent);
    }


    public void authLister () {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d("AUTH", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.d("AUTH", "onAuthStateChanged:signed_out");
                }
            }
        };
    }

}
