package com.santiago.worldskills6.ui.categorias

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiago.worldskills6.interfaces.ApiClient
import com.santiago.worldskills6.repository.webservice.RepositoryCategorias
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class CategoriasViewModel : ViewModel() {

    private val repositoryCategorias = RepositoryCategorias(ApiClient.webService)

    suspend fun getCategorias() = flow {
        try {
        emit(repositoryCategorias.getCategorias())
        }catch (e:Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)


}