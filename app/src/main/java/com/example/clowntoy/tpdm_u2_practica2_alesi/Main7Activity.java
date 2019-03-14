package com.example.clowntoy.tpdm_u2_practica2_alesi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main7Activity extends AppCompatActivity {

    Button con,sal;
    TextView ids,des,fec,tip,tel;
    EditText texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        con=findViewById(R.id.ConsBtnReg);
        sal=findViewById(R.id.consSalirReg);
        ids=findViewById(R.id.consIdReg);
        des=findViewById(R.id.consDesReg);
        fec=findViewById(R.id.consFecReg);
        tip=findViewById(R.id.consTipReg);
        tel=findViewById(R.id.consTelReg);
        texto=findViewById(R.id.idConsReg);

        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Seguro consultar=new Seguro(Main7Activity.this);
                Seguro res=consultar.select(ids.getText().toString());
                if(res!=null){
                    ids.setText(res.getIdseguro());
                    des.setText(res.getDescripcion());
                    fec.setText(res.getFecha());
                    tip.setText(res.getTipo()+"");
                    tel.setText(res.getTelefono());
                }
            }
        });

        sal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
