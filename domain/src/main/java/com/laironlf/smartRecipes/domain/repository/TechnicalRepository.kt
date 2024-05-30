package com.laironlf.smartRecipes.domain.repository

import java.io.File

interface TechnicalRepository {
    suspend fun uploadImage(file: File): String
}