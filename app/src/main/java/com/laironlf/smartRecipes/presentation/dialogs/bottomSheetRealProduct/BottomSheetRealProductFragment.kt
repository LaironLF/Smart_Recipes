package com.laironlf.smartRecipes.presentation.dialogs.bottomSheetRealProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.laironlf.smartRecipes.R
import com.laironlf.smartRecipes.databinding.SheetDialogRealProductBinding
import com.laironlf.smartRecipes.domain.models.Product
import com.laironlf.smartRecipes.domain.models.ProductBarcodeData
import com.laironlf.smartRecipes.domain.models.params.GetProductListParams
import com.laironlf.smartRecipes.domain.models.post.RealProductPost
import com.laironlf.smartRecipes.domain.usecases.product.GetProductsUseCase
import com.laironlf.smartRecipes.domain.usecases.technical.UploadNewRealProductUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BottomSheetRealProductFragment(
    private val productBarcodeData: ProductBarcodeData,
    private val onProductAdd: (Product) -> Unit
): BottomSheetDialogFragment() {

    private var _binding: SheetDialogRealProductBinding? = null
    private val binding: SheetDialogRealProductBinding get() = _binding!!
    private var productList: List<Product> = emptyList()
    @Inject lateinit var getProductsUseCase: GetProductsUseCase
    @Inject lateinit var uploadNewRealProductUseCase: UploadNewRealProductUseCase
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SheetDialogRealProductBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewRealProductInfo.tietProductTitle.setText(productBarcodeData.name)
        binding.viewRealProductInfo.tietBrandTitle.setText(productBarcodeData.brandName)
        binding.btnAddProduct.setOnClickListener { onAddBtnClick() }
        try {
            val bitmap = BarcodeEncoder().encodeBitmap(productBarcodeData.barcode, BarcodeFormat.EAN_13, 400, 400)
            binding.viewRealProductInfo.ivBarcode.setImageBitmap(bitmap)
            binding.viewRealProductInfo.tvBarcode.text = productBarcodeData.barcode
        } catch (_: Exception){ }

        lifecycleScope.launch {
            val adapterItems = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1)
            productList = getProductsUseCase(GetProductListParams(GetProductListParams.FetchType.AllRemoteProducts))
            adapterItems.addAll(productList.map { it.title })
            binding.actvProductType.setAdapter(adapterItems)
        }
    }

    private fun getSelectedProduct(): Product? {
        val title = binding.actvProductType.text.toString()
        return productList.firstOrNull{ it.title == title }
    }

    private fun onAddBtnClick() = lifecycleScope.launch {
        val product = getSelectedProduct()
        if(product == null) {
            Toast.makeText(context, "Надо выбрать тип продукта!", Toast.LENGTH_SHORT).show()
            return@launch
        }
        val realProductPost = RealProductPost(
            barcode = productBarcodeData.barcode,
            title = productBarcodeData.name,
            description = productBarcodeData.brandName,
            idProduct = product.id
        )
        try {
            uploadNewRealProductUseCase(realProductPost)
            onProductAdd(product)
            dismiss()
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    companion object{
        const val TAG = "BottomSheetRealProductFragment"
    }
}