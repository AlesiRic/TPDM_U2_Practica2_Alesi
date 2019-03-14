package com.example.clowntoy.tpdm_u2_practica2_alesi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    EditText textConsult;
    Button bus,can;
    TextView tel,nom,fec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textConsult=findViewById(R.id.telConsPro);
        bus=findViewById(R.id.consPro);
        can=findViewById(R.id.canConsPro);
        tel=findViewById(R.id.numConsPro);
        nom=findViewById(R.id.nomConsPro);
        fec=findViewById(R.id.fecConsPro);
        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Propietario busqueda=new Propietario(Main3Activity.this);
                Propietario resultado=busqueda.select(textConsult.getText().toString());
                if(resultado!=null){
                    tel.setText(resultado.getTelefono());
                    nom.setText(resultado.getNombre());
                    fec.setText(resultado.getFecha());
                }
            }
        });
    }
}
