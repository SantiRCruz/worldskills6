package com.santiago.worldskills6.repository.webservice

import com.santiago.worldskills6.interfaces.ApiService
import com.santiago.worldskills6.models.webservice.politicas.ResponsePoliticas
import com.santiago.worldskills6.models.webservice.registro.BodyRegistro
import com.santiago.worldskills6.models.webservice.registro.ResponseRegistro

class RepositoryRegistro(val apiService: ApiService) {

    suspend fun postRegistro(nombre:String,correo:String,contrasena:String,ciudad:String):ResponseRegistro = apiService.postRegistro(BodyRegistro(nombre,correo,contrasena,ciudad))

        suspend fun getPoliticas():ResponsePoliticas = apiService.getPoliticas()
}