package com.vasquez.fernandez.jordan.appvehiculos.logica;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vasquez.fernandez.jordan.appvehiculos.datos.Conexion;

import java.util.ArrayList;

public class Modelo extends Conexion {
    private int id;
    private String nombre;

    public static ArrayList<Modelo> listaModelos = new ArrayList<>();

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

    private void cargarDatosModelo(){
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM MODELO ORDER BY NOMBRE";

        Cursor cursor = db.rawQuery(sql,null);

        listaModelos.clear();

        while (cursor.moveToNext()){
            Modelo modelo = new Modelo();
            modelo.setId(cursor.getInt(0));
            modelo.setNombre(cursor.getString(1));
            listaModelos.add(modelo);
        }
    }

    public String[] obtenerNombresModelos(){
        this.cargarDatosModelo();
        String[] nombresModelos = new String[listaModelos.size()];

        for (int i = 0; i < nombresModelos.length ; i++) {
            nombresModelos[i] = listaModelos.get(i).getNombre();
        }
        return nombresModelos;
    }

    public int obtenerIdPorNombre(String nombre){
        this.cargarDatosModelo();
        for (int i = 0; i < listaModelos.size() ; i++) {
            if (nombre.equals(listaModelos.get(i).getNombre())){
                return listaModelos.get(i).getId();
            }
        }
        return 0;
    }
}
