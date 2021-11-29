package com.example.s205360lykkehjulet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.s205360lykkehjulet.Data.DataCategories
import com.example.s205360lykkehjulet.adapter.ItemAdapter
import com.example.s205360lykkehjulet.databinding.ActivityMainBinding
import javax.sql.DataSource


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        //NavigationUI.setupActionBarWithNavController(this, navController)
        setupActionBarWithNavController(navController)


    }
}
