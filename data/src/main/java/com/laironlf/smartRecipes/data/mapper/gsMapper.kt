package com.laironlf.smartRecipes.data.mapper

import com.laironlf.smartRecipes.data.dto.productBarcodeResponce.ProductBarcodeResponce
import com.laironlf.smartRecipes.domain.models.ProductBarcodeData

fun ProductBarcodeResponce.mapToDomain(): ProductBarcodeData = ProductBarcodeData(
    name = this.gepirItem?.itemDataLine?.first()?.itemName?: "",
    brandName = this.gepirItem?.itemDataLine?.first()?.brandName?: "",
    barcode = this.gepirItem?.itemDataLine?.first()?.gepirRequestedKey?.requestedKeyValue?: ""
)