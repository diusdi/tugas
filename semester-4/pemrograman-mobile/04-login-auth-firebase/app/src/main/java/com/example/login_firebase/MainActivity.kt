package com.example.login_firebase

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isInvisible
import com.example.login_firebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private lateinit var binding: ActivityMainBinding
lateinit var auth: FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth


        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

         binding.login.setOnClickListener {
            var username = binding.username.text
            var password = binding.password.text
            signIn("$username", "$password")
        }
    }

    private fun showLandingPage(){
        val intent = Intent(this, MenuActivity::class.java).apply{
            putExtra(EXTRA_MESSAGE, "Selamat Datang")
        }
        startActivity(intent)
    }

    private fun updateUI(currentUser: FirebaseUser?){
        if(currentUser !== null) showLandingPage()
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    @SuppressLint("SetTextI18n")
    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                    binding.infoError.visibility =
                        if (binding.infoError.isInvisible) View.VISIBLE
                        else View.INVISIBLE
                    binding.infoError.text = "Username / Password salah ! !"
                }
            }
    }
}