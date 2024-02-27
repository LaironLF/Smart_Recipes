package com.laironlf.smartRecipes.presentation.fragments.fridge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.laironlf.smartRecipes.databinding.FragmentFridgeBinding
import com.laironlf.smartRecipes.domain.models.Product
import com.laironlf.smartRecipes.presentation.adapters.ProductListAdapter
import com.laironlf.smartRecipes.presentation.fragments.fridge.FridgeViewModel.State
import com.laironlf.smartRecipes.presentation.utils.bindTwoWay
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FridgeFragment : Fragment() {

    private val viewModel: FridgeViewModel by viewModels()

    private var _binding: FragmentFridgeBinding? = null
    private val binding get() = _binding!!

    private val productListAdapter = ProductListAdapter(::onProductClick)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFridgeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        subscribeToViewModel()
        binding.tlProductsPage.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewModel.onTabSelected(tab?.position ?: 0 )
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

    }

    private fun subscribeToViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            productListAdapter.productList = if (state is State.Loaded) state.products else emptyList()
            binding.recviewFridgeProducts.visibility = if(state is State.Loaded) View.VISIBLE else View.GONE
        }
    }

    private fun setupRecyclerView() {
        binding.recviewFridgeProducts.layoutManager = LinearLayoutManager(context)
        binding.recviewFridgeProducts.adapter = productListAdapter
    }

    private fun onProductClick(product: Product, position: Int){
        viewModel.onProductClick(product, position)
        productListAdapter.deleteItem(position)
    }

    companion object {
        fun newInstance() = FridgeFragment()
    }
}
