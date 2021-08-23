package com.santiago.worldskills6.repository.local

import com.santiago.worldskills6.models.local.DBPedido

class RepositoryDB(val dbManager: DBManager) {

     fun insertData(id:Int,idProducto:Int,nombre:String,descripcion:String,url_imagen:String,precioUnidad:Int,precioTotal:Int,cantidad:Int):Long = dbManager.insertData(DBPedido(id,idProducto,nombre,descripcion,url_imagen,precioUnidad,precioTotal,cantidad))

    fun listData():MutableList<DBPedido> = dbManager.listData()

    fun sumTotal():Int = dbManager.sumTotal()
}