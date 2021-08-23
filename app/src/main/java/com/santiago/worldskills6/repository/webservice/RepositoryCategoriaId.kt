package com.santiago.worldskills6.repository.webservice

import com.santiago.worldskills6.interfaces.ApiService
import com.santiago.worldskills6.models.webservice.categoriaid.ResponseCategoriaId

class RepositoryCategoriaId (val apiService: ApiService){

    suspend fun getCategoriaId(idCategoria:Int):ResponseCategoriaId = apiService.getCategoriaId(idCategoria)

}