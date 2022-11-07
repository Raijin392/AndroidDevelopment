package android.example.androiddevelopment.model

import android.example.androiddevelopment.repository.ClothesRepository
import android.example.androiddevelopment.repository.ShoesRepository

data class Product(
    val id : Int,
    val price : String,
    val brand : String,
    val model : String,
    val sizes : String,
    val description : String,
    val imageUrl : String
)