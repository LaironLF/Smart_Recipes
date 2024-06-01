package com.laironlf.smartRecipes.presentation.dialogs.bottomSheetProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.laironlf.smartRecipes.databinding.SheetDialogProductBinding
import com.laironlf.smartRecipes.domain.models.Product
import com.laironlf.smartRecipes.presentation.adapters.recyclerAdapters.ProductListAdapter
import com.laironlf.smartRecipes.presentation.dialogs.bottomSheetProduct.BottomSheetProductViewModel.State
import com.laironlf.smartRecipes.presentation.utils.bindTwoWay
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetProductFragment(
    private val onAddAction: (Product) -> Unit
): BottomSheetDialogFragment() {

    private var _binding: SheetDialogProductBinding? = null
    private val binding: SheetDialogProductBinding get() = _binding!!
    private val viewModel: BottomSheetProductViewModel by viewModels()
    private val productsAdapter: ProductListAdapter = ProductListAdapter(::onProductClick)

    private fun onProductClick(product: Product, i: Int) {
        onAddAction(product)
        dismiss()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SheetDialogProductBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViews()
        subscribeToViewModel()
        binding.searchProducts.requestFocus()
    }

    private fun setupRecyclerViews() {
        binding.recviewProducts.layoutManager = LinearLayoutManager(context)
        binding.recviewProducts.adapter = productsAdapter
    }

    private fun subscribeToViewModel() {
        binding.searchProducts.bindTwoWay(viewModel.query, viewLifecycleOwner)
        binding.vAddProduct.tietProductTitle.bindTwoWay(viewModel.newProductTitle, viewLifecycleOwner)

        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.progressBar.visibility = if (state is State.Loading) View.VISIBLE else View.GONE
            binding.recviewProducts.visibility = if(state is State.Empty) View.GONE else View.VISIBLE
            binding.tvDoesntExist.visibility = if (state is State.Empty) View.VISIBLE else View.GONE
            productsAdapter.productList = if (state is State.Loaded) state.products else emptyList()
        }
    }

    companion object {
        const val TAG = "BottomSheetFragment"
    }
}