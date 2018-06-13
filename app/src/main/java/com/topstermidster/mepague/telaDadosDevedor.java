package com.topstermidster.mepague;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class telaDadosDevedor extends AppCompatActivity implements View.OnClickListener {

    private EditText nome;
    private EditText valor;
    private TextView data;
    private EditText descricao;
    private Bundle bundle;
    FirebaseFirestore db;
    FirebaseUser currentUser;
    private FirebaseAuth mAuth;

    static final int DIALOG_ID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_dados_devedor);
        setTitle(R.string.edit);
        bundle = getIntent().getExtras();

        nome = (EditText) findViewById(R.id.nameDev);
        valor = (EditText) findViewById(R.id.valueDev);
        data = (TextView) findViewById(R.id.dateDev);
        descricao = (EditText) findViewById(R.id.descDev);

        valor.addTextChangedListener(new Mask(valor));

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        nome.setText(bundle.getString("Nome"));
        valor.setText(bundle.getString("Valor"));
        data.setText(bundle.getString("Data"));
        descricao.setText(bundle.getString("Descricao"));

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        data.setOnClickListener(this);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Calendar calendario = Calendar.getInstance();

        int ano = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        switch (id) {
            case DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, ano, mes,
                        dia);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    String date = String.valueOf(dayOfMonth) + " /"
                            + String.valueOf(monthOfYear+1) + " /" + String.valueOf(year);
                    Toast.makeText(telaDadosDevedor.this,
                            "DATA = " + date, Toast.LENGTH_SHORT)
                            .show();
                    data.setText(date);
                }
            };

    public void onClick(View v) {
        if (v == data)
            showDialog(DIALOG_ID);
    }

    public void Editar (View view) {
        final Intent intent = new Intent(getApplicationContext(), telaPrincipal.class);
        db.collection(currentUser.getEmail()).document(nome.getText().toString())
                .update(
                     "date" , data.getText().toString(),
                        "desc", descricao.getText().toString(),
                        "name", nome.getText().toString(),
                        "value", valor.getText().toString()
                ).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("", "DocumentSnapshot successfully updated!");
                Toast.makeText(telaDadosDevedor.this, "Alteração realizada com sucesso!", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("", "Error updating document", e);
                        Toast.makeText(telaDadosDevedor.this, "Alteração não realizada!", Toast.LENGTH_LONG).show();
                    }
                });
    }

}
