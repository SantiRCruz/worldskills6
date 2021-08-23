package com.santiago.worldskills6.ui.registro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiago.worldskills6.interfaces.ApiClient
import com.santiago.worldskills6.repository.webservice.RepositoryProductoId
import com.santiago.worldskills6.repository.webservice.RepositoryRegistro
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import java.lang.Exception

class RegistroViewModel : ViewModel() {

    private val repositoryRegistro = RepositoryRegistro(ApiClient.webService)

    suspend fun getPoliticas() = flow {
        try {
            emit(repositoryRegistro.getPoliticas())
        }catch (e : Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)

    suspend fun postRegistro(nombre:String,correo:String,contrasena:String,ciudad:String) = flow {
        try {
            emit(repositoryRegistro.postRegistro(nombre,correo,contrasena,ciudad))
        }catch (e : Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)

    suspend fun getImg(id:Int) = flow {
        val repositoryRegistro = RepositoryProductoId(ApiClient.webService)

        try {
            emit(repositoryRegistro.getProductoId(id))
        }catch (e : Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)

}