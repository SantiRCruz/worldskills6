package com.santiago.worldskills6.ui.categoriaid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiago.worldskills6.interfaces.ApiClient
import com.santiago.worldskills6.repository.webservice.RepositoryCategoriaId
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class CategoriasIdViewModel: ViewModel() {

    private val repositoryCategoriaId = RepositoryCategoriaId(ApiClient.webService)

    suspend fun getCategoriaId(idCategoria:Int) = flow {
        try {
            emit(repositoryCategoriaId.getCategoriaId(idCategoria))
        }catch (e:Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)
}