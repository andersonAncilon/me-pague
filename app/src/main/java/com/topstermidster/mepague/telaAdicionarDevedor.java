package com.topstermidster.mepague;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class telaAdicionarDevedor extends AppCompatActivity {

    private EditText edtName;
    private EditText edtValue;
    private EditText edtDate;
    private FirebaseAuth mAuth;
    FirebaseFirestore db;
    FirebaseUser currentUser;
    devedor dev;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_adicionar_devedor);

        edtName = (EditText) findViewById(R.id.edtName);
        edtValue = (EditText) findViewById(R.id.edtValue);
        edtDate = (EditText) findViewById(R.id.edtDate);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
    }

    public void inserir (View view) {
        dev = new devedor();
        telaPrincipal tela = new telaPrincipal();

        dev.setName(edtName.getText().toString());
        dev.setValue(edtValue.getText().toString());
        dev.setDate(edtDate.getText().toString());

        db.collection(currentUser.getEmail()).document(dev.getName())
                .set(dev)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("FIREBASE", "DocumentSnapshot successfully written!");
                        Toast.makeText(telaAdicionarDevedor.this, "Devedor : " + dev.getName() +" adicionado com sucesso", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("FIREBASE", "Error writing document", e);
                    }
                });
        Intent intent = new Intent(getApplicationContext(), telaPrincipal.class);
        startActivity(intent);
        //tela.prepareDevedorData();
    }
}
