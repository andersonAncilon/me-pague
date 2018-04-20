package com.topstermidster.mepague;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class telaCadastro extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText login;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
        login = (EditText) findViewById(R.id.edtTextLoginCreate);
        password = (EditText) findViewById(R.id.edtTextPasswordCreate);
        confirmPassword = (EditText) findViewById(R.id.edtTextPasswordConfirm);
        mAuth = FirebaseAuth.getInstance();
    }

    public void createAcc (View view) {

        if(password.getText().toString().equals(confirmPassword.getText().toString()))
            mAuth.createUserWithEmailAndPassword(login.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("Usuario criado", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(telaCadastro.this, "Cadastro realizado com sucesso.",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("Nao foi criado", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(telaCadastro.this, "Cadastro não realizado, tente novamente.",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });

        else
            Toast.makeText( getApplicationContext(), "As senhas não conferem", Toast.LENGTH_LONG).show();
    }




}
