package com.santiago.worldskills6.ui.categoriaid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.santiago.worldskills6.databinding.FragmentCategoriaIdBinding
import com.santiago.worldskills6.models.Constants
import com.santiago.worldskills6.models.webservice.categoriaid.ResponseCategoriaId
import com.santiago.worldskills6.ui.CategoriaIdAdapter
import kotlinx.coroutines.flow.collect


class CategoriaIdFragment : Fragment() {
    private val categoriasIdViewModel : CategoriasIdViewModel by viewModels()
    private var _binding: FragmentCategoriaIdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCategoriaIdBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            categoriasIdViewModel.getCategoriaId(Constants.ID_CATEGORIA_ID).collect {
                when(it){
                    is ResponseCategoriaId ->{
                        binding.txtTitulo.text = it.nombre
                        binding.rvCategoriaId.layoutManager = LinearLayoutManager(requireContext())
                        val adapter = CategoriaIdAdapter(it.productos)
                        binding.rvCategoriaId.adapter = adapter
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