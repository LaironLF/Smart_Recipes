package com.laironlf.smartRecipes.presentation.dialogs.bottomSheetIngredients

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.laironlf.smartRecipes.R
import com.laironlf.smartRecipes.databinding.SheetDialogIngredientsBinding
import com.laironlf.smartRecipes.domain.models.MeasureType
import com.laironlf.smartRecipes.domain.models.Product
import com.laironlf.smartRecipes.domain.models.post.IngredientPost
import com.laironlf.smartRecipes.presentation.adapters.recyclerAdapters.ProductListAdapter
import com.laironlf.smartRecipes.presentation.dialogs.bottomSheetIngredients.SheetDialogIngredientsViewModel.State
import com.laironlf.smartRecipes.presentation.utils.bindTwoWay
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SheetDialogIngredients(
    private val onAddIngredientAction: (IngredientPost) -> Unit
): BottomSheetDialogFragment() {


    private var _binding: SheetDialogIngredientsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SheetDialogIngredientsViewModel by viewModels()
    private val productListAdapter = ProductListAdapter(::onProductClick)
    private var selectedProduct: Product? = null
    var wasDismissed: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SheetDialogIngredientsBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchProducts.bindTwoWay(viewModel.queryText, viewLifecycleOwner, ::onQueryChanged)
        binding.btnAddIngredient.setOnClickListener { addIngredientAction() }
        binding.btnClearSelectedProduct.setOnClickListener{ clear() }
        binding.tvAddNewProductOfferAction.setOnClickListener{ viewModel.onAddNewProductOfferClicked() }
        binding.vAddProduct.tietProductTitle.bindTwoWay(viewModel.newProductTitle, viewLifecycleOwner)
        binding.vAddProduct.btnAddProduct.setOnClickListener { viewModel.onAddNewProductClicked() }
        setupDropList()
        setupRecyclerViews()
        subscribeToViewModel()

        binding.searchProducts.requestFocus()
    }

    private fun onQueryChanged(query: String) = viewModel.startFetching(query)

    private fun setupDropList() {
        val adapterItems = ArrayAdapter<String>(requireContext(), R.layout.item_dropdown)
        adapterItems.clear()
        if (viewModel.measureTypes.value != null)
            adapterItems.addAll(viewModel.measureTypes.value!!.map { it.title })
        viewModel.measureTypes.observe(viewLifecycleOwner){ measureTypes ->
            adapterItems.clear()
            adapterItems.addAll(measureTypes.map { it.title })
        }
        binding.actvMeasureType.setAdapter(adapterItems)
    }

    private fun getSelectedMeasureType(): MeasureType? {
        val value = binding.actvMeasureType.text.toString()
        return viewModel.measureTypes.value!!.firstOrNull { it.title == value }
    }

    private fun setupRecyclerViews() {
        binding.recviewProducts.layoutManager = LinearLayoutManager(context)
        binding.recviewProducts.adapter = productListAdapter
    }

    private fun subscribeToViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.recviewProducts.visibility = if (state is State.Loaded) View.VISIBLE else View.GONE
            productListAdapter.productList = if (state is State.Loaded) state.products else emptyList()
            binding.searchProducts.visibility = if (state is State.Selected || state is State.AddProduct) View.GONE else View.VISIBLE
            binding.llIngredientParams.visibility = if (state is State.Selected) View.VISIBLE else View.GONE
            binding.btnAddIngredient.visibility = if (state is State.Selected) View.VISIBLE else View.GONE
            binding.progressBar.visibility = if (state is State.Loading) View.VISIBLE else View.GONE
            binding.llAddProductOffer.visibility = if (state is State.Empty) View.VISIBLE else View.GONE
            binding.vAddProduct.root.visibility = if (state is State.AddProduct) View.VISIBLE else View.GONE
            if (state is State.AddProduct){
                val productTitleView =  binding.vAddProduct.tietProductTitle
                productTitleView.requestFocus()
                Handler(Looper.getMainLooper()).postDelayed({
                    productTitleView.setSelection(productTitleView.text?.length ?: 0)
                    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    imm?.showSoftInput(productTitleView, InputMethodManager.SHOW_IMPLICIT)
                }, 100)
            }

        }
    }

    private fun clear(){
        selectedProduct = null
        binding.tvProductTitle.text = ""
        binding.etQuantity.text?.clear()
        binding.actvMeasureType.text.clear()
        viewModel.onClearAction()
    }

    private fun onProductClick(product: Product, position: Int){
        d(TAG, "onProductClick: ${product.title}")
        binding.tvProductTitle.text = product.title
        viewModel.productSelected()
        selectedProduct = product
    }

    private fun addIngredientAction(){
        val quantity = binding.etQuantity.text.toString().toIntOrNull()
        if (getSelectedMeasureType() == null) return
        if (selectedProduct == null) return
        val ingredient = IngredientPost(
            measureType = getSelectedMeasureType()!!,
            product = selectedProduct!!,
            quantity = quantity ?: 0
        )
        onAddIngredientAction(ingredient)
        clear()
        dismiss()
    }
    companion object {
        const val TAG = "AddRecipeIngredientsDialog"
    }
}