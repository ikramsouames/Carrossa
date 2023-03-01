package com.example.carrossa

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carrossa.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    lateinit var binding:ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val pref = getSharedPreferences("user_db", Context.MODE_PRIVATE)
        var  con = pref.getBoolean("connected", false)


        if (con) {

            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
            finish()
        }
        binding.button2.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
            finish()
        }
    }
}