package com.santiago.worldskills6.ui.especialidad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiago.worldskills6.interfaces.ApiClient
import com.santiago.worldskills6.repository.webservice.RepositoryEspecialidad
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import java.lang.Exception

class EspecialidadViewModel:ViewModel() {

    val repositoryEspecialidad = RepositoryEspecialidad(ApiClient.webService)

    suspend fun getEspecialidad() = flow {
        try {
        emit(repositoryEspecialidad.getEspecialidad())
        }catch (e:Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)

}