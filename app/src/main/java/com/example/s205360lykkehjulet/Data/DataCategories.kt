package com.example.s205360lykkehjulet.Data

import com.example.s205360lykkehjulet.model.CategoriesButton
import com.example.s205360lykkehjulet.R

//inspiration from unit 2 Affimation app
class DataCategories {
    fun loadButtonContext (): List<CategoriesButton> {
        return listOf<CategoriesButton>(
            CategoriesButton(R.string.button1_text, R.drawable.kendte),
            CategoriesButton(R.string.button2_text, R.drawable.bogtitler),
            CategoriesButton(R.string.button3_text, R.drawable.sport),
            CategoriesButton(R.string.button4_text, R.drawable.seveardigheder)
                )
    }
}