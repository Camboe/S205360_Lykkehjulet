package com.example.s205360lykkehjulet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.s205360lykkehjulet.Data.DataCategories
import com.example.s205360lykkehjulet.adapter.ItemAdapter
import com.example.s205360lykkehjulet.databinding.ActivityMainBinding
import javax.sql.DataSource


class MainActivity : AppCompatActivity() {
    // Make the navController as a variable so i can use it in a method
    // Inspiration from unit 3 CupCake app.
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
    // Method for navigation up button in the app. Inspiration from unit 3 CupCake app.
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}
