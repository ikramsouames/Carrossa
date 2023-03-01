package com.example.carrossa

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.edit
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.carrossa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val navHostFragment = supportFragmentManager. findFragmentById(R.id.navHost) as NavHostFragment
         navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.navBottom,navController)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overflow_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.logout -> {
                val intent = Intent(this, login::class.java)
                val pref = getSharedPreferences("user_db", Context.MODE_PRIVATE)
                pref.edit { putBoolean("connected", false) }
                this.startActivity(intent)
                finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }
}