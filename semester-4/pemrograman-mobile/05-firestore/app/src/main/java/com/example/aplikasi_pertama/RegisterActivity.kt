package com.example.aplikasi_pertama

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.aplikasi_pertama.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private lateinit var binding: ActivityRegisterBinding
private lateinit var auth: FirebaseAuth
class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//      ketika tombol cancel ditekan
        binding.cancel.setOnClickListener {
            cancel()
        }
//      ketika tombol register ditekan
        binding.register.setOnClickListener {
            var username = binding.emailinput.text
            var password = binding.passInput.text
            val nama = binding.namaInput.text
            val telp = binding.phoneInput.text
            val alamat = binding.alamatInput.text

            signUp("$username", "$password")
            addData("$nama", "$telp", "$alamat")
        }



        auth = Firebase.auth
    }

    private fun cancel(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun showMainPage(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun updateUI(currentUser: FirebaseUser?){
        if(currentUser != null){
            finish()
        }
        showMainPage()
    }
    private fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    fun addData(nama:String, telp:String, alamat:String){
        val db = Firebase.firestore
        val user = hashMapOf(
            "nama" to nama,
            "no_telp" to telp,
            "alamat" to alamat
        )

// Add a new document with a generated ID
        db.collection("coba")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
}