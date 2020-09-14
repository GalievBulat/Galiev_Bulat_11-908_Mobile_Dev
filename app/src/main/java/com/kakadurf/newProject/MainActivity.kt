package com.kakadurf.newProject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fox = Fox("Steve",3,true,120)
        var cat: Pet? = Cat("John",1,true,80)
        println(fox)
        println(cat)
        println(fox.makeASound())
        println(cat?.makeASound()?: "not found")
        println(fox.makeAFunnySound())
        println(cat?.makeAFunnySound()?: "not found")
        cat?.ageUp()
        fox.ageUp()
        println(fox)
        println(cat)
        cat = fox.steal(cat)
        println(cat)
        println(fox.sayThatHeHadVanished())
    }
}