package com.kakadurf.hw_sem2.controllers

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.snackbar.Snackbar
import com.kakadurf.hw_sem2.R
import com.kakadurf.hw_sem2.controllers.helpers.PermissionHelper
import kotlinx.android.synthetic.main.layout_activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

const val PERMISSION_CODE = 111
class ActivityMain : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.IO
    private val fragmentManager : FragmentManager = supportFragmentManager
    private val permissionService =
        PermissionHelper(this)
    private val onChoice: (Int)->Unit = {
        Log.d("hi", "onChoose")
        swapFragment(
            ExtendedWeatherScreenFragment.create(it)
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity_main)
        if(permissionService.checkPerms()){
            Log.d("hi","has perms")
            swapFragment(ListFragment(onChoice))
        } else {
            permissionService.requestPerm()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(permissionService.onRequestPermissionsResult(requestCode, grantResults))
            swapFragment(ListFragment (onChoice))
        else
            Snackbar.make(fl_main,"permissions denied", Snackbar.LENGTH_LONG).show()
    }

    private fun swapFragment(fragment: Fragment){
        fragmentManager.beginTransaction().replace(R.id.fl_main,fragment).
        addToBackStack("app_stack").commit()
    }

}