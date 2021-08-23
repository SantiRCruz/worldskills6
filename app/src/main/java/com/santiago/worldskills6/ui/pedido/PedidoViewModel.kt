package com.santiago.worldskills6.ui.pedido

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiago.worldskills6.interfaces.ApiClient
import com.santiago.worldskills6.repository.local.DBManager
import com.santiago.worldskills6.repository.local.RepositoryDB
import com.santiago.worldskills6.repository.webservice.RepositoryEnvioPedido
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class PedidoViewModel : ViewModel() {

    suspend fun listData(context: Context) = flow {
        val repositoryDB = RepositoryDB(DBManager(context))
        try {
            emit(repositoryDB.listData())
        }catch (e :Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)

    suspend fun sumTotal(context: Context) = flow {
        val repositoryDB = RepositoryDB(DBManager(context))
        try {
            emit(repositoryDB.sumTotal())
        }catch (e :Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)

    suspend fun postEnvioPedido(id_cliente:Int,json_pedido:String,total:Int) = flow {
        val repositoryEnvioPedido = RepositoryEnvioPedido(ApiClient.webService)
        try {
            emit(repositoryEnvioPedido.postEnvioPedido(id_cliente,json_pedido,total))
        }catch (e :Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)

}