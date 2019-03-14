package com.example.clowntoy.tpdm_u2_practica2_alesi;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Main5Activity extends AppCompatActivity {

    Seguro[] vector;
    ListView lista;
    Button salir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        lista=findViewById(R.id.listaPropietarios);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mostrarAlerta(position);
            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        Seguro get=new Seguro(this);
        vector=get.select();
        String[] descripcion=null;
        if(vector==null){
            descripcion=new String[]{"No hay seguros capturados"};
        }else{
            descripcion=new String[vector.length];
            for(int i=0;i<vector.length;i++){
                descripcion[i]=vector[i].getDescripcion();
            }
        }
        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,descripcion);
        lista.setAdapter(adaptador);
        super.onStart();
    }

    public void mostrarAlerta(int pos){
        final int pos1=pos;
        AlertDialog.Builder alerta=new AlertDialog.Builder(this);
        alerta.setTitle("Atencion")
                .setMessage("¿Deseas modificar/editar el seguro "+vector[pos].getIdseguro()+"?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        invocarEliminarActualizar(pos1);
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }


    private void invocarEliminarActualizar(int pos){
        Intent eliminaactualiza=new Intent(this,Main8Activity.class);
        eliminaactualiza.putExtra("id",vector[pos].getIdseguro());
        eliminaactualiza.putExtra("descripcion",vector[pos].getDescripcion());
        eliminaactualiza.putExtra("fecha",vector[pos].getFecha());
        eliminaactualiza.putExtra("tipo",vector[pos].getTipo());
        eliminaactualiza.putExtra("telefono",vector[pos].getTelefono());
        startActivity(eliminaactualiza);
    }


}
