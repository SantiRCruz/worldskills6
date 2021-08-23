package com.santiago.worldskills6.repository.webservice

import com.santiago.worldskills6.interfaces.ApiService
import com.santiago.worldskills6.models.webservice.productoid.ResponseProductoId

class RepositoryProductoId (val apiService: ApiService){

    suspend fun getProductoId(idProducto:Int):ResponseProductoId  = apiService.getProductoId(idProducto)

}