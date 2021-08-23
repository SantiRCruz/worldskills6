package com.santiago.worldskills6.repository.webservice

import com.santiago.worldskills6.interfaces.ApiService
import com.santiago.worldskills6.models.webservice.iniciosesion.ResponseInicioSesion

class RepositoryInicioSesion (val apiService: ApiService) {

    suspend fun getInicioSesion(correo:String,contrasena:String):ResponseInicioSesion = apiService.getInicioSesion(correo,contrasena)

}