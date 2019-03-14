package com.example.clowntoy.tpdm_u2_practica2_alesi;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main8Activity extends AppCompatActivity {

    EditText des,fec,tip;
    TextView ide,tel;
    Button edi,del,bac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        ide=findViewById(R.id.ediDelIdSeg);
        tel=findViewById(R.id.ediDelTelSeg);
        des=findViewById(R.id.ediDelDesSeg);
        fec=findViewById(R.id.ediDelFecSeg);
        tip=findViewById(R.id.ediDelTipSeg);

        edi=findViewById(R.id.btnEditSeg);
        del=findViewById(R.id.btnDeletSeg);
        bac=findViewById(R.id.btnCancelEDSeg);
        final Bundle parametros=getIntent().getExtras();
        ide.setText(parametros.getString("id"));
        des.setText(parametros.getString("descripcion"));
        fec.setText(parametros.getString("fecha"));
        tip.setText(parametros.getString("tipo"));
        tel.setText(parametros.getString("telefono"));

        bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        edi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Seguro editar=new Seguro(Main8Activity.this);
                boolean res=editar.update(new Seguro(
                        parametros.getString("id"),
                        des.getText().toString(),
                        fec.getText().toString(),
                        Float.parseFloat(tip.getText().toString()),
                        parametros.getString("telefono")
                ));
                if(res){
                    AlertDialog.Builder alert=new AlertDialog.Builder(Main8Activity.this);
                    alert.setTitle("Éxito").setMessage("La actualización fue exitosa.")
                            .setPositiveButton("OK",null)
                            .show();
                }else{
                    AlertDialog.Builder alert=new AlertDialog.Builder(Main8Activity.this);
                    alert.setTitle("Error").setMessage(editar.getError())
                            .setPositiveButton("OK",null)
                            .show();
                }
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Seguro eliminar=new Seguro(Main8Activity.this);
                boolean res=eliminar.update(new Seguro(
                        parametros.getString("id"),
                        des.getText().toString(),
                        fec.getText().toString(),
                        Float.parseFloat(tip.getText().toString()),
                        parametros.getString("telefono")
                ));
                if(res){
                    AlertDialog.Builder alert=new AlertDialog.Builder(Main8Activity.this);
                    alert.setTitle("Éxito").setMessage("La eliminación fue exitosa.")
                            .setPositiveButton("OK",null)
                            .show();
                    finish();
                }else{
                    AlertDialog.Builder alert=new AlertDialog.Builder(Main8Activity.this);
                    alert.setTitle("Error").setMessage(eliminar.getError())
                            .setPositiveButton("OK",null)
                            .show();
                }
            }
        });

    }
}
