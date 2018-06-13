package com.topstermidster.mepague;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class telaPrincipal extends AppCompatActivity implements MyMediatorInterface {

    private List<devedor> devedorList = new ArrayList<>();
    private FirebaseAuth mAuth;
    private RecyclerView rcView;
    private RecyclerView.Adapter rcAdapter;
    private RecyclerView.LayoutManager rcLayoutMan;
    FirebaseFirestore db;
    FirebaseUser currentUser;
    private Button btnAdd;


    Bundle bundle;

    devedor deve = new devedor();

    private ProgressBar progresso;

    devedor dev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        setTitle(R.string.main);
        rcView = (RecyclerView) findViewById(R.id.recycler_view);

        rcAdapter = new devedorAdapter(devedorList, this);
        rcLayoutMan = new LinearLayoutManager(getApplicationContext());
        rcView.setLayoutManager(rcLayoutMan);
        rcView.setItemAnimator(new DefaultItemAnimator());
        rcView.setAdapter(rcAdapter);
        rcView.addItemDecoration(new SimpleDividerItemDecoration(this));
        bundle = new Bundle();

        progresso = (ProgressBar) findViewById(R.id.progressoDados);
        progresso.setVisibility(View.VISIBLE);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        prepareDevedorData();
    }

    public void insertData(View view) {
        db.collection(currentUser.getEmail()).document(dev.getName())
                .set(dev)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("FIREBASE", "DocumentSnapshot successfully written!");

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("FIREBASE", "Error writing document", e);

                    }
                });
    }


    public void prepareDevedorData() {
        db.collection(currentUser.getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                deve = document.toObject(devedor.class);
                                if(!devedorList.contains(deve.getName()))
                                    devedorList.add(deve);
                            }
                            progresso.setVisibility(View.GONE);
                            rcAdapter.notifyDataSetChanged();
                        } else {
                            Log.d("AQUI", "Error getting documents: ", task.getException());
                            progresso.setVisibility(View.GONE);
                        }
                    }
                });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        rcAdapter.notifyDataSetChanged();
    }

    public void logoutUser (View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getApplicationContext(), telaLogin.class);
        final SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor = mSharedPreference.edit();
        editor.clear();
        editor.commit();
        startActivity(intent);
    }

    public void addDevedor (View view) {
        Intent intent = new Intent(getApplicationContext(), telaAdicionarDevedor.class);
        startActivity(intent);
    }


    @Override
    public void userItemClick (int pos) {
        Toast.makeText(telaPrincipal.this, "Quem te pagou : " + devedorList.get(pos).getName(), Toast.LENGTH_SHORT).show();

        db.collection(currentUser.getEmail()).document(devedorList.get(pos).getName())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
            devedorList.remove(devedorList.get(pos));
            rcAdapter.notifyDataSetChanged();
    }

    @Override
    public void userItemClickData (int posx) {
        Intent intent = new Intent(getApplicationContext(), telaDadosDevedor.class);
        bundle.putString("Nome", devedorList.get(posx).getName());
        bundle.putString("Valor", devedorList.get(posx).getValue());
        bundle.putString("Data", devedorList.get(posx).getDate());
        bundle.putString("Descricao", devedorList.get(posx).getDesc());
        intent.putExtras(bundle);
        startActivity(intent);


    }

    public void edit(View view) {
        int pos = 0;
        userItemClickData(pos);
    }

    public void delete(View view) {
        int pos = 0;
        userItemClick(pos);
    }
}