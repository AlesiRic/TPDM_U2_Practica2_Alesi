package com.example.clowntoy.tpdm_u2_practica2_alesi;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {

    TextView tel;
    EditText nom,fecha;
    Button edit,del,exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        tel=findViewById(R.id.uptDelTel);
        nom=findViewById(R.id.uptDelNom);
        fecha=findViewById(R.id.updDelFec);
        edit=findViewById(R.id.actualizarProBtn);
        del=findViewById(R.id.eliminarProBtn);
        exit=findViewById(R.id.canUDbtn);
        Bundle parametros=getIntent().getExtras();
        tel.setText(parametros.getString("telefono"));
        nom.setText(parametros.getString("nombre"));
        fecha.setText(parametros.getString("fecha"));
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Propietario update=new Propietario(Main4Activity.this);
                boolean res=update.update(new Propietario(tel.getText().toString(),
                        nom.getText().toString(),
                        fecha.getText().toString()));
                if(res){
                    AlertDialog.Builder alert=new AlertDialog.Builder(Main4Activity.this);
                    alert.setTitle("Éxito").setMessage("La actualización fue exitosa")
                            .setPositiveButton("OK",null).show();
                }else{
                    AlertDialog.Builder alert=new AlertDialog.Builder(Main4Activity.this);
                    alert.setTitle("Error").setMessage(update.getError())
                            .setPositiveButton("OK",null).show();
                }
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Propietario delete=new Propietario(Main4Activity.this);
                boolean res=delete.delete(new Propietario(tel.getText().toString(),
                        nom.getText().toString(),
                        fecha.getText().toString()));
                if(res){
                    AlertDialog.Builder alert=new AlertDialog.Builder(Main4Activity.this);
                    alert.setTitle("Éxito").setMessage("La eliminación fue exitosa")
                            .setPositiveButton("OK",null).show();
                    finish();
                }else{
                    AlertDialog.Builder alert=new AlertDialog.Builder(Main4Activity.this);
                    alert.setTitle("Error").setMessage(delete.getError())
                            .setPositiveButton("OK",null).show();
                }
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
