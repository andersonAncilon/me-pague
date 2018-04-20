package com.topstermidster.mepague;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class telaPrincipal extends AppCompatActivity {

    private List<devedor> devedorList = new ArrayList<>();

    private RecyclerView rcView;
    private RecyclerView.Adapter rcAdapter;
    private RecyclerView.LayoutManager rcLayoutMan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        rcView = (RecyclerView) findViewById(R.id.recycler_view);

        rcAdapter = new devedorAdapter(devedorList);
        rcLayoutMan = new LinearLayoutManager(getApplicationContext());
        rcView.setLayoutManager(rcLayoutMan);
        rcView.setItemAnimator(new DefaultItemAnimator());
        rcView.setAdapter(rcAdapter);
        rcView.addItemDecoration(new SimpleDividerItemDecoration(this));

        prepareDevedorData();
    }

    public void prepareDevedorData() {
        devedor dev = new devedor("teste", "teste");
        devedorList.add(dev);

        dev = new devedor("teste", "teste");
        devedorList.add(dev);

        dev = new devedor("teste1", "teste");
        devedorList.add(dev);

        dev = new devedor("teste2", "teste");
        devedorList.add(dev);

        dev = new devedor("teste3", "teste");
        devedorList.add(dev);

        dev = new devedor("teste3", "teste");
        devedorList.add(dev);
        dev = new devedor("teste3", "teste");
        devedorList.add(dev);
        dev = new devedor("teste3", "teste");
        devedorList.add(dev);

        rcAdapter.notifyDataSetChanged();
    }

    public void logoutUser (View view) {
        Intent intent = new Intent(getApplicationContext(), telaLogin.class);
        startActivity(intent);
    }
}