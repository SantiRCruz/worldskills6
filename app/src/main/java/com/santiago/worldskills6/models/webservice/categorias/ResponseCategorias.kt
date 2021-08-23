package com.santiago.worldskills6.models.webservice.categorias

data class ResponseCategorias(
    var respuesta : String = "",
    var datos : ArrayList<Datos> = arrayListOf()
)