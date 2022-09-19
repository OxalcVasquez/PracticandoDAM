package com.vasquez.fernandez.jordan.appvehiculos.logica;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vasquez.fernandez.jordan.appvehiculos.datos.Conexion;

import java.util.ArrayList;

public class Marca extends Conexion {
    private int id;
    private String nombre;

    public static ArrayList<Marca> listaMarca = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private void cargarDatosMarca(){
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM MARCA ORDER BY NOMBRE";

        Cursor cursor = db.rawQuery(sql,null);

        listaMarca.clear();

        while (cursor.moveToNext()){
            Marca marca = new Marca();
            marca.setId(cursor.getInt(0));
            marca.setNombre(cursor.getString(1));
            listaMarca.add(marca);
        }
    }

    public String[] obtenerNombresMarca(){
        this.cargarDatosMarca();
        String[] nombresMarcas = new String[listaMarca.size()];

        for (int i = 0; i < nombresMarcas.length ; i++) {
            nombresMarcas[i] = listaMarca.get(i).getNombre();
        }
        return nombresMarcas;
    }

    public int obtenerIdPorNombre(String nombre){
        this.cargarDatosMarca();
        for (int i = 0; i < listaMarca.size() ; i++) {
            if (nombre.equals(listaMarca.get(i).getNombre())){
                return listaMarca.get(i).getId();
            }
        }
        return 0;
    }
}
