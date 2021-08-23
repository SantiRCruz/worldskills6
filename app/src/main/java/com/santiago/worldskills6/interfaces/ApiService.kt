package com.santiago.worldskills6.interfaces

import com.santiago.worldskills6.models.webservice.categoriaid.ResponseCategoriaId
import com.santiago.worldskills6.models.webservice.categorias.ResponseCategorias
import com.santiago.worldskills6.models.webservice.enviopedido.BodyEnvioPedido
import com.santiago.worldskills6.models.webservice.enviopedido.ResponseEnvioPedido
import com.santiago.worldskills6.models.webservice.especialidad.ResponseEspecialidad
import com.santiago.worldskills6.models.webservice.historial.ResponseHistorial
import com.santiago.worldskills6.models.webservice.iniciosesion.ResponseInicioSesion
import com.santiago.worldskills6.models.webservice.politicas.ResponsePoliticas
import com.santiago.worldskills6.models.webservice.productoid.ResponseProductoId
import com.santiago.worldskills6.models.webservice.registro.BodyRegistro
import com.santiago.worldskills6.models.webservice.registro.ResponseRegistro
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {

    @POST("pedidos")
    suspend fun postPedido(@Body bodyEnvioPedido: BodyEnvioPedido ): ResponseEnvioPedido

    @GET("pedidos")
    suspend fun getHistorial(@Query("cliente")cliente:String,@Query("token")token:String): ResponseHistorial

    @GET("productos/{idProducto}")
    suspend fun getProductoId(@Path("idProducto")idProducto:Int): ResponseProductoId

    @GET("categorias")
    suspend fun getCategorias(): ResponseCategorias

    @GET("categorias/{idCategoria}")
    suspend fun getCategoriaId(@Path("idCategoria")idCategoria:Int): ResponseCategoriaId

    @GET("especialidad")
    suspend fun getEspecialidad(): ResponseEspecialidad

    @GET("politicas?ver")
    suspend fun getPoliticas(): ResponsePoliticas

    @POST("clientes")
    suspend fun postRegistro(@Body bodyRegistro: BodyRegistro): ResponseRegistro

    @GET("clientes")
    suspend fun getInicioSesion(@Query("correo")correo:String,@Query("contrasena")contrasena:String): ResponseInicioSesion

}
object ApiClient{
    val webService by lazy {
        Retrofit.Builder()
            .baseUrl("https://wsc.fabricasoftware.co/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}