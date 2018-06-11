package com.topstermidster.mepague;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.DatePickerDialog;
import android.app.Dialog;
import java.util.Calendar;
import android.widget.DatePicker;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class telaAdicionarDevedor extends AppCompatActivity implements View.OnClickListener {

    private EditText edtName;
    private EditText edtValue;
    private TextView edtDate;
    private FirebaseAuth mAuth;
    FirebaseFirestore db;
    FirebaseUser currentUser;
    devedor dev;
    int yearV, month, day;
    static final int DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_adicionar_devedor);

        edtName = (EditText) findViewById(R.id.edtName);
        edtValue = (EditText) findViewById(R.id.edtValue);
        edtDate = (TextView) findViewById(R.id.edtDate);


        edtValue.addTextChangedListener(new Mask(edtValue));

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        edtDate.setOnClickListener(this);
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
                    String data = String.valueOf(dayOfMonth) + " /"
                            + String.valueOf(monthOfYear+1) + " /" + String.valueOf(year);
                    Toast.makeText(telaAdicionarDevedor.this,
                            "DATA = " + data, Toast.LENGTH_SHORT)
                            .show();
                    edtDate.setText(data);
                }
            };

    public void onClick(View v) {
        if (v == edtDate)
            showDialog(DIALOG_ID);
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
