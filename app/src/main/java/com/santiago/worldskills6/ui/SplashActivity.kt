package com.santiago.worldskills6.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.santiago.worldskills6.R
import com.santiago.worldskills6.models.Constants
import com.santiago.worldskills6.repository.local.DBHelper
import com.santiago.worldskills6.ui.especialidad.EspecialidadActivity
import com.santiago.worldskills6.ui.iniciosesion.IniciarSesionActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        this.supportActionBar?.hide()
        val dbHelper = DBHelper(applicationContext)
        dbHelper.writableDatabase
        startTimer()
    }

    private fun startTimer() {
        object:CountDownTimer(3000,1000){
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                val sharedPreferences = getSharedPreferences("inicioSesion", Context.MODE_PRIVATE)
                val status = sharedPreferences.getBoolean(Constants.KEY_STATUS,false)
                if (status){
                    val intent = Intent(applicationContext, EspecialidadActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    val intent = Intent(applicationContext, IniciarSesionActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }.start()
    }
}