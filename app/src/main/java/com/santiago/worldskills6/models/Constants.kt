package com.santiago.worldskills6.models

class Constants {
    companion object{

        //shared preferences
        val KEY_CORREO = "correo"
        val KEY_CONTRASENA = "contrasena"
        val KEY_RECORDAR_USER = "recordarUser"
        val KEY_USER_NAME = "nombre"
        val KEY_USER_ID = "idCliente"
        val KEY_USER_TOKEN = "token"
        val KEY_STATUS = "status"

        // id para listar
        var ID_PRODUCTO_ID = 0
        // id para listar
        var ID_CATEGORIA_ID = 0

        //DB
        val DB_NAME = "pedidos"
        val DB_VERSION = 1

        val TABLE_NAME = "producto"
        val TABLE_COLUMN_1 = "id"
        val TABLE_COLUMN_2 = "idProducto"
        val TABLE_COLUMN_3 = "nombre"
        val TABLE_COLUMN_4 = "descripcion"
        val TABLE_COLUMN_5 = "url_imagen"
        val TABLE_COLUMN_6 = "precioUnidad"
        val TABLE_COLUMN_7 = "precioTotal"
        val TABLE_COLUMN_8 = "cantidad"

        val TABLE_CREATE = " CREATE TABLE " + TABLE_NAME + " ( " +
                TABLE_COLUMN_1 + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                TABLE_COLUMN_2 + " INTEGER NOT NULL , " +
                TABLE_COLUMN_3 + " TEXT NOT NULL , " +
                TABLE_COLUMN_4 + " TEXT NOT NULL , " +
                TABLE_COLUMN_5 + " TEXT NOT NULL , " +
                TABLE_COLUMN_6 + " INTEGER NOT NULL , " +
                TABLE_COLUMN_7 + " INTEGER NOT NULL , " +
                TABLE_COLUMN_8 + " INTEGER NOT NULL ) "

        val QUERY_ALL = " SELECT * FROM " + TABLE_NAME

    }
}