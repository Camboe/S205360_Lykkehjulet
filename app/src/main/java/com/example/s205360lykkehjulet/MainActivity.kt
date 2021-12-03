package com.example.s205360lykkehjulet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.s205360lykkehjulet.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    /**
     * Inspiration from unit 3 CupCake app.
     * https://developer.android.com/codelabs/basic-android-kotlin-training-shared-viewmodel?hl=ar&continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-3-pathway-4%3Fhl%3Dar%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-shared-viewmodel#2
     * pictures for my application is found on https://www.pngwing.com/
     */
    // Make the navController as a variable so i can use it in a method
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        NavigationUI.setupActionBarWithNavController(this, navController)


    }

    /**
     * Inspiration from unit 3 CupCake app.
     * https://developer.android.com/codelabs/basic-android-kotlin-training-shared-viewmodel?hl=ar&continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-3-pathway-4%3Fhl%3Dar%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-shared-viewmodel#2
     */
    // Method for navigation up button in the app.
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}
