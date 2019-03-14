package com.example.clowntoy.tpdm_u2_practica2_alesi;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;

public class Propietario {

    private String telefono;
    private String nombre;
    private String fecha;
    private String error;
    public BaseSeguro base;

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getError(){
        return error;
    }

    public Propietario(Activity act){
        base=new BaseSeguro(act,"seguro",null,1);
    }

    public Propietario(String telefono,String nombre,String fecha){
        this.telefono=telefono;
        this.nombre=nombre;
        this.fecha=fecha;
    }

    public boolean insert(Propietario in){
        try{
            SQLiteDatabase insert=base.getWritableDatabase();
            ContentValues valores=new ContentValues();
            valores.put("TELEFONO",in.getTelefono());
            valores.put("NOMBRE",in.getNombre());
            valores.put("FECHA",in.getFecha());
            long res=insert.insert("Propietario",null,valores);
            insert.close();
            if(res==-1){
                error="No se pudo realizar la inserción.";
                return false;
            }
        }catch(SQLiteException e){
                error=e.getMessage();
                return false;
        }
        return true;
    }

    public Propietario[] select(){
        Propietario[] res=null;
        try{

            SQLiteDatabase select=base.getReadableDatabase();
            Cursor c=select.rawQuery("SELECT * FROM Propietario;",null);
            if(c.moveToFirst()){
                res=new Propietario[c.getCount()];
                int pos=0;
                do{
                    res[0]=new Propietario(c.getString(0),c.getString(1),c.getString(2));
                    pos++;
                }
                while(c.moveToNext());
            }

        }catch (SQLiteException e){

        }
        return res;
    }


    public Propietario select(String telefono){
        Propietario res=null;
        try{

            SQLiteDatabase select=base.getReadableDatabase();
            Cursor c=select.rawQuery("SELECT * FROM Propietario WHERE TELEFONO='"+telefono+"';",null);
            if(c.moveToFirst()){
                res=new Propietario(c.getString(0),c.getString(1),c.getString(2));

            }

        }catch (SQLiteException e){

        }
        return res;
    }

    public boolean delete(Propietario pro){
        try{
            SQLiteDatabase delete=base.getWritableDatabase();
            long res=delete.delete("Propietario","TELEFONO=?",new String[]{pro.getTelefono()});
            delete.close();
            if(res==-1){
                error="No se puede eliminar.";
                return false;
            }
        }catch(SQLiteException e){
            error=e.getMessage();
            return false;
        }
        return true;
    }

    public boolean update(Propietario pro){
        try{
            SQLiteDatabase update=base.getWritableDatabase();
            ContentValues val=new ContentValues();
            val.put("NOMBRE",pro.getNombre());
            val.put("FECHA",pro.getFecha());
            long res=update.update("Propietario",val,"TELEFONO=?",new String[]{pro.getTelefono()});
            if(res==-1){
                error="La actualización no pudo realizarse";
                return false;
            }
        }catch(SQLiteException e){
            error=e.getMessage();
            return false;
        }
        return true;
    }

}
