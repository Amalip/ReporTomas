package com.example.amalip.reportomas.Utilities

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ProgressBar

/**
 * Created by Amalip on 11/27/2017.
 */
abstract class BaseActivity : AppCompatActivity() {
    internal lateinit var progressDialog: ProgressDialog

    //Override the onCreate method implementing the data that all activies will use
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }

    //This method helps us getting the idLayout of the activity
    abstract fun getLayoutId(): Int

    fun showLoading() {
        progressDialog = ProgressDialog.show(this, null, null)
        progressDialog.setContentView(ProgressBar(this))
        progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun hideLoading() {
        try {
            progressDialog.cancel()
        } catch (ex: Exception) { }
    }
}