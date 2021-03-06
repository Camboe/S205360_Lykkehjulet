package com.example.s205360lykkehjulet.Data

import com.example.s205360lykkehjulet.model.CategoriesButton
import com.example.s205360lykkehjulet.R

// Inspiration from unit 2 Affimation app. Generates the cardviews.
class DataCategories {
    fun loadButtonContext(): List<CategoriesButton> {
        return listOf(
            CategoriesButton(R.string.button1_text, R.drawable.kendte),
            CategoriesButton(R.string.button2_text, R.drawable.bogtitler),
            CategoriesButton(R.string.button3_text, R.drawable.sport),
            CategoriesButton(R.string.button4_text, R.drawable.seveardigheder)
        )
    }
}