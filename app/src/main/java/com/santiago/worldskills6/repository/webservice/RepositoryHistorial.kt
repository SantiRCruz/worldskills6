package com.santiago.worldskills6.repository.webservice

import com.santiago.worldskills6.interfaces.ApiService
import com.santiago.worldskills6.models.webservice.historial.ResponseHistorial

class RepositoryHistorial (val apiService: ApiService){

    suspend fun getHistorial(idCliente:String,token:String):ResponseHistorial = apiService.getHistorial(idCliente,token)
}