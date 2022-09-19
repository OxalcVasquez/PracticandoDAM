package com.vasquez.fernandez.jordan.appvehiculos.datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexion extends SQLiteOpenHelper {

    public static Context contextApp;
    public static String dbName = "VehiculosBD";
    public static int version = 1;

    public Conexion() {
        super(contextApp, dbName, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Ejecutar el script DDL
        sqLiteDatabase.execSQL(Tablas.tablaMarca);
        sqLiteDatabase.execSQL(Tablas.tablaTipo);
        sqLiteDatabase.execSQL(Tablas.tablaModelo);
        sqLiteDatabase.execSQL(Tablas.tablaVehiculo);

        //Ejecutar el Script DML
        for (int i = 0; i < Tablas.tablaMarcaDatos.length; i++) {
            sqLiteDatabase.execSQL(Tablas.tablaMarcaDatos[i]);
        }
        for (int i = 0; i < Tablas.tablaTipoDatos.length; i++) {
            sqLiteDatabase.execSQL(Tablas.tablaTipoDatos[i]);
        }
        for (int i = 0; i < Tablas.tablaModeloDatos.length; i++) {
            sqLiteDatabase.execSQL(Tablas.tablaModeloDatos[i]);
        }
        for (int i = 0; i < Tablas.tablaVehiculoDatos.length; i++) {
            sqLiteDatabase.execSQL(Tablas.tablaVehiculoDatos[i]);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
