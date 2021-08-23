package com.santiago.worldskills6.ui.productoid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.santiago.worldskills6.R
import com.santiago.worldskills6.databinding.FragmentProductoIdBinding
import com.santiago.worldskills6.models.Constants
import com.santiago.worldskills6.models.webservice.productoid.ResponseProductoId
import com.santiago.worldskills6.repository.local.DBManager
import kotlinx.coroutines.flow.collect

class ProductoIdFragment : Fragment() {
    private val productoIdViewModel : ProductoIdViewModel by viewModels()
    private var _binding: FragmentProductoIdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProductoIdBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            productoIdViewModel.getProductoId(Constants.ID_PRODUCTO_ID).collect { producto ->
                when(producto){
                    is ResponseProductoId ->{
                        binding.txtNombre.text = producto.productos.nombre
                        binding.txtDescripcion.text = producto.productos.descripcion
                        binding.txtPrecio.text = " $ " + producto.productos.precio
                        Glide.with(binding.root).load(producto.productos.url_imagen).into(binding.imageView5)
                        binding.buttonAgregar.setOnClickListener {
                            val dbManager = DBManager(requireContext())
                            val lista = dbManager.listAcumulacion(producto.productos.id)
                            if (lista.isEmpty()){
                                lifecycleScope.launchWhenStarted {
                                    productoIdViewModel.insertData(requireContext(),0,producto.productos.id,producto.productos.nombre,producto.productos.descripcion,producto.productos.url_imagen,producto.productos.precio,producto.productos.precio,1).collect { insert ->
                                        when(insert){
                                            is Long ->{
                                                if (insert>0){
                                                    Navigation.findNavController(binding.root).navigate(R.id.navigation_pedido)
                                                }
                                            }
                                        }
                                    }
                                }
                            }else{
                                val result = dbManager.updateData(lista[0].id,lista[0].precioUnidad*(lista[0].cantidad+1),lista[0].cantidad+1)
                                if (result>0){
                                    Navigation.findNavController(binding.root).navigate(R.id.navigation_pedido)
                                }
                            }
                        }
                        binding.progressBar.visibility = View.GONE
                        binding.container.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}