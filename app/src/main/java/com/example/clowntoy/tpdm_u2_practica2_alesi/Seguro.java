package com.example.clowntoy.tpdm_u2_practica2_alesi;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class Seguro {

    String idseguro,descripcion,fecha,telefono,error;
    float tipo;
    BaseSeguro base;

    public Seguro(Activity act){
        base=new BaseSeguro(act,"seguro",null,1);
    }

    public Seguro(String idseguro,String descripcion,String fecha,float tipo,String telefono){
        this.idseguro=idseguro;
        this.descripcion=descripcion;
        this.fecha=fecha;
        this.tipo=tipo;
        this.telefono=telefono;
    }

    public String getIdseguro() {
        return idseguro;
    }

    public void setIdseguro(String idseguro) {
        this.idseguro = idseguro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public float getTipo() {
        return tipo;
    }

    public void setTipo(float tipo) {
        this.tipo = tipo;
    }

    public String getError(){
        return error;
    }

    public boolean insert(Seguro in){
        try{
            SQLiteDatabase insert=base.getWritableDatabase();
            ContentValues valores=new ContentValues();
            valores.put("IDSEGURO",in.getIdseguro());
            valores.put("DESCRIPCION",in.getDescripcion());
            valores.put("FECHA",in.getFecha());
            valores.put("TIPO",in.getTipo());
            valores.put("TELEFONO",in.getTelefono());
            long res=insert.insert("SEGURO",null,valores);
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

    public Seguro[] select(){
        Seguro[] res=null;
        try{

            SQLiteDatabase select=base.getReadableDatabase();
            Cursor c=select.rawQuery("SELECT * FROM SEGURO;",null);
            if(c.moveToFirst()){
                res=new Seguro[c.getCount()];
                int pos=0;
                do{
                    res[0]=new Seguro(c.getString(0),c.getString(1),
                            c.getString(2),c.getFloat(3),c.getString(4));
                    pos++;
                }
                while(c.moveToNext());
            }

        }catch (SQLiteException e){

        }
        return res;
    }


    public Seguro select(String idseguro){
        Seguro res=null;
        try{
            SQLiteDatabase select=base.getReadableDatabase();
            Cursor c=select.rawQuery("SELECT * FROM SEGURO WHERE IDSEGURO='"+idseguro+"';",null);
            if(c.moveToFirst()){
                res=new Seguro(c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getFloat(3),
                        c.getString(4));
            }

        }catch (SQLiteException e){

        }
        return res;
    }

    public boolean delete(Seguro seg){
        try{
            SQLiteDatabase delete=base.getWritableDatabase();
            long res=delete.delete("SEGURO","IDSEGURO=?",new String[]{seg.getIdseguro()});
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

    public boolean update(Seguro seg){
        try{
            SQLiteDatabase update=base.getWritableDatabase();
            ContentValues val=new ContentValues();
            val.put("DESCRIPCION",seg.getDescripcion());
            val.put("FECHA",seg.getFecha());
            val.put("TIPO",seg.getTipo());
            val.put("TELEFONO",seg.getTelefono());
            long res=update.update("SEGURO",val,"IDSEGURO=?",new String[]{seg.getIdseguro()});
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
