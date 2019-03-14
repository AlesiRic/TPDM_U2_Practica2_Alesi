package com.example.clowntoy.tpdm_u2_practica2_alesi;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    Propietario[] vector;
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista=findViewById(R.id.listaPropietarios);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mostrarAlerta(position);
            }
        });

    }

    @Override
    protected void onStart() {
        Propietario get=new Propietario(this);
        vector=get.select();
        String[] nombretelefono=null;
        if(vector==null){
            nombretelefono=new String[]{"No hay propietarios capturados"};
        }else{
            nombretelefono=new String[vector.length];
            for(int i=0;i<vector.length;i++){
                nombretelefono[i]=vector[i].getNombre()+"\n"+vector[i].getTelefono();
            }
        }
        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombretelefono);
        lista.setAdapter(adaptador);
        super.onStart();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuprincipal,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.ActInsertPro:Intent intentA=new Intent(this,Main2Activity.class);
                startActivity(intentA);
                break;
            case R.id.ActConsPro:Intent intentB=new Intent(this,Main3Activity.class);
                startActivity(intentB);
                break;
            case R.id.listaSeguros:Intent intentC=new Intent(this,Main5Activity.class);
                startActivity(intentC);
                break;
            case R.id.ActInsertSeg:Intent intentD=new Intent(this,Main6Activity.class);
                startActivity(intentD);
                break;
            case R.id.ActConsSeg:Intent intentE=new Intent(this,Main7Activity.class);
                startActivity(intentE);
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void mostrarAlerta(int pos){
        final int pos1=pos;
        AlertDialog.Builder alerta=new AlertDialog.Builder(this);
        alerta.setTitle("Atencion")
                .setMessage("¿Deseas modificar/editar el propietario "+vector[pos].getNombre()+"?")
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
        Intent eliminaactualiza=new Intent(this,Main4Activity.class);
        eliminaactualiza.putExtra("telefono",vector[pos].getTelefono());
        eliminaactualiza.putExtra("nombre",vector[pos].getNombre());
        eliminaactualiza.putExtra("fecha",vector[pos].getFecha());
        startActivity(eliminaactualiza);
    }

}
