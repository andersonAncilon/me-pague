package com.topstermidster.mepague;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class telaCadastro extends AppCompatActivity {

    firebase fire;
    private EditText login;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
        fire = new firebase();
        login = (EditText) findViewById(R.id.edtTextLoginCreate);
        email = (EditText) findViewById(R.id.edtTextEmailCreate);
        password = (EditText) findViewById(R.id.edtTextPasswordCreate);
        confirmPassword = (EditText) findViewById(R.id.edtTextPasswordConfirm);
    }

    public void createAcc (View view) {
        if(password.getText().equals(confirmPassword.getText()))
            fire.createUser(login.getText().toString(), email.getText().toString(), password.getText().toString() );

        else
            Toast.makeText( null, "As senhas não conferem", Toast.LENGTH_LONG).show();
    }

}
