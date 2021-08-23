package com.santiago.worldskills6.ui.categorias

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.santiago.worldskills6.databinding.FragmentCategoriasBinding
import com.santiago.worldskills6.models.webservice.categorias.ResponseCategorias
import com.santiago.worldskills6.ui.CategoriasAdapter
import kotlinx.coroutines.flow.collect

class CategoriasFragment : Fragment() {

    private val categoriasViewModel : CategoriasViewModel by viewModels()
    private var _binding: FragmentCategoriasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCategoriasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            categoriasViewModel.getCategorias().collect {
                when(it){
                    is ResponseCategorias ->{
                        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
                        val adapter = CategoriasAdapter(it.datos)
                        binding.recyclerView.adapter = adapter
                        binding.progressBar.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
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