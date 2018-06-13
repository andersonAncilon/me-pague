package com.topstermidster.mepague;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class datePicker extends Activity implements Button.OnClickListener {

    private TextView botao;
    public String data;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    static final int DATE_DIALOG_ID = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_adicionar_devedor);
        botao = (TextView) findViewById(R.id.edtDate);
        botao.setOnClickListener(this);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Calendar calendario = Calendar.getInstance();

        int ano = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, ano, mes,
                        dia);
        }
        return null;
    }


    public void abreDatePicker() {
        DatePickerDialog.OnDateSetListener mDateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        data = String.valueOf(dayOfMonth) + " /"
                                + String.valueOf(monthOfYear+1) + " /" + String.valueOf(year);
                        Toast.makeText(datePicker.this,
                                "DATA = " + data, Toast.LENGTH_SHORT)
                                .show();
                        botao.setText(data);
                    }
                };
    }

    @Override
    public void onClick(View v) {
        if (v == botao)
            showDialog(DATE_DIALOG_ID);
    }
}