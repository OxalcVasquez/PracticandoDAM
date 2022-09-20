package com.vasquez.fernandez.jordan.appvehiculos.logica;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vasquez.fernandez.jordan.appvehiculos.datos.Conexion;

import java.util.ArrayList;

public class Vehiculo extends Conexion {

    private String numeroChasis;
    private String numeroMotor;
    private String color;
    private Double montoDescuento;
    private Double precio;
    private String foto;
    private int marcaId;
    private int modeloId;
    private int tipoId;
    private String marca;
    private String modelo;
    private String tipo;

    public static ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();

    public String getNumeroChasis() {
        return numeroChasis;
    }

    public void setNumeroChasis(String numeroChasis) {
        this.numeroChasis = numeroChasis;
    }

    public String getNumeroMotor() {
        return numeroMotor;
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getMontoDescuento() {
        return montoDescuento;
    }

    public void setMontoDescuento(Double montoDescuento) {
        this.montoDescuento = montoDescuento;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(int marcaId) {
        this.marcaId = marcaId;
    }

    public int getModeloId() {
        return modeloId;
    }

    public void setModeloId(int modeloId) {
        this.modeloId = modeloId;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void cargarDatos(){

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT V.*,M.nombre as marca,MO.nombre as modelo,TI.nombre as tipo FROM VEHICULOS as V \n" +
                "INNER JOIN marca M ON (V.marca_id == M.id)\n" +
                "INNER JOIN modelo MO ON (V.modelo_id == MO.id)\n" +
                "INNER JOIN TIPO TI ON (V.tipo_id == TI.id)\n" +
                "ORDER BY numero_chasis;";
        Cursor cursor = db.rawQuery(sql,null);
        listaVehiculos.clear();
        while(cursor.moveToNext()){
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setNumeroChasis(cursor.getString(0));
            vehiculo.setNumeroMotor(cursor.getString(1));
            vehiculo.setColor(cursor.getString(2));
            vehiculo.setPrecio(cursor.getDouble(3));
            vehiculo.setMontoDescuento(cursor.getDouble(4));
            vehiculo.setFoto(cursor.getString(5));
            vehiculo.setMarcaId(cursor.getInt(6));
            vehiculo.setModeloId(cursor.getInt(7));
            vehiculo.setTipoId(cursor.getInt(8));
            vehiculo.setMarca(cursor.getString(9));
            vehiculo.setModelo(cursor.getString(10));
            vehiculo.setTipo(cursor.getString(11));

            listaVehiculos.add(vehiculo);

        }

    }

    public long agregar(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("numero_chasis",this.getNumeroChasis());
        registro.put("numero_montor",this.getNumeroMotor());
        registro.put("color",this.getColor());
        registro.put("precio",this.getPrecio());
        registro.put("monto_descuento",this.getMontoDescuento());
        registro.put("foto",this.getFoto());
        registro.put("marca_id",this.getMarcaId());
        registro.put("modelo_id",this.getModeloId());
        registro.put("tipo_id",this.getModeloId());
        return db.insert("vehiculos",null,registro);
    }

    public long eliminar(){
        long r = 0;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            r = db.delete("vehiculos","numero_chasis = ?",new String[]{this.getNumeroChasis()});

        } catch (Exception e){
            e.printStackTrace();
        }
        return  r;
    }

    public boolean verificarNumeroChasis(String numeroChasis){
        this.cargarDatos();
        for (int i = 0; i < listaVehiculos.size() ; i++) {
             if  (numeroChasis.equals(listaVehiculos.get(i).getNumeroChasis())) {
                 return false;
             }
        }

        return true;
    }
}
