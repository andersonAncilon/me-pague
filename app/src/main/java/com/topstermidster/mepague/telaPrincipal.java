package com.topstermidster.mepague;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class telaPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
    }


    public void logoutUser (View view) {
        Intent intent = new Intent(getApplicationContext(), telaLogin.class);
        startActivity(intent);
    }
}
