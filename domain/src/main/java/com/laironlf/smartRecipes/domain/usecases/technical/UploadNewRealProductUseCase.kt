package com.laironlf.smartRecipes.domain.usecases.technical

import com.laironlf.smartRecipes.domain.models.post.RealProductPost
import com.laironlf.smartRecipes.domain.repository.TechnicalRepository

class UploadNewRealProductUseCase(
    private val technicalRepository: TechnicalRepository
) {
    suspend operator fun invoke(realProductPost: RealProductPost){
        technicalRepository.uploadRealProduct(realProductPost)
    }
}