package com.santiago.worldskills6.ui.historial

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiago.worldskills6.interfaces.ApiClient
import com.santiago.worldskills6.repository.webservice.RepositoryHistorial
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class HistorialViewModel : ViewModel() {

    private val repositoryHistorial = RepositoryHistorial(ApiClient.webService)

    suspend fun getHistorial(idCliente:String,token:String) = flow {
        try {
            emit(repositoryHistorial.getHistorial(idCliente,token))
        }catch (e : Exception){
         emit(e)
        }
    }.stateIn(viewModelScope)

}