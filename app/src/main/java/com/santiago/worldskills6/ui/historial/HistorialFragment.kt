package com.santiago.worldskills6.ui.historial

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.santiago.worldskills6.databinding.FragmentHistorialBinding
import com.santiago.worldskills6.models.Constants
import com.santiago.worldskills6.models.webservice.historial.ResponseHistorial
import com.santiago.worldskills6.ui.HistorialAdapter
import kotlinx.coroutines.flow.collect

class HistorialFragment : Fragment() {

    private val historialViewModel : HistorialViewModel by viewModels()
    private var _binding: FragmentHistorialBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHistorialBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            val sharedPreferences = requireActivity().getSharedPreferences("inicioSesion", Context.MODE_PRIVATE)
            val idCliente = sharedPreferences.getString(Constants.KEY_USER_ID,null)
            val token = sharedPreferences.getString(Constants.KEY_USER_TOKEN,null)
            historialViewModel.getHistorial(idCliente.toString(),token.toString()).collect {
                when(it){
                    is ResponseHistorial ->{
                        binding.rvHistorial.layoutManager = LinearLayoutManager(requireContext())
                        val adapter = HistorialAdapter(it.pedidos)
                        binding.rvHistorial.adapter = adapter
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