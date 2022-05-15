package com.example.login_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login_firebase.databinding.ActivityMenuBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private lateinit var binding: ActivityMenuBinding
class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.logout.setOnClickListener {logout()}

    }
    fun logout(){
        Firebase.auth.signOut()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}