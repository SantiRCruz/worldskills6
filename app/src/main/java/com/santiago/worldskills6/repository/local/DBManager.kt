package com.santiago.worldskills6.repository.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.santiago.worldskills6.models.Constants
import com.santiago.worldskills6.models.local.DBPedido
import com.santiago.worldskills6.models.webservice.enviopedido.JsonPedido

class DBManager(context:Context) {

    val dbHelper = DBHelper(context)
    var db :SQLiteDatabase ?= null

    fun openDbWr(){
        db = dbHelper.writableDatabase
    }
    fun openDbRd(){
        db = dbHelper.readableDatabase
    }
    fun closeDb(){
        if (db!=null){
            db?.close()
        }
    }

    fun insertData(dbPedido: DBPedido):Long{
        openDbWr()
        val values = ContentValues()
        values.put(Constants.TABLE_COLUMN_2,dbPedido.idProducto)
        values.put(Constants.TABLE_COLUMN_3,dbPedido.nombre)
        values.put(Constants.TABLE_COLUMN_4,dbPedido.descripcion)
        values.put(Constants.TABLE_COLUMN_5,dbPedido.url_imagen)
        values.put(Constants.TABLE_COLUMN_6,dbPedido.precioUnidad)
        values.put(Constants.TABLE_COLUMN_7,dbPedido.precioTotal)
        values.put(Constants.TABLE_COLUMN_8,dbPedido.cantidad)
        val result = db?.insert(Constants.TABLE_NAME,null,values)
        closeDb()
        return result!!
    }
    fun updateData(id:Int,precioTotal:Int,cantidad:Int):Int{
        openDbWr()
        val values = ContentValues()
        values.put(Constants.TABLE_COLUMN_7,precioTotal)
        values.put(Constants.TABLE_COLUMN_8,cantidad)
        val result = db?.update(Constants.TABLE_NAME,values," id =? ", arrayOf(id.toString()))
        closeDb()
        return result!!
    }
    fun listData():MutableList<DBPedido>{
        val lista : MutableList<DBPedido> = arrayListOf()
        openDbRd()
        val result = db?.rawQuery(Constants.QUERY_ALL,null)
        if (result!!.moveToFirst())
            do {
                val dbPedido = DBPedido()
                dbPedido.id = result.getInt(result.getColumnIndex(Constants.TABLE_COLUMN_1))
                dbPedido.idProducto = result.getInt(result.getColumnIndex(Constants.TABLE_COLUMN_2))
                dbPedido.nombre = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_3))
                dbPedido.descripcion = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_4))
                dbPedido.url_imagen = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_5))
                dbPedido.precioUnidad = result.getInt(result.getColumnIndex(Constants.TABLE_COLUMN_6))
                dbPedido.precioTotal = result.getInt(result.getColumnIndex(Constants.TABLE_COLUMN_7))
                dbPedido.cantidad = result.getInt(result.getColumnIndex(Constants.TABLE_COLUMN_8))
                lista.add(dbPedido)
            }while (result.moveToNext())
        closeDb()
        return lista
    }
    fun listPedido():MutableList<JsonPedido>{
        val lista : MutableList<JsonPedido> = arrayListOf()
        openDbRd()
        val result = db?.rawQuery(Constants.QUERY_ALL,null)
        if (result!!.moveToFirst())
            do {
                val jsonPedido = JsonPedido()
                jsonPedido.id_producto = result.getInt(result.getColumnIndex(Constants.TABLE_COLUMN_2))
                jsonPedido.cantidad = result.getInt(result.getColumnIndex(Constants.TABLE_COLUMN_8))
                jsonPedido.precio = result.getInt(result.getColumnIndex(Constants.TABLE_COLUMN_6))
                lista.add(jsonPedido)
            }while (result.moveToNext())
        closeDb()
        return lista
    }
    fun listAcumulacion(idProducto:Int):MutableList<DBPedido>{
        val lista : MutableList<DBPedido> = arrayListOf()
        openDbRd()
        val result = db?.rawQuery(Constants.QUERY_ALL + " WHERE " + Constants.TABLE_COLUMN_2 + " =? ",
            arrayOf(idProducto.toString()))
        if (result!!.moveToFirst())
            do {
                val dbPedido = DBPedido()
                dbPedido.id = result.getInt(result.getColumnIndex(Constants.TABLE_COLUMN_1))
                dbPedido.idProducto = result.getInt(result.getColumnIndex(Constants.TABLE_COLUMN_2))
                dbPedido.nombre = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_3))
                dbPedido.descripcion = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_4))
                dbPedido.url_imagen = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_5))
                dbPedido.precioUnidad = result.getInt(result.getColumnIndex(Constants.TABLE_COLUMN_6))
                dbPedido.precioTotal = result.getInt(result.getColumnIndex(Constants.TABLE_COLUMN_7))
                dbPedido.cantidad = result.getInt(result.getColumnIndex(Constants.TABLE_COLUMN_8))
                lista.add(dbPedido)
            }while (result.moveToNext())
        closeDb()
        return lista
    }
    fun sumTotal():Int{
        var valor = 0
        openDbRd()
        val result = db?.rawQuery(" SELECT SUM ( " + Constants.TABLE_COLUMN_7 + " ) FROM " + Constants.TABLE_NAME,null)
        if (result!!.moveToFirst())
            valor = result.getInt(0)
        closeDb()
        return valor
    }
    fun deleteId(id:Int):Int{
        openDbWr()
        val result = db?.delete(Constants.TABLE_NAME, " id=? ", arrayOf(id.toString()))
        closeDb()
        return result!!
    }
    fun deleteAll():Int{
        openDbWr()
        val result = db?.delete(Constants.TABLE_NAME,null,null)
        closeDb()
        return result!!
    }
}