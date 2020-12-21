package com.workerunionsupport.project

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_signup.etEmailAddress
import kotlinx.android.synthetic.main.activity_signup.etPassword

class SignupActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        firebaseAuth = FirebaseAuth.getInstance()

        textViewAlready.setOnClickListener {
            val intent = Intent(this, LoginOtp::class.java)
            startActivity(intent)
            finish()
        }

        btnSignUp.setOnClickListener {
            signUpUser()
        }
    }

    private fun signUpUser(){
        val email = etEmailAddress.text.toString()
        val password = etPassword.text.toString()
        val cnfPassword = etCnfPassword.text.toString()

        if(email.isBlank() || password.isBlank() || cnfPassword.isBlank()){
            Toast.makeText(this, "Email or Password can't be empty", Toast.LENGTH_SHORT).show()
            return
        }

        if(password != cnfPassword){
            Toast.makeText(this,"Password do not match",Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this,"Signup Successful",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
                }
            }
    }
}


