package com.santiago.worldskills6.ui.registro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.santiago.worldskills6.databinding.ActivityRegistroBinding
import com.santiago.worldskills6.models.webservice.politicas.ResponsePoliticas
import com.santiago.worldskills6.models.webservice.productoid.ResponseProductoId
import com.santiago.worldskills6.models.webservice.registro.ResponseRegistro
import com.santiago.worldskills6.ui.especialidad.EspecialidadActivity
import com.santiago.worldskills6.ui.iniciosesion.IniciarSesionActivity
import kotlinx.coroutines.flow.collect

class RegistroActivity : AppCompatActivity() {

    private val registroViewModel : RegistroViewModel by viewModels()
    private lateinit var binding: ActivityRegistroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getImg()
        registro()
        pasarInicioSesion()
    }
    private fun getImg() {
        lifecycleScope.launchWhenStarted {
            registroViewModel.getImg(14).collect {
                when(it){
                    is ResponseProductoId ->{
                        Glide.with(binding.root).load(it.productos.url_imagen).into(binding.backgroundImage)
                        binding.progressBar.visibility = View.GONE
                        binding.containerRegistro.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun pasarInicioSesion() {
        binding.buttonInicioSesion.setOnClickListener {
            val intent = Intent(applicationContext,IniciarSesionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registro() {
        binding.buttonRegistro.setOnClickListener {
            if (binding.eTNombre.text.toString().isEmpty()||binding.eTCorreo.text.toString().isEmpty()||binding.eTContrasena.text.toString().isEmpty()||binding.eTCiudad.text.toString().isEmpty()){
                Snackbar.make(binding.root,"debe tener todos los campos llenos",Snackbar.LENGTH_LONG).show()
            }else{
                lifecycleScope.launchWhenStarted {
                    registroViewModel.getPoliticas().collect { politicas ->
                        when(politicas){
                            is ResponsePoliticas ->{
                                if (politicas.respuesta == "OK"){
                                    val dialog = AlertDialog.Builder(this@RegistroActivity)
                                        .setTitle("políticas de privacidad y protección de datos")
                                        .setMessage(politicas.datos.politicas)
                                        .setNegativeButton("CANCELAR"){Dialog , with->
                                            Snackbar.make(binding.root,"no se creo la cuenta, usted rechazo los terminos y condiciones",Snackbar.LENGTH_LONG).show()
                                        }
                                        .setPositiveButton("ACEPTAR"){Dialog , with->
                                            lifecycleScope.launchWhenStarted {
                                                registroViewModel.postRegistro(binding.eTNombre.text.toString(),binding.eTCorreo.text.toString(),binding.eTContrasena.text.toString(),binding.eTCiudad.text.toString()).collect { registro ->
                                                    when(registro){
                                                     is ResponseRegistro ->{
                                                         if (registro.respuesta=="OK"){
                                                             val intent = Intent(applicationContext,
                                                                 EspecialidadActivity::class.java)
                                                             startActivity(intent)
                                                             finish()
                                                         }else{
                                                             Snackbar.make(binding.root,registro.mensaje,Snackbar.LENGTH_LONG).show()
                                                         }
                                                     }
                                                    }
                                                }
                                            }
                                        }
                                        .setCancelable(false)
                                        .create()
                                    dialog.show()
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}