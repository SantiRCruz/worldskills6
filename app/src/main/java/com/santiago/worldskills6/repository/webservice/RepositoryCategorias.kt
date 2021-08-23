package com.santiago.worldskills6.repository.webservice

import com.santiago.worldskills6.interfaces.ApiService
import com.santiago.worldskills6.models.webservice.categorias.ResponseCategorias

class RepositoryCategorias(val apiService: ApiService){

    suspend fun getCategorias():ResponseCategorias = apiService.getCategorias()

}