package com.kakadurf.newProject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val id=intent.getIntExtra("id",0)
        val repository = UserRepository()
        val user = repository.findById(id)
        user?.let {
            with(user) {
                iv_avatar.setImageResource(photoId)
                tv_name.text = "Name: $name"
                tv_desc.text = "Description: $description"
                tv_job.text = "Job: $job"
            }
        }
    }

}