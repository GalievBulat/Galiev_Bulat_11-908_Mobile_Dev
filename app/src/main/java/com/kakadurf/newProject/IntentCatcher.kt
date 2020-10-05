package com.kakadurf.newProject

import android.content.Intent
import android.content.Intent.EXTRA_TEXT
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
        tv_text.text = intent.getStringExtra(EXTRA_TEXT)
    }

}