package com.santiago.worldskills6.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.santiago.worldskills6.R
import com.santiago.worldskills6.databinding.ItemCategoriasBinding
import com.santiago.worldskills6.databinding.ItemPedidoBinding
import com.santiago.worldskills6.models.Constants
import com.santiago.worldskills6.models.local.DBPedido
import com.santiago.worldskills6.models.webservice.categorias.Datos
import com.santiago.worldskills6.repository.local.DBManager

class PedidoAdapter(val pedido : List<DBPedido>): RecyclerView.Adapter<PedidoAdapter.PedidoHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return PedidoHolder(layoutInflater.inflate(R.layout.item_pedido,parent,false),parent.context)
    }

    override fun onBindViewHolder(holder: PedidoHolder, position: Int) {
        holder.bind(pedido[position])
    }

    override fun getItemCount(): Int =pedido.size

    class PedidoHolder(val view: View,val context:Context): RecyclerView.ViewHolder(view){
        private val binding = ItemPedidoBinding.bind(view)
        val dbManager = DBManager(context)
        fun bind(pedido: DBPedido){
            if (pedido.cantidad > 1 ) binding.imgRes.setImageResource(R.drawable.ic_baseline_horizontal_rule_24)
            binding.txtCantidad.text = pedido.cantidad.toString()
            binding.txtPrecioUnidad.text = "Precio Unidad : $ " + pedido.precioUnidad
            binding.txtTotal.text = "Precio Total : $ " + pedido.precioTotal
            Glide.with(binding.root).load(pedido.url_imagen).into(binding.imgItem)
            binding.imgRes.setOnClickListener {
                if (pedido.cantidad == 1){
                    val result = dbManager.deleteId(pedido.id)
                    if (result>0){
                        Navigation.findNavController(binding.root).navigate(R.id.navigation_pedido)
                    }
                }else{
                    val result = dbManager.updateData(pedido.id,pedido.precioUnidad*(pedido.cantidad-1),pedido.cantidad-1)
                    if (result>0){
                        Navigation.findNavController(binding.root).navigate(R.id.navigation_pedido)
                    }
                }
            }
            binding.imgSum.setOnClickListener {
                val result = dbManager.updateData(pedido.id,pedido.precioUnidad*(pedido.cantidad+1),pedido.cantidad+1)
                if (result>0){
                    Navigation.findNavController(binding.root).navigate(R.id.navigation_pedido)
                }
            }
        }
    }
}