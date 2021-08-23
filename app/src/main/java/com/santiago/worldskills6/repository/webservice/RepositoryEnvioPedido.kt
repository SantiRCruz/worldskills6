package com.santiago.worldskills6.repository.webservice

import com.santiago.worldskills6.interfaces.ApiService
import com.santiago.worldskills6.models.webservice.enviopedido.BodyEnvioPedido
import com.santiago.worldskills6.models.webservice.enviopedido.ResponseEnvioPedido

class RepositoryEnvioPedido(val apiService: ApiService) {

    suspend fun postEnvioPedido(id_cliente:Int,json_pedido:String,total:Int):ResponseEnvioPedido = apiService.postPedido(BodyEnvioPedido(id_cliente,json_pedido,total))

}