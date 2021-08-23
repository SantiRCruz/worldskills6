package com.santiago.worldskills6.models.webservice.productoid

import com.santiago.worldskills6.models.webservice.categoriaid.Productos

data class ResponseProductoId (
    var respuesta:String = "",
    var productos: Productos = Productos()
        )