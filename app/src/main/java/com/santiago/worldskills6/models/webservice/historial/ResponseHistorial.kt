package com.santiago.worldskills6.models.webservice.historial

data class ResponseHistorial(
    var respuesta:String =  "¿",
    var pedidos: ArrayList<Pedidos> = arrayListOf()
)
