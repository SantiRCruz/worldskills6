package com.santiago.worldskills6.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.santiago.worldskills6.R
import com.santiago.worldskills6.databinding.ActivityMainBinding
import com.santiago.worldskills6.models.Constants
import com.santiago.worldskills6.ui.iniciosesion.IniciarSesionActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

         navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_categorias, R.id.navigation_pedido, R.id.navigation_cena
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.popBackStack()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.cerrarSesion->{
                val sharedPreferences = getSharedPreferences("inicioSesion", Context.MODE_PRIVATE)
                val edit = sharedPreferences.edit()
                edit.apply {
                    putBoolean(Constants.KEY_STATUS,false)
                }.apply()
                val intent = Intent(applicationContext,IniciarSesionActivity::class.java)
                startActivity(intent)
                finish()
            }
        }


        return super.onOptionsItemSelected(item)
    }
}