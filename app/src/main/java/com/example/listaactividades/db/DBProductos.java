package com.example.listaactividades.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;



public class DBProductos extends DbHelper{



    Context context;

    public DBProductos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarProductos(String nombre, int imagen, double precio, int categoria , int padre ){

        long id = 0;

        try {
            DbHelper base = new DbHelper(context);
            SQLiteDatabase db = base.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("NOMBRE",nombre);
            values.put("IMAGEN",imagen);
            values.put("PRECIO",precio);
            values.put("CATEGORIA", categoria);
            values.put("PADRE",padre);

            id = db.insert(TABLE_PRODUCTOS, null, values);

        }catch (Exception e){
            e.toString();
        }



        return id;
    }

}