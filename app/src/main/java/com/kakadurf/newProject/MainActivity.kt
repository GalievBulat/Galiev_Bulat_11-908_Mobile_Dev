package com.kakadurf.newProject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repository = UserRepository()
        val userAdapter =  UserAdapter(repository.getAll()){
            val intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("id",it)
            startActivity(intent)
        }
        rv_main.adapter = userAdapter
    }
}