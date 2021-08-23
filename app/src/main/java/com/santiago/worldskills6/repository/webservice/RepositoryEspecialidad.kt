package com.santiago.worldskills6.repository.webservice

import com.santiago.worldskills6.interfaces.ApiService
import com.santiago.worldskills6.models.webservice.especialidad.ResponseEspecialidad

class RepositoryEspecialidad(val apiService: ApiService) {

    suspend fun getEspecialidad():ResponseEspecialidad= apiService.getEspecialidad()

}