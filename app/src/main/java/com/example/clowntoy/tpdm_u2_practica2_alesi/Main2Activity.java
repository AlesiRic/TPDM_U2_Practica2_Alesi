package com.example.clowntoy.tpdm_u2_practica2_alesi;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    EditText tel,nom,fec;
    Button ins,can;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tel=findViewById(R.id.telInsPro);
        nom=findViewById(R.id.nomInsPro);
        fec=findViewById(R.id.fecInsPro);
        ins=findViewById(R.id.inspro);
        can=findViewById(R.id.canPro);

        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Propietario insercion=new Propietario(Main2Activity.this);
                boolean cond=insercion.insert(new Propietario(tel.getText().toString(),
                        nom.getText().toString(),
                        fec.getText().toString()));
                if(cond){
                    AlertDialog.Builder alert=new AlertDialog.Builder(Main2Activity.this);
                    alert.setMessage("Inserción exitosa").setTitle("Éxito").setPositiveButton("OK",null)
                            .show();
                }else{
                    AlertDialog.Builder alert=new AlertDialog.Builder(Main2Activity.this);
                    alert.setMessage(insercion.getError()).setTitle("Error").setPositiveButton("OK",null)
                            .show();
                }

            }
        });
        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
