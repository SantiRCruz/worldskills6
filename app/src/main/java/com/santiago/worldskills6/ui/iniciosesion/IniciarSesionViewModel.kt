package com.santiago.worldskills6.ui.iniciosesion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiago.worldskills6.interfaces.ApiClient
import com.santiago.worldskills6.repository.webservice.RepositoryCategoriaId
import com.santiago.worldskills6.repository.webservice.RepositoryInicioSesion
import com.santiago.worldskills6.repository.webservice.RepositoryProductoId
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class IniciarSesionViewModel : ViewModel() {

    private val repositoryInicioSesion = RepositoryInicioSesion(ApiClient.webService)

    suspend fun getInicioSesion(correo:String,contrasena:String) = flow {
        try {
            emit(repositoryInicioSesion.getInicioSesion(correo,contrasena))
        }catch (e : Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)

    suspend fun getImg(id:Int) = flow {
        val repositoryInicioSesion = RepositoryProductoId(ApiClient.webService)

        try {
            emit(repositoryInicioSesion.getProductoId(id))
        }catch (e : Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)

}