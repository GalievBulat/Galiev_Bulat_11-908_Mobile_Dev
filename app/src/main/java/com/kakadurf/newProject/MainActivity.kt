package com.kakadurf.newProject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        intent.extras?.let {
            tv_main_name.text =  it.getCharSequence("name")
            tv_user_id.text = "@" + it.getCharSequence("email")
            tv_nick.text =  it.getCharSequence("name")
            tv_annot.text = "@" + it.getCharSequence("email")
            val dateFormat = SimpleDateFormat("yyyy", Locale.getDefault())
            val dateText = dateFormat.format(Calendar.getInstance().time)
            if (it.getCharSequence("age").toString().matches(Regex("\\d+"))) {
                val time = dateText.toInt() - it.getCharSequence("age").toString().toInt()
                tv_reg.text = "Регистрация: " + (time) + "г."
            }
        }
        bt_change_the_pr.setOnClickListener{
            et_setName.visibility= View.VISIBLE
            tv_main_name.visibility = View.INVISIBLE
            bt_ok.visibility= View.VISIBLE
        }
        bt_ok.setOnClickListener{
            et_setName.visibility= View.INVISIBLE
            tv_main_name.text = et_setName.text
            tv_main_name.visibility = View.VISIBLE
            bt_ok.visibility= View.INVISIBLE
        }
        iv_back2.setOnClickListener{
            onBackPressed()
        }
    }

}