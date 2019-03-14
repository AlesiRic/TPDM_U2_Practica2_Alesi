package com.example.clowntoy.tpdm_u2_practica2_alesi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseSeguro extends SQLiteOpenHelper {
    public BaseSeguro(Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Propietario(TELEFONO VARCHAR(20) PRIMARY KEY," +
                "NOMBRE VARCHAR(200) NOT NULL," +
                "FECHA VARCHAR(200));");
        db.execSQL("CREATE TABLE SEGURO(" +
                "IDSEGURO VARCHAR(20) PRIMARY KEY," +
                "DESCRIPCION VARCHAR(200) NOT NULL," +
                "FECHA DATE," +
                "TIPO FLOAT," +
                "TELEFONO VARCHAR(20)," +
                "FOREIGN KEY (TELEFONO) REFERENCES Propietario(TELEFONO))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
