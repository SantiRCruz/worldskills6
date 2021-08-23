package com.santiago.worldskills6.ui.productoid

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiago.worldskills6.interfaces.ApiClient
import com.santiago.worldskills6.repository.local.DBManager
import com.santiago.worldskills6.repository.local.RepositoryDB
import com.santiago.worldskills6.repository.webservice.RepositoryProductoId
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class ProductoIdViewModel:ViewModel() {

    private val repositoryProductoId = RepositoryProductoId(ApiClient.webService)

    suspend fun getProductoId(idProducto:Int)= flow {
        try {
            emit(repositoryProductoId.getProductoId(idProducto))
        }catch (e:Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)

    suspend fun insertData(context : Context, id:Int, idProducto:Int, nombre:String, descripcion:String, url_imagen:String, precioUnidad:Int, precioTotal:Int, cantidad:Int) = flow {
        val repositoryDB = RepositoryDB(DBManager(context))
        try {
            emit(repositoryDB.insertData(id,idProducto,nombre,descripcion,url_imagen,precioUnidad,precioTotal,cantidad))
        }catch (e :Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)

}