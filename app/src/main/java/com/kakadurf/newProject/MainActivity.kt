package com.kakadurf.newProject

import android.app.Activity
import android.content.Intent
import android.content.Intent.*
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val INTENT_CODE  = 780
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt_send_intent.setOnClickListener{
            val intent = Intent(CATEGORY_MONKEY)
            intent.data = Uri.parse("new_project://kakadurf.com:1234/hi")
            startActivityForResult(intent,INTENT_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == INTENT_CODE) {
            if (resultCode == Activity.RESULT_OK){
                Toast.makeText(applicationContext,"hi, "
                        + data?.getCharSequenceExtra("name"),Toast.LENGTH_LONG).show()
            }else
                Toast.makeText(applicationContext,"why to do what?",Toast.LENGTH_LONG).show()
        } else
            Toast.makeText(applicationContext,"wrong intent",Toast.LENGTH_LONG).show()
    }
}