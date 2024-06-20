package com.laironlf.smartRecipes.presentation.fragments.fridge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import com.laironlf.smartRecipes.databinding.FragmentFridgeBinding
import com.laironlf.smartRecipes.domain.models.Product
import com.laironlf.smartRecipes.domain.models.ProductBarcodeData
import com.laironlf.smartRecipes.domain.usecases.technical.GetProductBarcodeDataUseCase
import com.laironlf.smartRecipes.presentation.adapters.recyclerAdapters.ProductListAdapter
import com.laironlf.smartRecipes.presentation.dialogs.bottomSheetProduct.BottomSheetProductFragment
import com.laironlf.smartRecipes.presentation.dialogs.bottomSheetRealProduct.BottomSheetRealProductFragment
import com.laironlf.smartRecipes.presentation.dialogs.loadingDialog.LoadingDialog
import com.laironlf.smartRecipes.presentation.fragments.fridge.FridgeViewModel.Action
import com.laironlf.smartRecipes.presentation.fragments.fridge.FridgeViewModel.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class FridgeFragment : Fragment() {

    private val viewModel: FridgeViewModel by viewModels()

    private var _binding: FragmentFridgeBinding? = null
    private val binding get() = _binding!!
    private val loadingDialog = LoadingDialog()

    private val productListAdapter = ProductListAdapter(::onProductClick)

    private val barcodeLauncher = registerForActivityResult(ScanContract()) { result ->
        if (result.contents == null) {
            Toast.makeText(context, "Cancelled", Toast.LENGTH_LONG).show()
        } else {
            viewModel.onScanProductResult(result.contents)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFridgeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabAddProduct.setOnClickListener{
            BottomSheetProductFragment(::onAddProduct).show(parentFragmentManager, BottomSheetProductFragment.TAG)
        }
        binding.fabAddProductScan.setOnClickListener {
            barcodeLauncher.launch(ScanOptions())
        }
        setupRecyclerView()
        subscribeToViewModel()

    }

    private fun onAddProduct(product: Product) {
        viewModel.onAddProduct(product)
    }

    private fun subscribeToViewModel() {

        viewModel.state.observe(viewLifecycleOwner) { state ->
            productListAdapter.productList = if (state is State.Loaded) state.products else emptyList()
            binding.recviewFridgeProducts.visibility = if(state is State.Loaded) View.VISIBLE else View.GONE
            binding.tvEmptyFridgeText.visibility = if (state is State.Empty) View.VISIBLE else View.GONE
        }
        lifecycleScope.launch {
            viewModel.actions.collect{ handleActions(it) }
        }
    }

    private fun handleActions(action: Action) {
        when(action){
            is Action.ShowToast -> Toast.makeText(context, action.message, Toast.LENGTH_SHORT).show()
            is Action.ShowRealProductInfo -> openBottomSheetRealProduct(action.productInfo)
            is Action.ShowLoadDialog -> loadingDialog.show(parentFragmentManager, "Dialog")
            is Action.CloseLoadDialog -> if (loadingDialog.showsDialog) loadingDialog.dismiss()
            else -> {}
        }
    }

    private fun openBottomSheetRealProduct(productInfo: ProductBarcodeData) {
        if (loadingDialog.showsDialog) loadingDialog.dismiss()
        BottomSheetRealProductFragment(productInfo, ::onAddProduct)
            .show(parentFragmentManager, BottomSheetRealProductFragment.TAG)
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
