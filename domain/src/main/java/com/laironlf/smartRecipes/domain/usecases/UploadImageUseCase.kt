package com.laironlf.smartRecipes.domain.usecases

import com.laironlf.smartRecipes.domain.repository.TechnicalRepository
import java.io.File
import java.net.URI

class UploadImageUseCase(
    private val technicalRepository: TechnicalRepository
) {
    suspend operator fun invoke(file: File): String{
        return technicalRepository.uploadImage(file)
    }
}