package com.kakadurf.newProject

import android.graphics.ColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private  var iView:ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        render(iv_1,Fragment1())
        render(iv_2,Fragment2())
        render(iv_3,Fragment3())
        render(iv_4,Fragment4())
        render(iv_5,Fragment5())
    }
    private fun render (view: ImageView, fr: Fragment){
        view.setOnClickListener{
            val trans = supportFragmentManager.beginTransaction()
            with(trans) {
                replace(R.id.fl, fr)
                commit()
            }
            iView?.setColorFilter(0)
            iView = view
            iView?.setColorFilter(R.color.colorAccent)
        }
    }
}