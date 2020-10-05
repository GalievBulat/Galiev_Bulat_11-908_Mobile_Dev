package com.kakadurf.newProject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_registation.*

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registation)
        bt_registrate.setOnClickListener{
            val name = et_name.text
            val email = et_email.text
            val age = et_age.text
            val intent = Intent(this,MainActivity::class.java).apply{
                putExtra("name",name)
                putExtra("email",email)
                putExtra("age",age)
            }
            println(name)
            startActivity(intent)
        }
    }
}