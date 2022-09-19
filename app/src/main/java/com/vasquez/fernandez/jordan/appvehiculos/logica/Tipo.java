package com.vasquez.fernandez.jordan.appvehiculos.logica;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vasquez.fernandez.jordan.appvehiculos.datos.Conexion;

import java.util.ArrayList;

public class Tipo extends Conexion {
    private int id;
    private String nombre;

    public static ArrayList<Tipo> listaTipo = new ArrayList<>();

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

    private void cargarDatosTipos(){
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM TIPO ORDER BY NOMBRE";

        Cursor cursor = db.rawQuery(sql,null);

        listaTipo.clear();

        while (cursor.moveToNext()){
            Tipo tipo = new Tipo();
            tipo.setId(cursor.getInt(0));
            tipo.setNombre(cursor.getString(1));
            listaTipo.add(tipo);
        }
    }

    public String[] obtenerNombresTipos(){
        this.cargarDatosTipos();
        String[] nombresTipos = new String[listaTipo.size()];

        for (int i = 0; i < nombresTipos.length ; i++) {
            nombresTipos[i] = listaTipo.get(i).getNombre();
        }
        return nombresTipos;
    }

    public int obtenerIdPorNombre(String nombre){
        this.cargarDatosTipos();
        for (int i = 0; i < listaTipo.size() ; i++) {
            if (nombre == listaTipo.get(i).getNombre()){
                return listaTipo.get(i).getId();
            }
        }
        return 0;
    }

}
