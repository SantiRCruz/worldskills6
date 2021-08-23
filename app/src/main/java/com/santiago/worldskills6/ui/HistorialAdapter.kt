package com.santiago.worldskills6.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.santiago.worldskills6.R
import com.santiago.worldskills6.databinding.ItemHistorialBinding
import com.santiago.worldskills6.models.Constants
import com.santiago.worldskills6.models.webservice.historial.Pedidos

class HistorialAdapter(val historial : List<Pedidos>): RecyclerView.Adapter<HistorialAdapter.HistorialHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistorialHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return HistorialHolder(layoutInflater.inflate(R.layout.item_historial,parent,false),parent.context)
    }

    override fun onBindViewHolder(holder: HistorialHolder, position: Int) {
        holder.bind(historial[position])
    }

    override fun getItemCount(): Int =historial.size

    class HistorialHolder(val view: View,val context: Context): RecyclerView.ViewHolder(view){
        private val binding = ItemHistorialBinding.bind(view)
        fun bind(historial: Pedidos){
            val sharedPreferences = context.getSharedPreferences("inicioSesion", Context.MODE_PRIVATE)
            val nombre = sharedPreferences.getString(Constants.KEY_USER_NAME,null)
            binding.txtId.text = "Pedido :" +historial.id.toString()
            binding.txtFecha.text = historial.created_at
            binding.txtTotal.text = "Total : $ " + historial.total
            binding.txtNombre.text = "señor@ :" +nombre
        }
    }
}