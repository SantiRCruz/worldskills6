package com.santiago.worldskills6.ui.iniciosesion

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.santiago.worldskills6.databinding.ActivityIniciarSesionBinding
import com.santiago.worldskills6.models.Constants
import com.santiago.worldskills6.models.webservice.iniciosesion.ResponseInicioSesion
import com.santiago.worldskills6.models.webservice.productoid.ResponseProductoId
import com.santiago.worldskills6.ui.especialidad.EspecialidadActivity
import com.santiago.worldskills6.ui.registro.RegistroActivity
import kotlinx.coroutines.flow.collect

class IniciarSesionActivity : AppCompatActivity() {
    private val iniciarSesionViewModel : IniciarSesionViewModel by viewModels()
    private lateinit var binding : ActivityIniciarSesionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        binding = ActivityIniciarSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        getImg()
        inicioSesion()
        pasarRegistro()
    }

    private fun getImg() {
        lifecycleScope.launchWhenStarted {
            iniciarSesionViewModel.getImg(14).collect {
                when(it){
                 is ResponseProductoId ->{
                     Glide.with(binding.root).load(it.productos.url_imagen).into(binding.backgroundImage)
                     binding.progressBar.visibility = View.GONE
                     binding.containerInicioSesion.visibility = View.VISIBLE
                 }
                }
            }
        }
    }


    private fun pasarRegistro() {
        binding.buttonRegistro.setOnClickListener {
            val intent = Intent(applicationContext, RegistroActivity::class.java)
            startActivity(intent)
        }
    }

    private fun inicioSesion() {
        binding.buttonInicioSesion.setOnClickListener {
            lifecycleScope.launchWhenStarted {
                iniciarSesionViewModel.getInicioSesion(binding.eTCorreo.text.toString(),binding.eTContrasena.text.toString()).collect {
                    when(it){
                        is ResponseInicioSesion ->{
                            if (it.respuesta == "OK"){
                                saveDataUser()
                                saveUserCredencials(it.nombre,it.idCliente.toString(),it.token)
                                saveStatus()
                                val intent = Intent(applicationContext, EspecialidadActivity::class.java)
                                startActivity(intent)
                                finish()
                            }else{
                                Snackbar.make(binding.root,"error en los datos",Snackbar.LENGTH_LONG).show()
                            }
                        }else -> Snackbar.make(binding.root,"error al iniciar sesion",Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun saveStatus() {
        val sharedPreferences = getSharedPreferences("inicioSesion",Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        edit.apply {
            putBoolean(Constants.KEY_STATUS,true)
        }.apply()
    }

    private fun saveUserCredencials(nombre:String,id:String,token:String) {
        val sharedPreferences = getSharedPreferences("inicioSesion",Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        edit.apply {
            putString(Constants.KEY_USER_NAME,nombre)
            putString(Constants.KEY_USER_ID,id)
            putString(Constants.KEY_USER_TOKEN,token)
        }.apply()
    }

    private fun saveDataUser() {
        val sharedPreferences = getSharedPreferences("inicioSesion",Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        if (binding.cbRecordar.isChecked){
            edit.apply {
                putString(Constants.KEY_CORREO,binding.eTCorreo.text.toString())
                putString(Constants.KEY_CONTRASENA,binding.eTContrasena.text.toString())
                putBoolean(Constants.KEY_RECORDAR_USER,true)
            }.apply()
        }else if(!binding.cbRecordar.isChecked){
            edit.apply {
                putString(Constants.KEY_CORREO,"")
                putString(Constants.KEY_CONTRASENA,"")
                putBoolean(Constants.KEY_RECORDAR_USER,false)
            }.apply()
        }
    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("inicioSesion",Context.MODE_PRIVATE)
        val correo = sharedPreferences.getString(Constants.KEY_CORREO,null)
        val contrasena = sharedPreferences.getString(Constants.KEY_CONTRASENA,null)
        val cbRecordar = sharedPreferences.getBoolean(Constants.KEY_RECORDAR_USER,false)

        binding.eTCorreo.setText(correo)
        binding.eTContrasena.setText(contrasena)
        if (cbRecordar)binding.cbRecordar.isChecked = true
    }
}