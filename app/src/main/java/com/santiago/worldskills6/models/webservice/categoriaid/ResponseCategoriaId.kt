package com.santiago.worldskills6.models.webservice.categoriaid

data class ResponseCategoriaId(
    var respuesta : String = "",
    var nombre : String = "",
    var descripcion : String = "",
    var productos : ArrayList<Productos> = arrayListOf()
)
