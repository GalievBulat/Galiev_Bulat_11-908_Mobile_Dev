package com.kakadurf.newProject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        botnav_bn.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.item_1-> {
                    render(Fragment1())
                    true
                }
                R.id.item_2-> {
                    render(Fragment2())
                    true
                }
                R.id.item_3-> {
                    render(Fragment3())
                    true
                }
                else -> false
            }
        }
        botnav_bn.setOnNavigationItemReselectedListener {  }


    }
    private fun render ( fr: Fragment){
            val trans = supportFragmentManager.beginTransaction()
            with(trans) {
                replace(R.id.frl_frame, fr)
                commit()
            }
            /*iView?.setColorFilter(0)
            iView = view
            iView?.setColorFilter(R.color.colorAccent)*/
        }
    }

