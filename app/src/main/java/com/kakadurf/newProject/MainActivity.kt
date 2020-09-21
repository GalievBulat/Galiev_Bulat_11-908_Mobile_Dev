package com.kakadurf.newProject

import android.app.ActionBar
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide();
        change_the_pr.setOnClickListener{
            et_setName.visibility= View.VISIBLE
            main_name.visibility = View.INVISIBLE
            button_ok.visibility= View.VISIBLE
        }
        button_ok.setOnClickListener{
            et_setName.visibility= View.INVISIBLE
            main_name.text = et_setName.text
            main_name.visibility = View.VISIBLE
            button_ok.visibility= View.INVISIBLE
        }
    }
}