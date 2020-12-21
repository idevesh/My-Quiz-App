package com.workerunionsupport.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login_intro.*
import java.lang.Exception

class LoginIntro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_intro)
        val firebaseAuth = FirebaseAuth.getInstance()

        if(firebaseAuth.currentUser != null){
            Toast.makeText(this, "Welcome Back", Toast.LENGTH_SHORT).show()
            redirect("MAIN")
        }

        btnGetStarted.setOnClickListener {
            redirect("LOGIN")
        }
    }

    private fun redirect(name:String){
        val intent = when(name){
            "LOGIN" -> Intent(this,LoginOtp::class.java)
            "MAIN" -> Intent(this, HomeActivity::class.java)
            else -> throw Exception("Some Error Occurred")
        }
        startActivity(intent)
        finish()
    }
}