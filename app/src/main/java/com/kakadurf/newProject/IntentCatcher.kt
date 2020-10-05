package com.kakadurf.newProject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_intent_catcher.*
import java.net.URI

class IntentCatcher: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_catcher)
        Toast.makeText(applicationContext,intent.data.toString(),Toast.LENGTH_LONG).show()

        bt_send.setOnClickListener {
            val intent =Intent()
            intent.putExtra("name",et_name.text)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

}