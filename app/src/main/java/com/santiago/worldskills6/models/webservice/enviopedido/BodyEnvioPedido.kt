package com.santiago.worldskills6.models.webservice.enviopedido

data class BodyEnvioPedido(
    var id_cliente :Int = 20,
    var json_pedido :String = "",
    var total :Int = 36000
)
