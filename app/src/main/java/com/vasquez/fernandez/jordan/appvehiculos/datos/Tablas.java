package com.vasquez.fernandez.jordan.appvehiculos.datos;

public class Tablas {

    public static String tablaMarca ="CREATE TABLE MARCA (\n" +
            "id int PRIMARY KEY,\n" +
            "nombre varchar(50) not NULL\n" +
            ");";
    public static String tablaMarcaDatos[] = {
            "INSERT INTO MARCA VALUES (1,'Mercedes-Benz');",
            "INSERT INTO MARCA VALUES (2,'Ford');",
            "INSERT INTO MARCA VALUES (3,'Opel');"
    };

    public static String tablaModelo ="CREATE TABLE MODELO (\n" +
            "id int PRIMARY KEY,\n" +
            "nombre varchar(50) not NULL\n" +
            ");";
    public static String tablaModeloDatos[] = {
            "INSERT INTO MODELO VALUES (1,'Alpine');",
            "INSERT INTO MODELO VALUES (2,'Abarth');",
            "INSERT INTO MODELO VALUES (3,'Bentley');"
    };

    public static String tablaTipo ="CREATE TABLE TIPO (\n" +
            "id int PRIMARY KEY,\n" +
            "nombre varchar(50) not NULL\n" +
            ");";
    public static String tablaTipoDatos[] = {
            "INSERT INTO TIPO VALUES (1,'Sedan');",
            "INSERT INTO TIPO VALUES (2,'Suv');",
            "INSERT INTO TIPO VALUES (3,'Pickup');"
    };

    public static String tablaVehiculo="CREATE TABLE VEHICULOS (\n" +
            "numero_chasis char(17) PRIMARY KEY,\n" +
            "numero_montor varchar(10) NOT NULL,\n" +
            "color varchar(20) NOT NULL,\n" +
            "precio real NOT NULL,\n" +
            "monto_descuento real NOT NULL,\n" +
            "foto blob,\n" +
            "marca_id int NOT NULL,\n" +
            "modelo_id int NOT NULL,\n" +
            "tipo_id int NOT NULL,\n" +
            "FOREIGN KEY (marca_id) REFERENCES MARCA(id),\n" +
            "FOREIGN KEY (modelo_id) REFERENCES MODELO(id),\n" +
            "FOREIGN KEY (tipo_id) REFERENCES TIPO(id)\n" +
            ");";

    public static String tablaVehiculoDatos[] = {
            "INSERT INTO VEHICULOS VALUES('ABCD121289IOURSBS','12A','Amarillo',129000,120,NULL,1,2,3);",
            "INSERT INTO VEHICULOS VALUES('ABCDA21289IOURSBS','89B','Azul',140000,0,NULL,2,1,1);"
    };

}
