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
    companion object {
        const val INTENT_CODE = 780
    }
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
        var text = "wrong intent"
        if (requestCode == INTENT_CODE) {
            text = when(resultCode){
                (Activity.RESULT_OK)-> "hi, " + data?.getCharSequenceExtra("name").toString()
                else -> "why to do what?"
            }
        }
        Toast.makeText(applicationContext,text,Toast.LENGTH_LONG).show()
    }
}