package com.santiago.worldskills6.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.santiago.worldskills6.R
import com.santiago.worldskills6.databinding.ItemCategoriaIdBinding
import com.santiago.worldskills6.models.Constants
import com.santiago.worldskills6.models.local.DBPedido
import com.santiago.worldskills6.models.webservice.categoriaid.Productos
import com.santiago.worldskills6.repository.local.DBManager
import kotlinx.coroutines.flow.collect

class CategoriaIdAdapter(val categoriaId : List<Productos>): RecyclerView.Adapter<CategoriaIdAdapter.CategoriaIdHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaIdHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return CategoriaIdHolder(layoutInflater.inflate(R.layout.item_categoria_id,parent,false),parent.context)
    }

    override fun onBindViewHolder(holder: CategoriaIdHolder, position: Int) {
        holder.bind(categoriaId[position])
    }

    override fun getItemCount(): Int =categoriaId.size

    class CategoriaIdHolder(val view: View,val context: Context): RecyclerView.ViewHolder(view){
        private val binding = ItemCategoriaIdBinding.bind(view)
        val dbManager = DBManager(context)
        fun bind(categoriaId: Productos){
            binding.txtNombre.text = categoriaId.nombre
            binding.txtDescipcion.text = categoriaId.descripcion
            binding.txtPrecio.text = " $  " + categoriaId.precio
            Glide.with(binding.root).load(categoriaId.url_imagen).into(binding.imageView4)
            binding.root.setOnClickListener {
                Constants.ID_PRODUCTO_ID = categoriaId.id
                Navigation.findNavController(binding.root).navigate(R.id.navigation_producto_id)
            }
            binding.buttonAgregar.setOnClickListener {
                val lista = dbManager.listAcumulacion(categoriaId.id)
                if (lista.isEmpty()){
                    val result = dbManager.insertData(DBPedido(0,categoriaId.id,categoriaId.nombre,categoriaId.descripcion,categoriaId.url_imagen,categoriaId.precio,categoriaId.precio,1))
                    if (result>0){
                        Navigation.findNavController(binding.root).navigate(R.id.navigation_pedido)
                    }
                }else{
                    val result = dbManager.updateData(lista[0].id,lista[0].precioUnidad*(lista[0].cantidad+1),lista[0].cantidad+1)
                    if (result>0){
                        Navigation.findNavController(binding.root).navigate(R.id.navigation_pedido)
                    }
                }
            }
        }
    }
}