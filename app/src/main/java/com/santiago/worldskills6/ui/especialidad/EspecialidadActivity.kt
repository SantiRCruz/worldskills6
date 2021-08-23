package com.santiago.worldskills6.ui.especialidad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.santiago.worldskills6.databinding.ActivityEspecialidadBinding
import com.santiago.worldskills6.models.webservice.especialidad.ResponseEspecialidad
import com.santiago.worldskills6.ui.MainActivity
import kotlinx.coroutines.flow.collect

class EspecialidadActivity : AppCompatActivity() {

    private val especialidadViewModel : EspecialidadViewModel by viewModels()
    private lateinit var binding: ActivityEspecialidadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        binding = ActivityEspecialidadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnContinuar()
        referenciar()
    }

    private fun referenciar() {
        lifecycleScope.launchWhenStarted {
            especialidadViewModel.getEspecialidad().collect {
                when(it){
                    is ResponseEspecialidad ->{
                        binding.txtNombre.text=it.datos.nombre
                        binding.txtDescripcion.text=it.datos.descripcion
                        binding.txtPrecio.text=" $ " + it.datos.precio
                        Glide.with(binding.root).load(it.datos.url_foto).into(binding.imageView2)
                        binding.progressBar.visibility = View.GONE
                        binding.container.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun btnContinuar() {
        binding.buttonContinuar.setOnClickListener {
            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


}