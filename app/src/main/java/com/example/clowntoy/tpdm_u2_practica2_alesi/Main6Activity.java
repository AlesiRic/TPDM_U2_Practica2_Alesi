package com.example.clowntoy.tpdm_u2_practica2_alesi;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Main6Activity extends AppCompatActivity {

    EditText ids,des,fec,tipo;
    Spinner tel;
    Button insert,exit;
    Propietario[] vector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        insert=findViewById(R.id.insseg);
        exit=findViewById(R.id.canSeg);
        ids=findViewById(R.id.idInsSeg);
        des=findViewById(R.id.desInsSeg);
        fec=findViewById(R.id.fecInsSeg);
        tipo=findViewById(R.id.tipInsSeg);
        tel=findViewById(R.id.telInsSeg);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Seguro insertar=new Seguro(Main6Activity.this);
                boolean res=insertar.insert(new Seguro(ids.getText().toString(),
                        des.getText().toString(),
                        fec.getText().toString(),
                        Float.parseFloat(tipo.getText().toString()),
                        tel.getSelectedItem().toString()));
                if(res){
                    AlertDialog.Builder alert=new AlertDialog.Builder(Main6Activity.this);
                    alert.setTitle("Éxito").setMessage("La inserción fue exitosa")
                            .setPositiveButton("OK",null).show();
                }else{
                    AlertDialog.Builder alert=new AlertDialog.Builder(Main6Activity.this);
                    alert.setTitle("Error").setMessage(insertar.getError())
                            .setPositiveButton("OK",null).show();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        Propietario get=new Propietario(this);
        vector=get.select();
        String[] telefono=null;
        if(vector==null){
            telefono=new String[]{"No hay telefonos a elegir"};
        }else{
            telefono=new String[vector.length];
            for(int i=0;i<vector.length;i++){
                telefono[i]=vector[i].getTelefono();
            }
        }
        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,telefono);
        tel.setAdapter(adaptador);
        super.onStart();
    }
}
