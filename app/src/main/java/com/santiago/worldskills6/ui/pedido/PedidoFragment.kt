package com.santiago.worldskills6.ui.pedido

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.santiago.worldskills6.R
import com.santiago.worldskills6.databinding.FragmentPedidoBinding
import com.santiago.worldskills6.models.Constants
import com.santiago.worldskills6.models.local.DBPedido
import com.santiago.worldskills6.models.webservice.enviopedido.ResponseEnvioPedido
import com.santiago.worldskills6.repository.local.DBManager
import com.santiago.worldskills6.ui.DialogPedido
import com.santiago.worldskills6.ui.PedidoAdapter
import kotlinx.coroutines.flow.collect

class PedidoFragment : Fragment() {

    private val pedidoViewModel : PedidoViewModel by viewModels()
    private var _binding: FragmentPedidoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPedidoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonFinalizarPedido.setOnClickListener {
            lifecycleScope.launchWhenStarted {
                val sharedPreferences = requireActivity().getSharedPreferences("inicioSesion", Context.MODE_PRIVATE)
                val idCliente = sharedPreferences.getString(Constants.KEY_USER_ID,null)
                val dbManager=DBManager(requireContext())
                val total = dbManager.sumTotal()
                val jsonPedido = dbManager.listPedido()
                if (!jsonPedido.isEmpty()){
                    pedidoViewModel.postEnvioPedido(idCliente!!.toInt(),jsonPedido.toString(),(total*1.19).toInt()).collect {
                        when(it){
                            is ResponseEnvioPedido ->{
                                if (it.respuesta=="OK"){
                                    val result = dbManager.deleteAll()
                                    if (result>0){
                                        val dialog = DialogPedido()
                                       dialog.show(requireActivity().supportFragmentManager,"pedido")
                                    }
                                }
                            }
                        }
                    }
                }else{
                    Snackbar.make(binding.root,"no tiene ningun pedido para enviar",Snackbar.LENGTH_LONG).show()
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            pedidoViewModel.listData(requireContext()).collect {
                val lista = it as List<DBPedido>
                if (!lista.isEmpty()){
                    binding.rvPedidos.layoutManager = LinearLayoutManager(requireContext())
                    val adapter = PedidoAdapter(lista)
                    binding.rvPedidos.adapter = adapter
                    binding.container.visibility = View.GONE
                    binding.rvPedidos.visibility = View.VISIBLE
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            pedidoViewModel.sumTotal(requireContext()).collect {
                when(it){
                    is Int ->{
                        binding.txtTotal.text = "Total : $"+it
                        binding.txtTotalIva.text = "Total IVA : $"+(it*1.19).toInt()
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